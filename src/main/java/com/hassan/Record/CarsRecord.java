package com.hassan.Record;


import java.math.BigDecimal;

public record CarsRecord(
        String mark,
        String modelName,
        String color,
        Long mileage,
        BigDecimal price,
        int power,
        String yearOfManufacture,
        String userName,
        String email,
        String phoneNumber,
        String cin) {
}
