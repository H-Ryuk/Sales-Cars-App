package com.hassan.Repo;

import com.hassan.Model.Cars;
import com.hassan.Record.SellerOfCarsRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface CarsRepo extends JpaRepository<Cars, Long> {


    @Query("select c from Cars c where mark like :mark% or modelName like :mark%")
    List<Cars> findCarByMark(String mark);


//    @Query(value = "select u.email, u.password, u.role from cars c " +
//            "join users u on c.seller = u.user_id where c.car_id = :carId", nativeQuery = true)
//    Optional<SellerOfCarsRecord> findSellerOfCar(Long carId);

    @Query("select new com.hassan.Record.SellerOfCarsRecord" +
            "(u.email, u.password, u.role) from Users u join u.carsList c where c.carId = :carId")
    Optional<SellerOfCarsRecord> findSellerOfCar(Long carId);




}
