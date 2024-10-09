package com.hassan.Record;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record CarRecord(
        @NotBlank(message = "car's mark should not be null")
        String mark,

        @NotBlank(message = "car's model should not be null")
        String modelName,

        @NotBlank(message = "car's color should not be null")
        String color,

        @NotNull(message = "car's mileage should not be null")
        Long mileage,

        @NotNull(message = "car's price should not be null")
        BigDecimal price,

        @Min(value = 1 , message = "car's power should not be null")
        int power,

        @NotBlank(message = "car's year of manufacture should not be null")
        String yearOfManufacture ) {
}
