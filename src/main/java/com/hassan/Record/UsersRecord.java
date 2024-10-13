package com.hassan.Record;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record UsersRecord(
        @NotBlank(message = "Username shouldn't be null")
        String userName,

        @NotNull(message = "Address shouldn't be null")
        String address,

        @Pattern(regexp = "^\\d{10}$", message = "Invalid mobile number entered")
        @NotNull(message = "Phone Number shouldn't be null")
        String phoneNumber,

        @Email(message = "Invalid email address")
        @NotNull(message = "Email address shouldn't be null")
        String email,

        @NotBlank(message = "Password shouldn't be null")
        String password,

        @NotNull(message = "CIN shouldn't be null")
        String cin ) {
}
