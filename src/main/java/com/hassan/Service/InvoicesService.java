package com.hassan.Service;

import com.hassan.Enumeration.Payment;
import com.hassan.Enumeration.Status;
import com.hassan.Exception.InvoiceNotFoundException;
import com.hassan.Exception.TargetNotFoundException;
import com.hassan.Model.Cars;
import com.hassan.Model.Invoices;
import com.hassan.Model.Users;
import com.hassan.Record.CarsRecord;
import com.hassan.Record.UserInvoiceCar;
import com.hassan.Repo.InvoicesRepo;
import com.hassan.Repo.UsersRepo;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


@AllArgsConstructor
@Service
public class InvoicesService {


    private final InvoicesRepo invoicesRepo;
    private final UsersRepo usersRepo;

    private final double taxPercentage = 20;
    private final double discountRate = 10;



    public void createInvoiceForCar(List<Cars> carsList) {
        List<Invoices> invoicesList = new ArrayList<>();

        BigDecimal totalCarPrice = BigDecimal.valueOf(0);

        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Users user = usersRepo.findByEmail(email)
                .orElseThrow(() -> new TargetNotFoundException(email));


        for (Cars car : carsList) {
            totalCarPrice = totalCarPrice.add(car.getPrice());
        }

        BigDecimal taxAmount = totalCarPrice.multiply(BigDecimal.valueOf(taxPercentage / 100));
        BigDecimal discountAmount = totalCarPrice.multiply(BigDecimal.valueOf(discountRate / 100));
        BigDecimal totalAmount = totalCarPrice.add(taxAmount);

        Invoices invoices =
                new Invoices(
                        totalCarPrice, // sub total
                        taxAmount, // tax amount
                        discountAmount, // discount amount
                        totalAmount, // total amount
                        Status.PAID, // invoice's payment status
                        Payment.CASH, // invoice's payment method
                        user, // user (buyer)
                        carsList // list of cars
                );

        invoicesList.add(invoices);

        invoicesRepo.saveAll(invoicesList);
    }




    public UserInvoiceCar getInvoiceById(Long invoiceId) {
        List<CarsRecord> carsList = invoicesRepo.getCarsByInvoiceId(invoiceId);
        return invoicesRepo.getInvoiceById(invoiceId)
                .map(userInvoiceCar -> new UserInvoiceCar(
                        userInvoiceCar.invoiceDta(),
                        userInvoiceCar.subTotal(),
                        userInvoiceCar.taxAmount(),
                        userInvoiceCar.discountAmount(),
                        userInvoiceCar.totalAmount(),
                        userInvoiceCar.paymentStatus(),
                        userInvoiceCar.paymentMethod(),
                        userInvoiceCar.userName(),
                        userInvoiceCar.address(),
                        userInvoiceCar.phoneNumber(),
                        userInvoiceCar.email(),
                        userInvoiceCar.cin(),
                        carsList
                ))
                .orElseThrow(() -> new InvoiceNotFoundException(invoiceId));
    }




}
