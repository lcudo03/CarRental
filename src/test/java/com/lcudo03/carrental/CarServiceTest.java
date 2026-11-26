package com.lcudo03.carrental;

import com.lcudo03.carrental.model.Car;
import com.lcudo03.carrental.repository.CarRepository;
import com.lcudo03.carrental.service.CarService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

public class CarServiceTest {
    CarService carService;
    CarRepository carRepository;

    @BeforeEach
    void setup() {
        this.carRepository = Mockito.mock(CarRepository.class);
        this.carService = new CarService(carRepository);
    }

    @Test
    void findAll_DataCorrect_CarsReturned() {
        List<Car> cars = List.of(
                new Car("FZG1944", "Chevrolet", "Matiz", 2006, "red", 210001, 1000),
                new Car("FZA1410", "BMW", "E36", 1999, "blue", 1200000, 60)
        );
        when(carRepository.findAll()).thenReturn(cars);
        List<Car> result = carService.findAll();

        Assertions.assertAll(
                () -> Assertions.assertEquals(2, result.size()),
                () -> Assertions.assertEquals("FZG1944", result.get(0).getRegistrationNumber()),
                () -> Assertions.assertEquals("Chevrolet", result.get(0).getBrand()),
                () -> Assertions.assertEquals("Matiz", result.get(0).getModel()),
                () -> Assertions.assertEquals(2006, result.get(0).getProductionYear()),
                () -> Assertions.assertEquals("red", result.get(0).getColor()),
                () -> Assertions.assertEquals(210001, result.get(0).getMileage()),
                () -> Assertions.assertEquals(1000, result.get(0).getPricePerDay()),
                () -> Assertions.assertEquals("FZA1410", result.get(1).getRegistrationNumber()),
                () -> Assertions.assertEquals("BMW", result.get(1).getBrand()),
                () -> Assertions.assertEquals("E36", result.get(1).getModel()),
                () -> Assertions.assertEquals(1999, result.get(1).getProductionYear()),
                () -> Assertions.assertEquals("blue", result.get(1).getColor()),
                () -> Assertions.assertEquals(1200000, result.get(1).getMileage()),
                () -> Assertions.assertEquals(60, result.get(1).getPricePerDay())
        );
    }

    @Test
    void findByRegistrationNumber_CarExists_CarReturned() {
        String registrationNumber = "PL09872";
        Car car = new Car(registrationNumber, "Cool", "Car", 2020, "Black", 123457, 100);
        when(carRepository.findByRegistrationNumber(registrationNumber))
                .thenReturn(Optional.of(car));
        Car result = carService.findByRegistrationNumber(registrationNumber);

        Assertions.assertAll(
                () -> Assertions.assertEquals(registrationNumber, result.getRegistrationNumber()),
                () -> Assertions.assertEquals("Cool", result.getBrand()),
                () -> Assertions.assertEquals("Car", result.getModel()),
                () -> Assertions.assertEquals(2020, result.getProductionYear()),
                () -> Assertions.assertEquals("Black", result.getColor()),
                () -> Assertions.assertEquals(123457, result.getMileage()),
                () -> Assertions.assertEquals(100, result.getPricePerDay())
        );
    }

    @Test
    void save_ValidData_CarSaved() {
        Car car = new Car("FZG1944", "Chevrolet", "Matiz", 2006, "red", 210001, 1000);
        when(carRepository.findByRegistrationNumber(car.getRegistrationNumber())).thenReturn(Optional.empty());
        when(carRepository.save(car)).thenReturn(car);
        Car result = carService.save(car);

        Assertions.assertAll(
                () -> Assertions.assertEquals("FZG1944", result.getRegistrationNumber()),
                () -> Assertions.assertEquals("Chevrolet", result.getBrand()),
                () -> Assertions.assertEquals("Matiz", result.getModel()),
                () -> Assertions.assertEquals(2006, result.getProductionYear()),
                () -> Assertions.assertEquals("red", result.getColor()),
                () -> Assertions.assertEquals(210001, result.getMileage()),
                () -> Assertions.assertEquals(1000, result.getPricePerDay())
        );
    }

    @Test
    void modifyByRegistrationNumber_CarExists_CarUpdated() {
        String registrationNumber = "PL09872";
        Car car = new Car(registrationNumber, "Cool", "Car", 2020, "Black", 123457, 100);
        Car updatedCar = new Car(registrationNumber, "NotCool", "Car", 2020, "Black", 1234567, 1000);
        when(carRepository.findByRegistrationNumber(registrationNumber)).thenReturn(Optional.of(car));
        when(carRepository.modifyByRegistrationNumber(car, updatedCar)).thenReturn(updatedCar);
        Car result = carService.modifyByRegistrationNumber(registrationNumber, updatedCar);

        Assertions.assertAll(
                () -> Assertions.assertEquals(registrationNumber, result.getRegistrationNumber()),
                () -> Assertions.assertEquals("NotCool", result.getBrand()),
                () -> Assertions.assertEquals("Car", result.getModel()),
                () -> Assertions.assertEquals(2020, result.getProductionYear()),
                () -> Assertions.assertEquals("Black", result.getColor()),
                () -> Assertions.assertEquals(1234567, result.getMileage()),
                () -> Assertions.assertEquals(1000, result.getPricePerDay())
        );
    }

    @Test
    void findAllByBrand_BrandExists_CarsReturned() {
        String brand = "Chevrolet";
        Car car1 = new Car("FZG1944", "Chevrolet", "Matiz", 2006, "red", 210001, 1000);
        Car car2 = new Car("FZA1945", "Chevrolet", "Matiz", 2003, "golden", 0, 5000);
        List<Car> cars = List.of(car1, car2);
        when(carRepository.findAllByBrand(brand)).thenReturn(cars);
        List<Car> result = carService.findAllByBrand(brand);

        Assertions.assertAll(
                () -> Assertions.assertEquals(2, result.size()),
                () -> Assertions.assertEquals("Chevrolet", result.get(0).getBrand()),
                () -> Assertions.assertEquals(2006, result.get(0).getProductionYear()),
                () -> Assertions.assertEquals("Chevrolet", result.get(1).getBrand()),
                () -> Assertions.assertEquals(2003, result.get(1).getProductionYear())
        );

    }
}
