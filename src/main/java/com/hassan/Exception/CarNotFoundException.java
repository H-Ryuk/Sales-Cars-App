package com.hassan.Exception;

import jakarta.validation.constraints.NotBlank;

public class CarNotFoundException extends RuntimeException {


    public CarNotFoundException(Long carId) {
        super("Car not found with ID: " + carId);
    }


    public CarNotFoundException() {
        super("Car not found");
    }
}
