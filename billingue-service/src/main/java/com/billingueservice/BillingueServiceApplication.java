package com.billingueservice;

import com.billingueservice.dto.InvoiceRequestDTO;
import com.billingueservice.services.InvoiceService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;

@SpringBootApplication
@EnableFeignClients //Activation de FeignClients
public class BillingueServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BillingueServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(InvoiceService invoiceService){
        return args -> {
            invoiceService.save(new InvoiceRequestDTO(BigDecimal.valueOf(98000),"CO1"));
            invoiceService.save(new InvoiceRequestDTO(BigDecimal.valueOf(50000),"CO2"));
            invoiceService.save(new InvoiceRequestDTO(BigDecimal.valueOf(10000),"CO1"));
        };
    }
}
