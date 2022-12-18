package com.billingueservice.services;

import com.billingueservice.dto.InvoiceRequestDTO;
import com.billingueservice.dto.InvoiceResponseDTO;
import com.billingueservice.entities.Customer;
import com.billingueservice.entities.Invoice;
import com.billingueservice.exceptions.CustomerNotFoundException;
import com.billingueservice.mappers.InvoiceMapper;
import com.billingueservice.openfeign.CustomerRestClient;
import com.billingueservice.repositories.InvoiceRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class InvoiceServiceImpl implements InvoiceService{

    private InvoiceRepository invoiceRepository;
    private InvoiceMapper invoiceMapper;
    private CustomerRestClient customerRestClient;

    public InvoiceServiceImpl(InvoiceRepository invoiceRepository, InvoiceMapper invoiceMapper,CustomerRestClient customerRestClient) {
        this.invoiceRepository = invoiceRepository;
        this.invoiceMapper = invoiceMapper;
        this.customerRestClient = customerRestClient;
    }

    @Override
    public InvoiceResponseDTO save(InvoiceRequestDTO invoiceRequestDTO) {
        Customer customer = null;
        try{
            //Vérifier l'intégrité référentielle.. Invoice/Customer
            customer = customerRestClient.getCustomer(invoiceRequestDTO.getCustomerId());
        }catch (Exception e){
            throw new CustomerNotFoundException("Customer not found");
        }

        Invoice invoice = invoiceMapper.fromInvoiceRequestDTOToInvoice(invoiceRequestDTO);
        invoice.setId(UUID.randomUUID().toString());
        invoice.setDate(new Date());
        Invoice saveInvoice = invoiceRepository.save(invoice);
        saveInvoice.setCustomer(customer);
        return invoiceMapper.fromInvoiceToInvoiceResponseDTO(saveInvoice);
    }

    @Override
    public InvoiceResponseDTO getInvoice(String id) {
        Invoice invoice = invoiceRepository.findById(id).get();
        Customer customer = customerRestClient.getCustomer(invoice.getCustomerId());
        invoice.setCustomer(customer);
        return invoiceMapper.fromInvoiceToInvoiceResponseDTO(invoice);
    }

    @Override
    public List<InvoiceResponseDTO> invoicesByCustomerId(String customerId) {
        List<Invoice> invoices = invoiceRepository.findByCustomerId(customerId);
        for(Invoice invoice:invoices){
            Customer customer = customerRestClient.getCustomer(invoice.getCustomerId());
            invoice.setCustomer(customer);
        }
        return invoices.stream()
                .map(invoice->invoiceMapper.fromInvoiceToInvoiceResponseDTO(invoice))
                .collect(Collectors.toList());
    }

    @Override
    public List<InvoiceResponseDTO> allInvoices() {
        List<Invoice> invoices = invoiceRepository.findAll();
        /*invoices.forEach(inv->{
            Customer customer = customerRestClient.getCustomer(inv.getCustomerId());
            inv.setCustomer(customer);
        });*/
        for(Invoice invoice:invoices){
            Customer customer = customerRestClient.getCustomer(invoice.getCustomerId());
            invoice.setCustomer(customer);
        }
        return invoices.stream()
                .map(invoice -> invoiceMapper.fromInvoiceToInvoiceResponseDTO(invoice))
                .collect(Collectors.toList());
    }
}
