package com.lcudo03.carrental.controller;

import com.lcudo03.carrental.model.Car;
import com.lcudo03.carrental.service.CarService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cars")
public class CarController {

    private final CarService service;

    @GetMapping
    public List<Car> findAll() {
        return service.findAll();
    }

    @GetMapping("/{registrationNumber}")
    public Car findByRegistrationNumber(@PathVariable String registrationNumber) {
        return service.findByRegistrationNumber(registrationNumber);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Car save(@Valid @RequestBody Car car) {
        return service.save(car);
    }

    @DeleteMapping("/{registrationNumber}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteByRegistrationNumber(@PathVariable String registrationNumber) {
        service.deleteByRegistrationNumber(registrationNumber);
    }

    @PutMapping("/{registrationNumber}")
    public Car modifyByRegistrationNumber(@PathVariable String registrationNumber, @Valid @RequestBody Car car) {
        return service.modifyByRegistrationNumber(registrationNumber, car);
    }

    @GetMapping("/brand/{brand}")
    public List<Car> findAllByBrand(@PathVariable String brand) {
        return service.findAllByBrand(brand);
    }
}
