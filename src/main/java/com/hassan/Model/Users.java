package com.hassan.Model;

import com.hassan.Enumeration.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;




@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
@Entity
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    private String userName;
    private String address;
    private String phoneNumber;
    @Column(unique = true)
    private String email;
    private String password;
    private String cin;

    @Enumerated(EnumType.STRING)
    private Role role;




    @OneToMany(mappedBy = "user")
    private List<Invoices> invoicesList = new ArrayList<>();


    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "user")
    private List<Cars> carsList = new ArrayList<>();


}
