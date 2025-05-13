package com.vehiclemanagement.api.vehicle_management.services;

import com.vehiclemanagement.api.vehicle_management.exception.ResourceNotFoundException;
import com.vehiclemanagement.api.vehicle_management.models.Brand;
import com.vehiclemanagement.api.vehicle_management.models.SearchRequest;
import com.vehiclemanagement.api.vehicle_management.models.Vehicle;
import com.vehiclemanagement.api.vehicle_management.models.VehicleDTO;
import com.vehiclemanagement.api.vehicle_management.repositories.BrandRepository;
import com.vehiclemanagement.api.vehicle_management.repositories.VehicleRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class VehicleServiceImpl implements VehicleService {
    private VehicleRepository vehicleRepository;

    private BrandRepository brandRepository;

    public VehicleServiceImpl(VehicleRepository vehicleRepository, BrandRepository brandRepository) {
        this.vehicleRepository = vehicleRepository;
        this.brandRepository = brandRepository;
    }

    public Page<VehicleDTO> getAll(Pageable pageable) {
        Page<Vehicle> vehicles = vehicleRepository.findAll(pageable);
        return vehicles.map(VehicleDTO::new);
    }

    public VehicleDTO getVehicleById(Long id) {
        Vehicle vehicle = vehicleRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Vehicle not found with id" + id));
        return new VehicleDTO(vehicle);
    }

    public VehicleDTO create(VehicleDTO vehicle) {
        Brand brand = brandRepository.findById(vehicle.getBrand().getId()).orElseThrow(() -> new ResourceNotFoundException("Brand not found with id" + vehicle.getBrand().getId()));

        Vehicle res = new Vehicle();
        res.setName(vehicle.getName());
        res.setYear(vehicle.getYear());
        res.setPrice(vehicle.getPrice());
        res.setOwner(vehicle.getOwner());
        res.setCreatedAt(Instant.now());
        res.setBrand(brand);

        Vehicle res1 = vehicleRepository.save(res);
        return new VehicleDTO(res1);
    }

    public boolean checkExists(Long id) {
        return vehicleRepository.existsById(id);
    }

    public VehicleDTO update(Long id, VehicleDTO vehicle) {
        if (checkExists(id)) {
            vehicle.setId(id);
            if (vehicle.getCreatedAt() == null) {
                vehicle.setCreatedAt(getVehicleById(id).getCreatedAt());
            }
            brandRepository.findById(vehicle.getBrand().getId()).orElseThrow(() -> new ResourceNotFoundException("Brand not found with id" + vehicle.getBrand().getId()));
            Vehicle res = vehicleRepository.save(vehicle.toVehicle());
            return new VehicleDTO(res);
        }
//        if (checkExists(id)) {
//            vehicle.setId(id);
//
//            Instant originalCreatedAt = getVehicleById(id).getCreatedAt(); // ✅ Lấy createdAt cũ
//
//            brandRepository.findById(vehicle.getBrand().getId())
//                    .orElseThrow(() -> new ResourceNotFoundException("Brand not found with id" + vehicle.getBrand().getId()));
//
//            Vehicle res = vehicleRepository.save(vehicle.toVehicle(originalCreatedAt)); // ✅ Dùng createdAt mặc định
//            return new VehicleDTO(res);
//        }
        throw new ResourceNotFoundException("Vehicle not found with id" + id);
    }

    public void delete(Long id) {
        VehicleDTO vehicle = getVehicleById(id);
        vehicleRepository.delete(vehicle.toVehicle());
    }

    public Page<VehicleDTO> search(SearchRequest searchRequest, Pageable pageable) {
        Page<Vehicle> vehicles = vehicleRepository.search(searchRequest, pageable);
        return vehicles.map(VehicleDTO::new);
    }

    public Page<VehicleDTO> filter(Pageable pageable) {
        Page<Vehicle> vehicles = vehicleRepository.filterByPriceAndBrand(pageable);
        return vehicles.map(VehicleDTO::new);
    }
}