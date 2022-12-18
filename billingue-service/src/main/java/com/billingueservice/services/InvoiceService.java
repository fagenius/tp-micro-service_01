package com.billingueservice.services;

import com.billingueservice.dto.InvoiceRequestDTO;
import com.billingueservice.dto.InvoiceResponseDTO;

import java.util.List;


public interface InvoiceService {
    InvoiceResponseDTO save(InvoiceRequestDTO invoiceRequestDTO);
    InvoiceResponseDTO getInvoice(String id);
    List<InvoiceResponseDTO> invoicesByCustomerId(String customerId);
    List<InvoiceResponseDTO> allInvoices();
}
