package com.hassan.Service;


import com.hassan.Exception.*;
import com.hassan.Model.Cars;
import com.hassan.Model.CarsImages;
import com.hassan.Model.Users;
import com.hassan.Record.CarRecordValidator;
import com.hassan.Record.CarsRecord;
import com.hassan.Record.SellerOfCarsRecord;
import com.hassan.Repo.CarsRepo;
import com.hassan.Repo.UsersRepo;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.core.context.SecurityContextHolder;
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



    public void addNewCar(CarRecordValidator carRecord, List<MultipartFile> images) {
        List<CarsImages> carsImagesList = new ArrayList<>();
        String userEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        Users user = usersRepo.findByEmail(userEmail)
                .orElseThrow(() -> new TargetNotFoundException(userEmail));

        try {
            for (MultipartFile file : images) {
                CarsImages carsImages = new CarsImages();

                carsImages.setImageName(file.getOriginalFilename());
                carsImages.setImageType(file.getContentType());
                carsImages.setImageData(file.getBytes());

                carsImagesList.add(carsImages);
            }
        } catch (IOException e) { throw new RuntimeException(e); }

        Cars car = convertCarRecordToCars(carRecord);
        car.setUser(user);

        car.setCarsImagesList(carsImagesList);
        if(!car.getCarsImagesList().isEmpty()){
            carsRepo.save(car);
        }else {
            throw new CarImageListIsEmpty(car.getMark());
        }
    }



    private Cars convertCarRecordToCars(CarRecordValidator carRecord){
        return new Cars(
                carRecord.mark(),
                carRecord.modelName(),
                carRecord.color(),
                carRecord.mileage(),
                carRecord.price(),
                carRecord.power(),
                carRecord.yearOfManufacture()
        );
    }




    public List<CarsRecord> findAll() {
        return carsRepo.findAllCars();
    }




    public void updateCarFields(UserDetails userDetails, Long carId, CarRecordValidator carRecord, List<MultipartFile> images) {

        if (userRightsOfUpdate(userDetails, carId)) {
            System.out.println("user part");
            carsRepo.findById(carId)
                    .ifPresentOrElse(cars -> {
                                cars.setMark(carRecord.mark());
                                cars.setColor(carRecord.color());
                                cars.setMileage(carRecord.mileage());
                                cars.setModelName(carRecord.modelName());
                                cars.setPower(carRecord.power());
                                cars.setPrice(carRecord.price());
                                cars.setYearOfManufacture(carRecord.yearOfManufacture());
                                cars.getCarsImagesList().clear();

                                for (MultipartFile file : images) {
                                    CarsImages carsImages = getCarsImages(file);
                                    cars.getCarsImagesList().add(carsImages);
                                }

                                cars.setCarsImagesList(cars.getCarsImagesList());

                                carsRepo.save(cars);
                                System.out.println("car part");
                            },
                            () -> {
                                throw new CarNotFoundException(carId);
                            });
        }

    }


    private CarsImages getCarsImages(MultipartFile file) {
        CarsImages carsImages = new CarsImages();
        carsImages.setImageName(file.getOriginalFilename()); // this is where we set the name of image
        carsImages.setImageType(file.getContentType());      // this is where we set the type of image png,jpeg ...
        try {
            carsImages.setImageData(file.getBytes());        // this is where we set the data of image bytes
        } catch (IOException e) {
            throw new CarBytesImagesException();
        }

        return carsImages;
    }



    private boolean userRightsOfUpdate(UserDetails userDetails, Long carId) {
        SellerOfCarsRecord sellerOfCarsRecord = carsRepo.findSellerOfCar(carId)
                .orElseThrow(() -> new CarNotFoundException(carId));

        if (userDetails.getUsername().equals(sellerOfCarsRecord.email()) &&
                userDetails.getPassword().equals(sellerOfCarsRecord.password())){
            return true;
        }else {
            throw new UserNotCompatibleWithSeller();
        }
    }



    public void deleteCarById(Long carId) {
        carsRepo.findById(carId)
                .ifPresentOrElse(cars -> {
                            carsRepo.deleteById(carId);
                        },
                        () -> {
                            throw new CarNotFoundException(carId);
                        });
    }



    public List<CarsRecord> findCarByMark(String mark) {
        List<CarsRecord> recordList = carsRepo.findCarByMark(mark);
        if(recordList.isEmpty()){
            throw new CarNotFoundException();
        }
        return recordList;
    }


}
