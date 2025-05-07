package com.vehiclemanagement.api.vehicle_management.services;

import com.vehiclemanagement.api.vehicle_management.exception.ResourceNotFoundException;
import com.vehiclemanagement.api.vehicle_management.models.Brand;
import com.vehiclemanagement.api.vehicle_management.repositories.BrandRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BrandService {
    private BrandRepository brandRepository;

    @Autowired
    public BrandService(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    public Brand create(Brand brand) {
        return brandRepository.save(brand);
    }

    public List<Brand> getAll() {
        return brandRepository.findAll();
    }

//    public boolean checkExists(Long id) {
//        return brandRepository.existsById(id);
//    }

    public Brand getBrandById(Long id) {

        return brandRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Brand not found with id" + id));
    }

    public Brand update(Long id, Brand brand) {
        Brand res = getBrandById(id);
        if (res == null) {
            return null;
        }
        res .setName(brand.getName());
        res.setType(brand.getType());
        return brandRepository.save(res);
    }

    public void delete(Long id) {
        Brand res = getBrandById(id);
        brandRepository.delete(res);
    }

    public List<Brand> getByName(String name) {
        return brandRepository.findByName(name);
    }

    public List<Brand> getByType(String type) {
        return brandRepository.findByType(Brand.BrandType.valueOf(type));
    }
}
