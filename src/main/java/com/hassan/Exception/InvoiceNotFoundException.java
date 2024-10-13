package com.hassan.Exception;

public class InvoiceNotFoundException extends RuntimeException {

    public InvoiceNotFoundException(Long invoiceId) {
        super("Invoice not found with ID: " + invoiceId);
    }
}
