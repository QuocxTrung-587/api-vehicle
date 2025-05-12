package com.vehiclemanagement.api.vehicle_management.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchRequest {
    private String brandName;
    private Integer year;
    private Long price;
    private String owner;
}
