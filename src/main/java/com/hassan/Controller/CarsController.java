package com.hassan.Controller;


import com.hassan.Model.Cars;
import com.hassan.Model.CarsImages;
import com.hassan.Record.CarRecord;
import com.hassan.Service.CarsService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@RestController
@RequestMapping("api/v1/cars")
@AllArgsConstructor
public class CarsController {


    private final CarsService carsService;



    @PostMapping
    public ResponseEntity<String> addNewCar(@RequestPart @Valid CarRecord car, @RequestPart List<MultipartFile> images){
        carsService.addNewCar(car,images);
        return new ResponseEntity<>("New car added successfully", HttpStatus.CREATED);
    }



    @GetMapping
    public List<Cars> findAll(){
        return carsService.findAll();
    }



    @GetMapping("{mark}")
    public List<Cars> findCarsByMark(@PathVariable String mark){
        return carsService.findCarByMark(mark);
    }



    @PutMapping("{carId}")
    public void updateCarFields(@AuthenticationPrincipal UserDetails userDetails,
                                @PathVariable Long carId,
                                @RequestPart @Valid CarRecord car,
                                @RequestPart List<MultipartFile> images){
        carsService.updateCarFields(userDetails, carId, car, images);
    }



    @DeleteMapping("{carId}")
    public void deleteCarById(@PathVariable Long carId){
        carsService.deleteCarById(carId);
    }


}
