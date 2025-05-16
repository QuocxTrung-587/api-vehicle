package com.api.vehiclemanagement.repositories;

import com.api.vehiclemanagement.entities.Brand;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BrandRepository extends JpaRepository<Brand, Long>, PagingAndSortingRepository<Brand, Long> {

    Page<Brand> findByActiveTrue(Pageable pageable);

    Brand findByIdAndActiveTrue(Long id);

    Page<Brand> findBrandsByNameContainingIgnoreCaseAndActiveTrue(String name, Pageable pageable);

    Page<Brand> findBrandsByTypeAndActiveTrue(Brand.BrandType type, Pageable pageable);
}
