package com.billingueservice.mappers;

import com.billingueservice.dto.InvoiceRequestDTO;
import com.billingueservice.dto.InvoiceResponseDTO;
import com.billingueservice.entities.Invoice;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface InvoiceMapper {
    Invoice fromInvoiceRequestDTOToInvoice(InvoiceRequestDTO invoiceRequestDTO);
    InvoiceResponseDTO fromInvoiceToInvoiceResponseDTO(Invoice invoice);
}
