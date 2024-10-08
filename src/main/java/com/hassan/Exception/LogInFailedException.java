package com.hassan.Exception;

public class LogInFailedException extends RuntimeException{

    public LogInFailedException(String message) {
        super("SignIn failed with this email: " + message);
    }
}
