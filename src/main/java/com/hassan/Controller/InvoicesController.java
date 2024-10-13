package com.hassan.Controller;


import com.hassan.Record.CarsListRecord;
import com.hassan.Record.UserInvoiceCar;
import com.hassan.Service.InvoicesService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;


@AllArgsConstructor
@RestController
@RequestMapping("api/v1/invoices")
public class InvoicesController {

    private final InvoicesService invoicesService;



    @PostMapping
    public void createInvoiceForCar(@RequestBody CarsListRecord carsListRecord){
        invoicesService.createInvoiceForCar(carsListRecord.carsList());
    }


    @GetMapping("{invoiceId}")
    public UserInvoiceCar getInvoiceById(@PathVariable Long invoiceId){
        return invoicesService.getInvoiceById(invoiceId);
    }


}
