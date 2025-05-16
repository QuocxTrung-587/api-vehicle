package com.api.vehiclemanagement.services;

import com.api.vehiclemanagement.models.BrandDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public interface BrandService {
    Page<BrandDTO> getAll(Pageable pageable);

    BrandDTO getBrandById(Long id);

    BrandDTO create(BrandDTO brand);

    BrandDTO update(Long id, BrandDTO brand);

    void delete(Long id);

    Page<BrandDTO> getByName(String name,Pageable pageable);

    Page<BrandDTO> getByType(String type, Pageable pageable);
}
