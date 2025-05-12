package com.vehiclemanagement.api.vehicle_management.services;

import com.vehiclemanagement.api.vehicle_management.exception.ResourceNotFoundException;
import com.vehiclemanagement.api.vehicle_management.models.Brand;
import com.vehiclemanagement.api.vehicle_management.repositories.BrandRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class BrandServiceImpl implements BrandService {
    private BrandRepository brandRepository;

    public BrandServiceImpl(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    public Brand create(Brand brand) {
        return brandRepository.save(brand);
    }

    public Page<Brand> getAll(Pageable pageable) {
        return brandRepository.findByActiveTrue(pageable);
    }

    public Brand getBrandById(Long id) {

        return brandRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Brand not found with id" + id));
    }

    public Brand getBrandByIdAndActiveTrue(Long id) {
        return brandRepository.findByIdAndActiveTrue(id);
    }

    public Brand update(Long id, Brand brand) {
        Brand res = getBrandById(id);
        if (res == null) {
            return null;
        }
        res .setName(brand.getName());
        res.setType(brand.getType());
        res.setActive(brand.isActive());
        return brandRepository.save(res);
    }

    public void delete(Long id) {
        Brand res = getBrandById(id);

        res.setActive(false);
        brandRepository.save(res);
//        brandRepository.delete(res);
    }

    public Page<Brand> getByName(String name, Pageable pageable) {
        return brandRepository.findBrandsByNameContainingIgnoreCaseAndActiveTrue(name, pageable);
    }

    public Page<Brand> getByType(String type, Pageable pageable) {
        return brandRepository.findBrandsByTypeAndActiveTrue(Brand.BrandType.valueOf(type), pageable);
    }
}
