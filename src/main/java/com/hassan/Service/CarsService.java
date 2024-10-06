package com.hassan.Service;


import com.hassan.Enumeration.Role;
import com.hassan.Model.Cars;
import com.hassan.Model.CarsImages;
import com.hassan.Record.SellerOfCarsRecord;
import com.hassan.Repo.CarsImagesRepo;
import com.hassan.Repo.CarsRepo;
import com.hassan.Repo.UsersRepo;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@Service
@AllArgsConstructor
public class CarsService {


    private final CarsRepo carsRepo;
    private final UsersRepo usersRepo;
    private final CarsImagesRepo carsImagesRepo;




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




    public void updateCarFields(UserDetails userDetails, Long carId, Cars car, List<MultipartFile> images) {

        if (userRightsOfUpdate(userDetails, carId)) {
            carsRepo.findById(carId)
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
                                    CarsImages carsImages = getCarsImages(file);
                                    cars.getCarsImagesList().add(carsImages);
                                }

                                cars.setCarsImagesList(cars.getCarsImagesList());

                                carsRepo.save(cars);
                            },
                            () -> {
                            }); // create a custom exception
        }

    }




    private CarsImages getCarsImages(MultipartFile file) {
        CarsImages carsImages = new CarsImages();
        carsImages.setImageName(file.getOriginalFilename());
        carsImages.setImageType(file.getContentType());
        try {
            carsImages.setImageData(file.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return carsImages;
    }



    private boolean userRightsOfUpdate(UserDetails userDetails, Long carId) {
        SellerOfCarsRecord sellerOfCarsRecord = carsRepo.findSellerOfCar(carId)
                .orElse(null); // handle exception

        assert sellerOfCarsRecord != null;
        return userDetails.getUsername().equals(sellerOfCarsRecord.email()) &&
                userDetails.getPassword().equals(sellerOfCarsRecord.password());
    }




    public void deleteCarById(Long carId) {
        carsRepo.deleteById(carId);
    }



    public List<Cars> findCarByMark(String mark) {
        return carsRepo.findCarByMark(mark);
    }


}
