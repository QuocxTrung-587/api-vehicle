package com.vehiclemanagement.api.vehicle_management.services;

import com.vehiclemanagement.api.vehicle_management.models.SearchRequest;
import com.vehiclemanagement.api.vehicle_management.models.Vehicle;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public interface VehicleService {
    public Page<Vehicle> getAll(Pageable pageable);

    public Vehicle getVehicleById(Long id);

    public Vehicle create(Vehicle vehicle);

    public Vehicle update(Long id, Vehicle vehicle);

    public void delete(Long id);

    public Page<Vehicle> search(SearchRequest searchRequest, Pageable pageable);

    public Page<Vehicle> filter(Pageable pageable);
}
