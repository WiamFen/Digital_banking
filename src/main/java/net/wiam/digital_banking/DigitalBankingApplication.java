package net.wiam.digital_banking;

import net.wiam.digital_banking.backend.dtos.CustomerDTO;
import net.wiam.digital_banking.backend.entities.*;
import net.wiam.digital_banking.backend.enums.AccountStatus;
import net.wiam.digital_banking.backend.enums.OperationType;
import net.wiam.digital_banking.backend.exceptions.BalanceNotSufficientException;
import net.wiam.digital_banking.backend.exceptions.BankAccountNotFoundException;
import net.wiam.digital_banking.backend.exceptions.CustomerNotFoundException;
import net.wiam.digital_banking.backend.repositories.AccountOperationRepository;
import net.wiam.digital_banking.backend.repositories.BankAccountRepository;
import net.wiam.digital_banking.backend.repositories.CustomerRepository;
import net.wiam.digital_banking.backend.services.BankAccountService;
import net.wiam.digital_banking.backend.services.BankAccountServiceImpl;
import net.wiam.digital_banking.backend.services.BankService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

@SpringBootApplication
public class DigitalBankingApplication {

	public static void main(String[] args) {
		SpringApplication.run(DigitalBankingApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(BankAccountService bankAccountService) {
		return args -> {
			Stream.of("Kamal","Imane","Jamal").forEach(name -> {
				CustomerDTO customerDTO = new CustomerDTO();
				customerDTO.setName(name);
				customerDTO.setEmail(name + "@gmail.com");
				bankAccountService.saveCustomer(customerDTO);
			});
			bankAccountService.listCustomers().forEach(customer -> {
                try {
					bankAccountService.saveCurrentBankAccount(Math.random()*90000,9000,customer.getId());
					bankAccountService.saveSavingBankAccount(Math.random()*120000,5.5,customer.getId());
					List<BankAccount> bankAccounts= bankAccountService.bankAccountList();
					for(BankAccount bankAccount:bankAccounts){
						for(int i=0;i<2;i++) {
							bankAccountService.credit(bankAccount.getId(),1000+Math.random()*12000,"credit");
							bankAccountService.debit(bankAccount.getId(),1000+Math.random()*9000,"debit");
						}
					}
				} catch (CustomerNotFoundException e) {
                    e.printStackTrace();
                } catch (BankAccountNotFoundException | BalanceNotSufficientException e) {
					e.printStackTrace();
                }
            });
		};
	}

//	@Bean
//	CommandLineRunner commandLineRunner(BankService bankService) {
//		return args -> {
//			bankService.consulter();
//		};
//	}

	//@Bean
	CommandLineRunner start(CustomerRepository customerRepository,
							BankAccountRepository bankAccountRepository,
							AccountOperationRepository accountOperationRepository) {
		return args -> {
			Stream.of("Laila","Yassine","Hassan").forEach(name -> {
				Customer customer = new Customer();
				customer.setName(name);
				customer.setEmail(name + "@gmail.com");
				customerRepository.save(customer);
			});
			customerRepository.findAll().forEach(cust->{
				CurrentAccount currentAccount = new CurrentAccount();
				currentAccount.setId(UUID.randomUUID().toString());
				currentAccount.setBalance(Math.random()*90000);
				currentAccount.setCreatedAt(new Date());
				currentAccount.setStatus(AccountStatus.CREATED);
				currentAccount.setCustomer(cust);
				currentAccount.setOverDraft(9000);
				bankAccountRepository.save(currentAccount);

				SavingAccount savingAccount = new SavingAccount();
				savingAccount.setId(UUID.randomUUID().toString());
				savingAccount.setBalance(Math.random()*90000);
				savingAccount.setCreatedAt(new Date());
				savingAccount.setStatus(AccountStatus.CREATED);
				savingAccount.setCustomer(cust);
				savingAccount.setInterestRate(5.5);
				bankAccountRepository.save(savingAccount);
			});

			bankAccountRepository.findAll().forEach(acc->{
				for (int i=0;i<10;i++){
					AccountOperation account0peration = new AccountOperation();
					account0peration.setOperationDate(new Date());
					account0peration.setAmount(Math.random()*12000);
					account0peration.setType(Math.random()>0.5? OperationType.DEBIT:OperationType.CREDIT);
					account0peration.setBankAccount(acc);
					accountOperationRepository.save(account0peration);
				}
			});
		};
	}

}
