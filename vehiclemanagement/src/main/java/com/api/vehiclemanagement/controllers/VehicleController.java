package com.api.vehiclemanagement.controllers;

import com.api.vehiclemanagement.models.SearchRequest;
import com.api.vehiclemanagement.models.VehicleDTO;
import com.api.vehiclemanagement.services.VehicleServiceImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/vehicle")
public class VehicleController {
    private VehicleServiceImpl vehicleServiceImpl;
    public static final int DEFAULT_PAGE_SIZE = 10;

    public VehicleController(VehicleServiceImpl vehicleServiceImpl) {
        this.vehicleServiceImpl = vehicleServiceImpl;
    }

//    @GetMapping
//    public ResponseEntity<List<VehicleDTO>> getAll() {
//        List<Vehicle> vehicles = vehicleServiceImpl.getAll();
//        List<VehicleDTO> response = vehicles.stream()
//                .map(VehicleDTO::new)
//                .collect(Collectors.toList());
//        return ResponseEntity.ok(response);
//    }

    @GetMapping
    public ResponseEntity<Page<VehicleDTO>> getAll(@RequestParam int page) {
        Pageable pageable = PageRequest.of(page, DEFAULT_PAGE_SIZE);
        Page<VehicleDTO> vehicles = vehicleServiceImpl.getAll(pageable);
        return ResponseEntity.ok(vehicles);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VehicleDTO> getVehicleById(@PathVariable Long id) {
        VehicleDTO vehicle = vehicleServiceImpl.getVehicleById(id);
        return ResponseEntity.ok(vehicle);
    }

    @PostMapping
    public ResponseEntity<VehicleDTO> create(@RequestBody VehicleDTO vehicleDTO) {
        VehicleDTO res = vehicleServiceImpl.create(vehicleDTO);
        return ResponseEntity.ok(res);
    }

    @PutMapping("/{id}")
    public ResponseEntity<VehicleDTO> update(@PathVariable Long id, @RequestBody VehicleDTO vehicleDTO) {
        VehicleDTO updated = vehicleServiceImpl.update(id, vehicleDTO);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        vehicleServiceImpl.delete(id);
    }

//    @GetMapping("/search")
//    public ResponseEntity<List<VehicleDTO>> search(@RequestParam(required = false) String brand, @RequestParam(required = false) Integer year, @RequestParam(required = false) Long price, @RequestParam(required = false) String owner) {
//        List<Vehicle> vehicles = vehicleServiceImpl.search(brand, year, price, owner);
//        List<VehicleDTO> response = vehicles.stream()
//                .map(VehicleDTO::new)
//                .collect(Collectors.toList());
//        return ResponseEntity.ok(response);
//    }

//    @GetMapping("/search")
//    public ResponseEntity<Page<VehicleDTO>> search(
//            @RequestParam(required = false) String brand,
//            @RequestParam(required = false) Integer year,
//            @RequestParam(required = false) Long price,
//            @RequestParam(required = false) String owner,
//            Pageable pageable) {
//        Page<Vehicle> vehicles = vehicleServiceImpl.search(brand, year, price, owner, pageable);
//        Page<VehicleDTO> response = vehicles.map(VehicleDTO::new);
//        return ResponseEntity.ok(response);
//    }

    @GetMapping("/search")
    public ResponseEntity<Page<VehicleDTO>> search(
            @RequestParam(required = false) String brand,
            @RequestParam(required = false) Integer year,
            @RequestParam(required = false) Long price,
            @RequestParam(required = false) String owner,
            @RequestParam int page) {
        SearchRequest searchRequest = new SearchRequest(brand, year, price, owner);
        Pageable pageable = PageRequest.of(page, DEFAULT_PAGE_SIZE);
        Page<VehicleDTO> vehicles = vehicleServiceImpl.search(searchRequest, pageable);
        return ResponseEntity.ok(vehicles);
    }

    @PostMapping("/search")
    public ResponseEntity<Page<VehicleDTO>> search(
            @RequestBody SearchRequest searchRequest,
            @RequestParam int page) {
        Pageable pageable = PageRequest.of(page, DEFAULT_PAGE_SIZE);
        Page<VehicleDTO> vehicles = vehicleServiceImpl.search(searchRequest, pageable);
        return ResponseEntity.ok(vehicles);
    }

//    @GetMapping("/filter")
//    public ResponseEntity<List<VehicleDTO>> filter() {
//        List<Vehicle> vehicles = vehicleServiceImpl.filter();
//        List<VehicleDTO> response = vehicles.stream()
//                .map(VehicleDTO::new)
//                .collect(Collectors.toList());
//        return ResponseEntity.ok(response);
//    }

    @GetMapping("/filter")
    public ResponseEntity<Page<VehicleDTO>> filter(@RequestParam int page) {
        Pageable pageable = PageRequest.of(page, DEFAULT_PAGE_SIZE);
        Page<VehicleDTO> vehicles = vehicleServiceImpl.filter(pageable);
        return ResponseEntity.ok(vehicles);
    }
}
