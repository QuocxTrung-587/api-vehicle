package com.vehiclemanagement.api.vehicle_management.services;

import com.vehiclemanagement.api.vehicle_management.models.Brand;
import com.vehiclemanagement.api.vehicle_management.models.BrandDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public interface BrandService {
    public Page<BrandDTO> getAll(Pageable pageable);

    public BrandDTO getBrandById(Long id);

    public BrandDTO create(BrandDTO brand);

    public BrandDTO update(Long id, BrandDTO brand);

    public void delete(Long id);

    public Page<BrandDTO> getByName(String name,Pageable pageable);

    public Page<BrandDTO> getByType(String type, Pageable pageable);
}
