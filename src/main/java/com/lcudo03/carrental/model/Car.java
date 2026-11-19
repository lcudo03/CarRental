package com.lcudo03.carrental.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Car {
    @NotBlank
    private String registrationNumber;

    @NotBlank
    private String brand;

    @NotBlank
    private String model;

    @NotNull
    private int productionYear;

    @NotBlank
    private String color;

    @NotNull
    private int mileage;

    @NotNull
    private double pricePerDay;
}
