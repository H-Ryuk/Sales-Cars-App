package com.hassan.Controller;


import com.hassan.Record.CarsUserRecord;
import com.hassan.Record.UserInvoiceCar;
import com.hassan.Service.InvoicesService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@AllArgsConstructor
@RestController
@RequestMapping("api/v1/invoices")
public class InvoicesController {

    private final InvoicesService invoicesService;



    @PostMapping
    public void createInvoiceForCar(@RequestBody CarsUserRecord carsUserRecord){
        invoicesService.createInvoiceForCar(carsUserRecord.cars(), carsUserRecord.user());
    }


    @GetMapping("{invoiceId}")
    public List<UserInvoiceCar> getInvoiceById(@PathVariable Long invoiceId){
        return invoicesService.getInvoiceById(invoiceId);
    }


}
