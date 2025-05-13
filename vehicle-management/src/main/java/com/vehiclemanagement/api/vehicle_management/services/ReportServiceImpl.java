package com.vehiclemanagement.api.vehicle_management.services;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReportServiceImpl {
    public byte[] generateReport(String reportName, List<?> data, Map<String, Object> parameters) throws Exception {
        File file = ResourceUtils.getFile("classpath:reports/" + reportName + ".jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());

        if (!parameters.containsKey("SUBREPORT_DIR")) {
            parameters.put("SUBREPORT_DIR", "classpath:reports/");
        }
        
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(data);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
        return JasperExportManager.exportReportToPdf(jasperPrint);
    }
}