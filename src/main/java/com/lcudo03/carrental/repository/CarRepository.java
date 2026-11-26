package com.lcudo03.carrental.repository;

import com.lcudo03.carrental.model.Car;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class CarRepository {
    private final List<Car> carList = new ArrayList<>();

    public CarRepository() {
        carList.add(new Car("WA12345", "Toyota", "Corolla", 2020, "Black", 50000, 150.0));

        carList.add(new Car("KR4567A", "Skoda", "Octavia", 2021, "Silver", 45000, 180.0));

        carList.add(new Car("DW98765", "BMW", "320d", 2022, "White", 30000, 250.0));
    }

    public List<Car> findAll() {
        return new ArrayList<>(carList);
    }

    public Optional<Car> findByRegistrationNumber(String registrationNumber) {
        return carList.stream()
                .filter(car -> registrationNumber.equals(car.getRegistrationNumber()))
                .findFirst();
    }

    public Car save(Car car) {
        carList.add(car);
        return car;
    }

    public boolean deleteByRegistrationNumber(String registrationNumber) {
        return findByRegistrationNumber(registrationNumber)
                .map(carList::remove)
                .orElse(false);
    }

    public Car modifyByRegistrationNumber(Car car, Car updatedCar) {
        car.setBrand(updatedCar.getBrand());
        car.setModel(updatedCar.getModel());
        car.setProductionYear(updatedCar.getProductionYear());
        car.setColor(updatedCar.getColor());
        car.setMileage(updatedCar.getMileage());
        car.setPricePerDay(updatedCar.getPricePerDay());
        return car;
    }

    public List<Car> findAllByBrand(String brand) {
        return carList.stream()
                .filter(car -> brand.equalsIgnoreCase(car.getBrand()))
                .toList();
    }

}
