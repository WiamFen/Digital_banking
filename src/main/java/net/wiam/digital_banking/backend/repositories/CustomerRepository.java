package net.wiam.digital_banking.backend.repositories;

import net.wiam.digital_banking.backend.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,Long> {

}
