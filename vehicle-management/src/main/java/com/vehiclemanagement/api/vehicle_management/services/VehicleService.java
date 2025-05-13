package com.vehiclemanagement.api.vehicle_management.services;

import com.vehiclemanagement.api.vehicle_management.models.SearchRequest;
import com.vehiclemanagement.api.vehicle_management.models.Vehicle;
import com.vehiclemanagement.api.vehicle_management.models.VehicleDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public interface VehicleService {
    public Page<VehicleDTO> getAll(Pageable pageable);

    public VehicleDTO getVehicleById(Long id);

    public VehicleDTO create(VehicleDTO vehicle);

    public VehicleDTO update(Long id, VehicleDTO vehicle);

    public void delete(Long id);

    public Page<VehicleDTO> search(SearchRequest searchRequest, Pageable pageable);

    public Page<VehicleDTO> filter(Pageable pageable);
}
