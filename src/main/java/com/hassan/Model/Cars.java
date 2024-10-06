package com.hassan.Model;


import jakarta.persistence.*;
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

}
