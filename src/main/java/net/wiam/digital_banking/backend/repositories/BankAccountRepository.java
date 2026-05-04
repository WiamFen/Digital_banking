package net.wiam.digital_banking.backend.repositories;

import net.wiam.digital_banking.backend.entities.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankAccountRepository extends JpaRepository<BankAccount,String> {

}
