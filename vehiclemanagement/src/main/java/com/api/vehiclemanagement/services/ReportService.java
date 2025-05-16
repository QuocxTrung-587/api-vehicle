package com.api.vehiclemanagement.services;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface ReportService {
    byte[] generateReport(String reportName, List<?> data, Map<String, Object> parameters) throws Exception;
}
