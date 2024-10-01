package com.hassan.Service;


import com.hassan.Model.Cars;
import com.hassan.Model.CarsImages;
import com.hassan.Repo.CarsImagesRepo;
import com.hassan.Repo.CarsRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;



@Service
@AllArgsConstructor
public class CarsService {


    private CarsRepo carsRepo;
    private CarsImagesRepo carsImagesRepo;



    public void addNewCar(Cars car, List<MultipartFile> images) {
        List<CarsImages> carsImagesList = new ArrayList<>();

        try {
            for (MultipartFile file : images) {
                CarsImages carsImages = new CarsImages();

                carsImages.setImageName(file.getOriginalFilename());
                carsImages.setImageType(file.getContentType());
                carsImages.setImageData(file.getBytes());

                carsImagesList.add(carsImages);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        car.setCarsImagesList(carsImagesList);
        carsRepo.save(car);
    }




    public List<Cars> findAll() {
        return carsRepo.findAll();
    }




    public CarsImages image(Long imageId) {
        return carsImagesRepo.findById(imageId)
                .orElse(null);
    }




    public void updateCarFields(Cars car, List<MultipartFile> images) {
        carsRepo.findById(car.getCarId())
                .ifPresentOrElse(cars -> {
                            cars.setMark(car.getMark());
                            cars.setColor(car.getColor());
                            cars.setMileage(car.getMileage());
                            cars.setModelName(car.getModelName());
                            cars.setPower(car.getPower());
                            cars.setPrice(car.getPrice());
                            cars.setYearOfManufacture(car.getYearOfManufacture());
                            cars.getCarsImagesList().clear();

                            for (MultipartFile file : images) {
                                CarsImages carsImages = new CarsImages();
                                carsImages.setImageName(file.getOriginalFilename());
                                carsImages.setImageType(file.getContentType());
                                try {
                                    carsImages.setImageData(file.getBytes());
                                } catch (IOException e) { throw new RuntimeException(e); }
                                cars.getCarsImagesList().add(carsImages);
                            }

                            cars.setCarsImagesList(cars.getCarsImagesList());

                            carsRepo.save(cars);
                        },
                        () -> {
                        }); // create a custom exception
    }



    public void deleteCarById(Long carId) {
        carsRepo.deleteById(carId);
    }

}
