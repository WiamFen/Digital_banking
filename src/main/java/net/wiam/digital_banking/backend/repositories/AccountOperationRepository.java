package net.wiam.digital_banking.backend.repositories;


import net.wiam.digital_banking.backend.entities.AccountOperation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountOperationRepository extends JpaRepository<AccountOperation,Long> {

}
