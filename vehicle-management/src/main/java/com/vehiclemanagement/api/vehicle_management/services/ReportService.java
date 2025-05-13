package com.vehiclemanagement.api.vehicle_management.services;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface ReportService {
    public byte[] generateReport(String reportName, List<?> data, Map<String, Object> parameters) throws Exception;
}
