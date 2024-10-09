package com.hassan.Exception;

public class UserNotCompatibleWithSeller extends RuntimeException {

    public UserNotCompatibleWithSeller() {
        super("The user is not compatible with the seller of this vehicle and therefore you cannot update it");
    }
}
