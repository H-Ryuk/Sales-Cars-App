package com.hassan.Record;

import com.hassan.Enumeration.Payment;
import com.hassan.Enumeration.Status;

import java.math.BigDecimal;
import java.time.LocalDate;

public record InvoicesRecord(
        LocalDate date,
        BigDecimal subTotal,
        BigDecimal taxAmount,
        BigDecimal discountAmount,
        BigDecimal totalAmount,
        Status paymentStatus,
        Payment paymentMethod) {
}
