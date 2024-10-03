package com.hassan.Record;

import com.hassan.Model.Users;

import java.math.BigDecimal;



public record CarRecord(
        String mark,
        String modelName,
        String color,
        Long mileage,
        BigDecimal price,
        int power,
        String yearOfManufacture ) {
}
