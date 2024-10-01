package com.hassan.Model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;



@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
@Entity
public class CarsImages {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long imageId;
    private String imageName;
    private String imageType;
    @Lob
    @Column(columnDefinition = "mediumblob")
    private byte[] imageData;
}
