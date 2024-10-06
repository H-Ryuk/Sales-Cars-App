package com.hassan.Record;

import com.hassan.Enumeration.Role;

public record SellerOfCarsRecord(
        String email,
        String password,
        Role role
) {
}
