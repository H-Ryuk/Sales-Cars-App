package com.hassan.Record;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserLogInRecord(
        @Email(message = "Invalid email address")
        String email,
        @NotBlank(message = "password should not be null")
        String password ) {
}
