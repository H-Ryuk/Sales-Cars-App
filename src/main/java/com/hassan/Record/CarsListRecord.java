package com.hassan.Record;

import com.hassan.Model.Cars;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.List;

public record CarsListRecord(
        List<Cars> carsList ) {
}
