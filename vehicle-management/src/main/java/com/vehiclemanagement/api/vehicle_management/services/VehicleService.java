package com.vehiclemanagement.api.vehicle_management.services;

import com.vehiclemanagement.api.vehicle_management.exception.ResourceNotFoundException;
import com.vehiclemanagement.api.vehicle_management.models.Brand;
import com.vehiclemanagement.api.vehicle_management.models.Vehicle;
import com.vehiclemanagement.api.vehicle_management.repositories.BrandRepository;
import com.vehiclemanagement.api.vehicle_management.repositories.VehicleRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class VehicleService {
    private VehicleRepository vehicleRepository;

    private BrandRepository brandRepository;

    public VehicleService(VehicleRepository vehicleRepository, BrandRepository brandRepository) {
        this.vehicleRepository = vehicleRepository;
        this.brandRepository = brandRepository;
    }

    public List<Vehicle> getAll() {
        return vehicleRepository.findAll();
    }

    public Vehicle getVehicleById(Long id) {
        return vehicleRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Vehicle not found with id" + id));
    }

    public Vehicle create(Vehicle vehicle) {
        Brand brand = brandRepository.findById(vehicle.getBrand().getId()).orElseThrow(() -> new ResourceNotFoundException("Brand not found with id" + vehicle.getBrand().getId()));

        Vehicle res = new Vehicle();
        res.setName(vehicle.getName());
        res.setYear(vehicle.getYear());
        res.setPrice(vehicle.getPrice());
        res.setOwner(vehicle.getOwner());
        res.setCreatedAt(Instant.now());
        res.setBrand(brand);

        return vehicleRepository.save(res);
    }

    public boolean checkExists(Long id) {
        return vehicleRepository.existsById(id);
    }

    public Vehicle update(Long id, Vehicle vehicle) {
        if (checkExists(id)) {
            vehicle.setId(id);
            if (vehicle.getCreatedAt() == null) {
                vehicle.setCreatedAt(getVehicleById(id).getCreatedAt());
            }
            brandRepository.findById(vehicle.getBrand().getId()).orElseThrow(() -> new ResourceNotFoundException("Brand not found with id" + vehicle.getBrand().getId()));
            return vehicleRepository.save(vehicle);
        }
        throw new ResourceNotFoundException("Vehicle not found with id" + id);
    }

    public void delete(Long id) {
        Vehicle vehicle = getVehicleById(id);
        vehicleRepository.delete(vehicle);
    }

    public List<Vehicle> search(String brand, Integer year, Long price, String owner) {
        return vehicleRepository.search(brand, year, price, owner);
    }

    public List<Vehicle> filter() {
        return vehicleRepository.filterByPriceAndBrand();
    }
}