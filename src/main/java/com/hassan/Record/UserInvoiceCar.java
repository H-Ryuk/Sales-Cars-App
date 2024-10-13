package com.hassan.Record;

import com.hassan.Enumeration.Payment;
import com.hassan.Enumeration.Status;
import com.hassan.Model.Cars;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;


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
        List<CarsRecord> carsList
) {
}
