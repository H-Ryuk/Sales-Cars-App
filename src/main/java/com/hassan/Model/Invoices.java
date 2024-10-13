package com.hassan.Model;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.hassan.Enumeration.Payment;
import com.hassan.Enumeration.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;




@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
@Entity
public class Invoices {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long invoiceId;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM-dd-yyyy")
    private LocalDate invoiceDate;
    private BigDecimal subTotal;
    private BigDecimal taxAmount;
    private BigDecimal discountAmount;
    private BigDecimal totalAmount;

    @Enumerated(EnumType.STRING)
    private Status paymentStatus;
    @Enumerated(EnumType.STRING)
    private Payment paymentMethod;


    @ManyToOne
    @JoinColumn(name = "buyer")
    private Users user;

    @ManyToMany
    @JoinTable(name = "CarsInvoices",
            joinColumns = @JoinColumn(name = "invoice_fk"),
            inverseJoinColumns = @JoinColumn(name = "car_fk")
    )
    private List<Cars> carsList = new ArrayList<>();


    public Invoices(
            BigDecimal subTotal,
            BigDecimal taxAmount,
            BigDecimal discountAmount,
            BigDecimal totalAmount,
            Status paymentStatus,
            Payment paymentMethod,
            Users user,
            List<Cars> carsList) {
        this.subTotal = subTotal;
        this.taxAmount = taxAmount;
        this.discountAmount = discountAmount;
        this.totalAmount = totalAmount;
        this.paymentStatus = paymentStatus;
        this.paymentMethod = paymentMethod;
        this.user = user;
        this.carsList = carsList;
    }

    @PrePersist
    private void onCreate() {
        this.invoiceDate = LocalDate.now();
    }

}
