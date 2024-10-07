package com.hassan.Exception;

public class TargetNotFoundException extends RuntimeException{

    public TargetNotFoundException(String username) {
        super("User not found with this name " + username);
    }
}
