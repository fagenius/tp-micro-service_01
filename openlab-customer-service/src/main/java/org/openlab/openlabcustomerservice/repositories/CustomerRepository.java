package org.openlab.openlabcustomerservice.repositories;

import org.openlab.openlabcustomerservice.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, String> {
}
