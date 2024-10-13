package com.hassan.Model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;




@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
@Entity
public class Cars {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long carId;
    private String mark;
    private String modelName;
    private String color;
    private Long mileage;
    private BigDecimal price;
    private int power;
    private String yearOfManufacture;



    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "car_fk")
    private List<CarsImages> carsImagesList = new ArrayList<>();


    @ManyToOne
    @JoinColumn(name = "seller")
    private Users user;



    public Cars(
            String mark,
            String modelName,
            String color,
            Long mileage,
            BigDecimal price,
            int power,
            String yearOfManufacture) {

        this.mark = mark;
        this.modelName = modelName;
        this.color = color;
        this.mileage = mileage;
        this.price = price;
        this.power = power;
        this.yearOfManufacture = yearOfManufacture;
    }



    public Cars(Long carId, BigDecimal price) {
        this.carId = carId;
        this.price = price;
    }

}
