package com.hassan.Exception;

public class CarBytesImagesException extends RuntimeException {

    public CarBytesImagesException() {
        super("There is an error in the binary data of the image");
    }
}
