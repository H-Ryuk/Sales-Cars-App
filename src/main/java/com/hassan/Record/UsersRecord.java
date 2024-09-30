package com.hassan.Record;

import com.hassan.Enumeration.Role;

public record UsersRecord(
        String userName,
        String address,
        String phoneNumber,
        String email,
        String cin,
        Role role) {
}
