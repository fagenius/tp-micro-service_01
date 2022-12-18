package org.openlab.openlabcustomerservice;

import org.openlab.openlabcustomerservice.dto.CustomerRequestDTO;
import org.openlab.openlabcustomerservice.services.CustomerService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class OpenlabCustomerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(OpenlabCustomerServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner start(CustomerService customerService){
		return args -> {
			customerService.save(new CustomerRequestDTO("CO1", "Adria", "fayeibf@gmail.com"));
			customerService.save(new CustomerRequestDTO("CO2", "Abdou", "raouf@gmail.com"));
			customerService.save(new CustomerRequestDTO("CO3", "Camara", "camara@gmail.com"));
		};
	}
}
