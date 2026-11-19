package com.lcudo03.carrental.service;

import com.lcudo03.carrental.exceptions.CarAlreadyExistsException;
import com.lcudo03.carrental.exceptions.CarNotFoundException;
import com.lcudo03.carrental.model.Car;
import com.lcudo03.carrental.repository.CarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.Year;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CarService {
    private final CarRepository repository;

    public List<Car> findAll() {
        return repository.findAll();
    }

    public Car findByRegistrationNumber(String registrationNumber) {
        return repository.findByRegistrationNumber(registrationNumber)
                .orElseThrow(() -> new CarNotFoundException(registrationNumber));
    }

    public Car save(Car car) {
        if (car.getProductionYear() > Year.now().getValue() || car.getPricePerDay() <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid production year or price per day");
        }

        if (repository.findByRegistrationNumber(car.getRegistrationNumber()).isPresent()) {
            throw new CarAlreadyExistsException(car.getRegistrationNumber());
        }

        return repository.save(car);
    }

    public void deleteByRegistrationNumber(String registrationNumber) {
        if (!repository.deleteByRegistrationNumber(registrationNumber)) {
            throw new CarNotFoundException(registrationNumber);
        }

    }

    public Car modifyByRegistrationNumber(String registrationNumber, Car updatedCar) {
        Car car = findByRegistrationNumber(registrationNumber);
        return repository.modifyByRegistrationNumber(car, updatedCar);
    }

    public List<Car> findAllByBrand(String brand) {
        return repository.findAllByBrand(brand);
    }
}
