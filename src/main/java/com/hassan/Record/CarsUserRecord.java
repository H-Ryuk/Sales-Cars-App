package com.hassan.Record;

import com.hassan.Model.Cars;
import com.hassan.Model.Users;

import java.util.List;

public record CarsUserRecord(
        List<Cars> cars,
        Users user
) {
}
