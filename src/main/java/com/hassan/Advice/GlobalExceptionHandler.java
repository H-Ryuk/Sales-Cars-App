package com.hassan.Advice;


import com.hassan.Exception.CarImageListIsEmpty;
import com.hassan.Exception.CarNotFoundException;
import com.hassan.Exception.TargetNotFoundException;
import com.hassan.Exception.UserNotCompatibleWithSeller;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleInvalidArgument(MethodArgumentNotValidException ex){
        Map<String, String> errorMap = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(fieldError -> {
            errorMap.put(fieldError.getField(), fieldError.getDefaultMessage());
        });
        return new ResponseEntity<>(errorMap, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(TargetNotFoundException.class)
    public ResponseEntity<String> handleUserNotFoundException(TargetNotFoundException ex){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<String> handleLoginFailedException(){
        return new ResponseEntity<>("SignIn failed", HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(CarImageListIsEmpty.class)
    public ResponseEntity<String> handleCarImageListIsEmpty(CarImageListIsEmpty ex){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(CarNotFoundException.class)
    public ResponseEntity<String> handleCarImageListIsEmpty(CarNotFoundException ex){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(UserNotCompatibleWithSeller.class)
    public ResponseEntity<String> handleCarImageListIsEmpty(UserNotCompatibleWithSeller ex){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }


}
