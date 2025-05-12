package com.vehiclemanagement.api.vehicle_management.services;

import com.vehiclemanagement.api.vehicle_management.models.Brand;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public interface BrandService {
    public Page<Brand> getAll(Pageable pageable);

    public Brand getBrandById(Long id);

    public Brand create(Brand brand);

    public Brand update(Long id, Brand brand);

    public void delete(Long id);

    public Page<Brand> getByName(String name,Pageable pageable);

    public Page<Brand> getByType(String type, Pageable pageable);
}
