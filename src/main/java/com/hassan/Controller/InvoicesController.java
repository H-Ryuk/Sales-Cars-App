package com.hassan.Controller;


import com.hassan.Record.CarsUserRecord;
import com.hassan.Service.InvoicesService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@AllArgsConstructor
@RestController
@RequestMapping("api/v1/invoices")
public class InvoicesController {

    private final InvoicesService invoicesService;


    @PostMapping
    public void createInvoiceForCar(@RequestBody CarsUserRecord carsUserRecord){
        invoicesService.createInvoiceForCar(carsUserRecord.cars(), carsUserRecord.user());
    }




}
