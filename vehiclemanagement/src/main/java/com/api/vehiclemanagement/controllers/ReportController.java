package com.api.vehiclemanagement.controllers;

import com.api.vehiclemanagement.models.VehicleDTO;
import com.api.vehiclemanagement.models.VehicleReportDTO;
import com.api.vehiclemanagement.services.ReportServiceImpl;
import com.api.vehiclemanagement.services.VehicleServiceImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/reports")
public class ReportController {
    private final ReportServiceImpl reportService;
    private final VehicleServiceImpl vehicleService;

    public ReportController(ReportServiceImpl reportService, VehicleServiceImpl vehicleService) {
        this.reportService = reportService;
        this.vehicleService = vehicleService;
    }

    @GetMapping("/{reportName}")
    public ResponseEntity<byte[]> generateReport(
            @PathVariable String reportName,
            @RequestParam(required = false) Long id) {
        try {
            List<?> data;
            Map<String, Object> parameters = new HashMap<>();

            if (reportName.equals("vehicle")) {
                Pageable pageable = PageRequest.of(0, 100);
                Page<VehicleDTO> vehiclePage = vehicleService.getAll(pageable);

//                data = vehiclePage.getContent().stream()
//                    .collect(Collectors.toList());

                data = vehiclePage.getContent().stream()
                        .map(dto -> {
                            VehicleReportDTO r = new VehicleReportDTO();
                            r.setName(dto.getName());
                            r.setYear(dto.getYear());
                            r.setPrice(dto.getPrice());
                            r.setOwner(dto.getOwner());
                            r.setBrandName(dto.getBrand().getName());
                            r.setBrandType(dto.getBrand().getType().name());
                            return r;
                        })
                        .collect(Collectors.toList());
            } else if (reportName.equals("vehicledetail") && id != null) {
                VehicleDTO vehicle = vehicleService.getVehicleById(id);
                VehicleReportDTO vehicleReport = new VehicleReportDTO(vehicle.toVehicle());
                vehicleReport.setBrandName(vehicle.getBrand().getName());
                vehicleReport.setBrandType(vehicle.getBrand().getType().name());
                data = List.of(vehicleReport);
                parameters.put("VEHICLE_ID", id);
            } else {
                data = List.of();
            }


            byte[] reportBytes = reportService.generateReport(reportName, data, parameters);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDispositionFormData("filename", reportName + ".pdf");

            return ResponseEntity.ok()
                    .headers(headers)
                    .body(reportBytes);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

}
