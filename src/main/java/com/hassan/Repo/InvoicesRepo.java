package com.hassan.Repo;

import com.hassan.Model.Invoices;
import com.hassan.Record.UserInvoiceCar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface InvoicesRepo extends JpaRepository<Invoices, Long> {


    @Query("select new com.hassan.Record.UserInvoiceCar" +
            "(i.invoiceDate, " +
            " i.subTotal, i.taxAmount, i.discountAmount, i.totalAmount, i.paymentStatus, i.paymentMethod," +
            " u.userName, u.address, u.phoneNumber, u.email, u.cin," +
            " c.mark, c.modelName, c.color, c.mileage, c.price, c.power, c.yearOfManufacture) " +
            "from Invoices i join i.user u " +
            "join i.carsList c " +
            "where i.invoiceId = :invoiceId")
    List<UserInvoiceCar> getCars(Long invoiceId);



}
