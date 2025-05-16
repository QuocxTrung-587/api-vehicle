package com.api.vehiclemanagement.services;

import com.api.vehiclemanagement.entities.Brand;
import com.api.vehiclemanagement.exception.ResourceNotFoundException;
import com.api.vehiclemanagement.models.BrandDTO;
import com.api.vehiclemanagement.repositories.BrandRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class BrandServiceImpl implements BrandService {
    private BrandRepository brandRepository;

    public BrandServiceImpl(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    public BrandDTO create(BrandDTO brand) {
        Brand res = brandRepository.save(brand.toBrand());
        return new BrandDTO(res);
    }

    public Page<BrandDTO> getAll(Pageable pageable) {
        Page<Brand> brands = brandRepository.findByActiveTrue(pageable);
        return brands.map(BrandDTO::new);
    }

    public BrandDTO getBrandById(Long id) {

        Brand brand =  brandRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Brand not found with id" + id));
        return new BrandDTO(brand);
    }

    public BrandDTO getBrandByIdAndActiveTrue(Long id) {
        Brand brand = brandRepository.findByIdAndActiveTrue(id);
        return new BrandDTO(brand);
    }

    public BrandDTO update(Long id, BrandDTO brand) {
        BrandDTO res = getBrandById(id);
        if (res == null) {
            return null;
        }
        res .setName(brand.getName());
        res.setType(brand.getType());
        res.setActive(brand.isActive());
        Brand updated = brandRepository.save(res.toBrand());
        return new BrandDTO(updated);
    }

    public void delete(Long id) {
        BrandDTO res = getBrandById(id);

        res.setActive(false);
        brandRepository.save(res.toBrand());
//        brandRepository.delete(res);
    }

    public Page<BrandDTO> getByName(String name, Pageable pageable) {
        Page<Brand> brands = brandRepository.findBrandsByNameContainingIgnoreCaseAndActiveTrue(name, pageable);
        return brands.map(BrandDTO::new);
    }

    public Page<BrandDTO> getByType(String type, Pageable pageable) {
        Page<Brand> brands = brandRepository.findBrandsByTypeAndActiveTrue(Brand.BrandType.valueOf(type), pageable);
        return brands.map(BrandDTO::new);
    }
}
