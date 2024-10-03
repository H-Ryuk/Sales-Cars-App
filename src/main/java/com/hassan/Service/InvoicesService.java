package com.hassan.Service;

import com.hassan.Enumeration.Payment;
import com.hassan.Enumeration.Status;
import com.hassan.Model.Cars;
import com.hassan.Model.Invoices;
import com.hassan.Model.Users;
import com.hassan.Record.UserInvoiceCar;
import com.hassan.Repo.InvoicesRepo;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;



@Service
public class InvoicesService {


    private final InvoicesRepo invoicesRepo;

    private final double taxPercentage = 20;
    private final double discountRate = 10;


    public InvoicesService(InvoicesRepo invoicesRepo) {
        this.invoicesRepo = invoicesRepo;
    }


    public void createInvoiceForCar(List<Cars> cars, Users user) {
        List<Invoices> invoicesList = new ArrayList<>();

        BigDecimal totalCarPrice = BigDecimal.valueOf(0);

        for (Cars car : cars) {
            totalCarPrice = totalCarPrice.add(car.getPrice());
        }

        BigDecimal taxAmount = totalCarPrice.multiply(BigDecimal.valueOf(taxPercentage / 100));
        BigDecimal discountAmount = totalCarPrice.multiply(BigDecimal.valueOf(discountRate / 100));
        BigDecimal totalAmount = totalCarPrice.add(taxAmount);

        Invoices invoices =
                new Invoices(
                        LocalDate.now(), // invoice creation date
                        totalCarPrice, // sub total
                        taxAmount, // tax amount
                        discountAmount, // discount amount
                        totalAmount, // total amount
                        Status.PAID, // invoice's payment status
                        Payment.CASH, // invoice's payment method
                        user, // user (buyer)
                        cars // list of cars
                );

        invoicesList.add(invoices);

        invoicesRepo.saveAll(invoicesList);
    }



    public List<UserInvoiceCar> getInvoiceById(Long invoiceId) {
        return invoicesRepo.getCars(invoiceId);
    }




}
