package com.vehiclemanagement.api.vehicle_management.controllers;

import com.vehiclemanagement.api.vehicle_management.models.Vehicle;
import com.vehiclemanagement.api.vehicle_management.services.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vehicle")
public class VehicleController {
    private VehicleService vehicleService;

    @Autowired
    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @GetMapping
    public ResponseEntity<List<Vehicle>> getAll() {
        return ResponseEntity.ok(vehicleService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Vehicle> getVehicleById(@PathVariable Long id) {
        return ResponseEntity.ok(vehicleService.getVehicleById(id));
    }

    @PostMapping
    public ResponseEntity<Vehicle> create(@RequestBody Vehicle vehicle) {
        return ResponseEntity.ok(vehicleService.create(vehicle));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Vehicle> update(@PathVariable Long id, @RequestBody Vehicle vehicle) {
        return ResponseEntity.ok(vehicleService.update(id, vehicle));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        vehicleService.delete(id);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Vehicle>> search(@RequestParam(required = false) String brand, @RequestParam(required = false) Integer year, @RequestParam(required = false) Long price, @RequestParam(required = false) String owner) {
        return ResponseEntity.ok(vehicleService.search(brand, year, price, owner));
    }

    @GetMapping("/filter")
    public ResponseEntity<List<Vehicle>> filter() {
        return ResponseEntity.ok(vehicleService.filter());
    }
}