package org.openlab.openlabcustomerservice.services;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.openlab.openlabcustomerservice.dto.CustomerRequestDTO;
import org.openlab.openlabcustomerservice.dto.CustomerResponseDTO;
import org.openlab.openlabcustomerservice.entities.Customer;
import org.openlab.openlabcustomerservice.mappers.CustomerMapper;
import org.openlab.openlabcustomerservice.repositories.CustomerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService{
    private CustomerRepository customerRepository;
    private CustomerMapper customerMapper;

    public CustomerServiceImpl(CustomerRepository customerRepository, CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }

    @Override
    public CustomerResponseDTO save(CustomerRequestDTO customerRequestDTO) {
        /*
        * Mapping Objet Objet
        * */
        Customer customer = customerMapper.customerRequestDTOCustomer(customerRequestDTO);

        //customer.setId(UUID.randomUUID().toString());
        Customer saveCustomer = customerRepository.save(customer);
        CustomerResponseDTO customerResponseDTO = customerMapper.customerToCustomerResponseDTO(saveCustomer);

        return customerResponseDTO;
    }

    @Override
    public CustomerResponseDTO getCustomer(String id) {
        Customer customer = customerRepository.findById(id).get();
        return customerMapper.customerToCustomerResponseDTO(customer);
    }

    @Override
    public CustomerResponseDTO update(CustomerRequestDTO customerRequestDTO) {
        Customer customer = customerMapper.customerRequestDTOCustomer(customerRequestDTO);
        Customer updateCustomer = customerRepository.save(customer);
        return customerMapper.customerToCustomerResponseDTO(updateCustomer);
    }

    @Override
    public List<CustomerResponseDTO> listCustomers() {
        List<Customer> customers = customerRepository.findAll();
        List<CustomerResponseDTO> customerResponseDTOS =
                customers.stream()
                        .map(cust->customerMapper.customerToCustomerResponseDTO(cust))
                        .collect(Collectors.toList());
        return customerResponseDTOS;
    }
}
