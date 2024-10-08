package com.hassan.Record;

import com.hassan.Enumeration.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record UserRegisterRecord(
        @NotBlank(message = "Username shouldn't be null")
        String userName,

        @NotNull(message = "Address shouldn't be null")
        String address,

        @Pattern(regexp = "^\\d{10}$", message = "Invalid mobile number entered")
        String phoneNumber,

        @Email(message = "Invalid email address")
        String email,

        @NotBlank(message = "Password shouldn't be null")
        String password,

        @NotNull(message = "CIN shouldn't be null")
        String cin,

        @NotNull(message = "Role shouldn't be null")
        Role role ) {
}
