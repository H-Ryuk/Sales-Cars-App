package com.hassan.Exception;

public class CarImageListIsEmpty extends RuntimeException{


    public CarImageListIsEmpty(String mark) {
        super("Add some images for this car: " + mark);
    }
}
