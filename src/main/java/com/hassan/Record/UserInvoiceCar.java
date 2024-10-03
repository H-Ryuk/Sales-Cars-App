package com.hassan.Record;

import com.hassan.Enumeration.Payment;
import com.hassan.Enumeration.Status;


import java.math.BigDecimal;
import java.time.LocalDate;


public record UserInvoiceCar(
        LocalDate invoiceDta,
        BigDecimal subTotal,
        BigDecimal taxAmount,
        BigDecimal discountAmount,
        BigDecimal totalAmount,
        Status paymentStatus,
        Payment paymentMethod,
        String userName,
        String address,
        String phoneNumber,
        String email,
        String cin,
        String mark,
        String modelName,
        String color,
        Long mileage,
        BigDecimal price,
        int power,
        String yearOfManufacture ) {
}
