package com.hassan.Repo;

import com.hassan.Model.Cars;
import com.hassan.Record.SellerOfCarsRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface CarsRepo extends JpaRepository<Cars, Long> {


    @Query("select c from Cars c where mark like :mark% or modelName like :mark%")
    List<Cars> findCarByMark(String mark);



    @Query("select new com.hassan.Record.SellerOfCarsRecord" +
            "(u.email, u.password, u.role) from Users u join u.carsList c where c.carId = :carId")
    Optional<SellerOfCarsRecord> findSellerOfCar(Long carId);


    @Modifying
    @Query(value = "delete from cars where car_id = :carId", nativeQuery = true)
    int deleteCarById(Long carId);


}
