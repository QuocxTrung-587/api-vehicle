package com.vehiclemanagement.api.vehicle_management.controllers;

import com.vehiclemanagement.api.vehicle_management.models.Brand;
import com.vehiclemanagement.api.vehicle_management.models.BrandDTO;
import com.vehiclemanagement.api.vehicle_management.services.BrandServiceImpl;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/brand")
public class BrandController {
    BrandServiceImpl brandServiceImpl;
    public static final int DEFAULT_PAGE_SIZE = 10;

    public BrandController(BrandServiceImpl brandServiceImpl) {
        this.brandServiceImpl = brandServiceImpl;
    }

//    @GetMapping
//    public ResponseEntity<List<BrandDTO>> getAll() {
//        List<Brand> brands = brandServiceImpl.getAll();
//        List<BrandDTO> responses = brands.stream()
//                .map(BrandDTO::new)
//                .collect(Collectors.toList());
//        return ResponseEntity.ok(responses);
//    }

    @GetMapping
    public ResponseEntity<Page<BrandDTO>> getAll(@RequestParam int page) {
        Pageable pageable = PageRequest.of(page, DEFAULT_PAGE_SIZE);
        Page<Brand> brands = brandServiceImpl.getAll(pageable);
        Page<BrandDTO> responses = brands.map(BrandDTO::new);
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BrandDTO> getBrandById(@PathVariable Long id) {
        Brand brand = brandServiceImpl.getBrandByIdAndActiveTrue(id);
        return ResponseEntity.ok(new BrandDTO(brand));
    }

    @PostMapping
    public ResponseEntity<BrandDTO> create(@Valid @RequestBody BrandDTO brandDTO) {
        Brand brand = brandDTO.toBrand();
        Brand created = brandServiceImpl.create(brand);
        return ResponseEntity.ok(new BrandDTO(created));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BrandDTO> update(@PathVariable Long id, @Valid @RequestBody BrandDTO brandDTO) {
        Brand brand = brandDTO.toBrand();
        Brand updated = brandServiceImpl.update(id, brand);
        return ResponseEntity.ok(new BrandDTO(updated));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        brandServiceImpl.delete(id);
    }

//    @GetMapping("/name/{name}")
//    public ResponseEntity<List<BrandDTO>> getByName(@PathVariable String name) {
//        List<Brand> brands = brandServiceImpl.getByName(name);
//        List<BrandDTO> responses = brands.stream()
//                .map(BrandDTO::new)
//                .collect(Collectors.toList());
//        return ResponseEntity.ok(responses);
//    }

    @GetMapping("/name/{name}")
    public ResponseEntity<Page<BrandDTO>> getByName(@PathVariable String name, @RequestParam int page) {
        Pageable pageable = PageRequest.of(page, DEFAULT_PAGE_SIZE);
        Page<Brand> brands = brandServiceImpl.getByName(name, pageable);
        Page<BrandDTO> responses = brands.map(BrandDTO::new);
        return ResponseEntity.ok(responses);
    }

//    @GetMapping("/type/{type}")
//    public ResponseEntity<List<BrandDTO>> getByType(@PathVariable String type) {
//        List<Brand> brands = brandServiceImpl.getByType(type);
//        List<BrandDTO> responses = brands.stream()
//                .map(BrandDTO::new)
//                .collect(Collectors.toList());
//        return ResponseEntity.ok(responses);
//    }

    @GetMapping("/type/{type}")
    public ResponseEntity<Page<BrandDTO>> getByType(@PathVariable String type, @RequestParam int page) {
        Pageable pageable = PageRequest.of(page, DEFAULT_PAGE_SIZE);
        Page<Brand> brands = brandServiceImpl.getByType(type, pageable);
        Page<BrandDTO> responses = brands.map(BrandDTO::new);
        return ResponseEntity.ok(responses);
    }
}
