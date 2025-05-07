package com.vehiclemanagement.api.vehicle_management.controllers;

import com.vehiclemanagement.api.vehicle_management.models.Brand;
import com.vehiclemanagement.api.vehicle_management.services.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/brand")
public class BrandController {
    BrandService brandService = new BrandService();

    @Autowired
    public BrandController(BrandService brandService) {
        this.brandService = brandService;
    }

    @GetMapping
    public ResponseEntity<List<Brand>> getAll() {
        return ResponseEntity.ok(brandService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Brand> getBrandById(@PathVariable Long id) {
        return ResponseEntity.ok(brandService.getBrandById(id));
    }

    @PostMapping
    public ResponseEntity<Brand> create(@RequestBody Brand brand) {
        return ResponseEntity.ok(brandService.create(brand));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Brand> update(@PathVariable Long id, @RequestBody Brand brand) {
        return ResponseEntity.ok(brandService.update(id, brand));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        brandService.delete(id);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<List<Brand>> getByName(@PathVariable String name) {
        return ResponseEntity.ok(brandService.getByName(name));
    }

    @GetMapping("/type/{type}")
    public ResponseEntity<List<Brand>> getByType(@PathVariable String type) {
        return ResponseEntity.ok(brandService.getByType(type));
    }
}
