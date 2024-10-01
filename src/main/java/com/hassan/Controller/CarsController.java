package com.hassan.Controller;


import com.hassan.Model.Cars;
import com.hassan.Model.CarsImages;
import com.hassan.Service.CarsService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@RestController
@RequestMapping("api/v1/cars")
@AllArgsConstructor
public class CarsController {


    private CarsService carsService;



    @PostMapping
    public void addNewCar(@RequestPart Cars car, @RequestPart List<MultipartFile> images){
        carsService.addNewCar(car,images);
    }

    @GetMapping
    public List<Cars> findAll(){
        return carsService.findAll();
    }


    @GetMapping("images/{imageId}")
    public ResponseEntity<byte[]> getImageByCarsImagesId(@PathVariable Long imageId){
       CarsImages img = carsService.image(imageId);
       HttpHeaders headers = new HttpHeaders();
       headers.set(HttpHeaders.CONTENT_TYPE, img.getImageType());
        return new ResponseEntity<>(img.getImageData(), headers, HttpStatus.OK);
    }


    @PutMapping
    public void updateCarFields(@RequestPart Cars car, @RequestPart List<MultipartFile> images){
        carsService.updateCarFields(car, images);
    }


    @DeleteMapping
    public void deleteCarById(@RequestParam Long carId){
        carsService.deleteCarById(carId);
    }


}
