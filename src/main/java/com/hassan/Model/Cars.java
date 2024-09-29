package com.hassan.Model;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
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
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM-dd-yyyy")
    private Date year;


    @ManyToMany(mappedBy = "carsList")
    private List<Invoices> invoicesList = new ArrayList<>();


    @OneToMany
    @JoinColumn(name = "car_fk")
    private List<CarsImages> carsImagesList = new ArrayList<>();
}
