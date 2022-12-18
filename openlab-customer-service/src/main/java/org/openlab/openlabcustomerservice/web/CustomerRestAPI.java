package org.openlab.openlabcustomerservice.web;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.openlab.openlabcustomerservice.dto.CustomerRequestDTO;
import org.openlab.openlabcustomerservice.dto.CustomerResponseDTO;
import org.openlab.openlabcustomerservice.services.CustomerService;
import org.openlab.openlabcustomerservice.services.CustomerServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class CustomerRestAPI {

    private CustomerServiceImpl customerService;

    public CustomerRestAPI(CustomerServiceImpl customerService) {
        this.customerService = customerService;
    }

    @GetMapping(path = "/customers")
    public List<CustomerResponseDTO> allCustomers(){
        return customerService.listCustomers();
    }

    @PostMapping(path = "/customers/save")
    public CustomerResponseDTO save(@RequestBody CustomerRequestDTO customerRequestDTO){
        customerRequestDTO.setId(UUID.randomUUID().toString());
        return customerService.save(customerRequestDTO);
    }

    @GetMapping(path = "/customers/{id}")
    public CustomerResponseDTO getCustomer(@PathVariable String id){

        return customerService.getCustomer(id);
    }

}
