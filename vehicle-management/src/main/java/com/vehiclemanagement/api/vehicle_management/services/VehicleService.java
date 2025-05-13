package com.vehiclemanagement.api.vehicle_management.services;

import com.vehiclemanagement.api.vehicle_management.models.SearchRequest;
import com.vehiclemanagement.api.vehicle_management.models.VehicleDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public interface VehicleService {
    Page<VehicleDTO> getAll(Pageable pageable);

    VehicleDTO getVehicleById(Long id);

    VehicleDTO create(VehicleDTO vehicle);

    VehicleDTO update(Long id, VehicleDTO vehicle);

    void delete(Long id);

    Page<VehicleDTO> search(SearchRequest searchRequest, Pageable pageable);

    Page<VehicleDTO> filter(Pageable pageable);
}
