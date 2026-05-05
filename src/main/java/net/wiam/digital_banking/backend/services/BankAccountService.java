package net.wiam.digital_banking.backend.services;

import net.wiam.digital_banking.backend.entities.BankAccount;
import net.wiam.digital_banking.backend.entities.CurrentAccount;
import net.wiam.digital_banking.backend.entities.Customer;
import net.wiam.digital_banking.backend.entities.SavingAccount;
import net.wiam.digital_banking.backend.exceptions.BalanceNotSufficientException;
import net.wiam.digital_banking.backend.exceptions.BankAccountNotFoundException;
import net.wiam.digital_banking.backend.exceptions.CustomerNotFoundException;

import java.util.List;

public interface BankAccountService {
    Customer saveCustomer(Customer customer);
    CurrentAccount saveCurrentBankAccount(double initialBalance, double overDraft, Long customerId ) throws CustomerNotFoundException;
    SavingAccount saveSavingBankAccount(double initialBalance, double interestRate, Long customerId) throws CustomerNotFoundException;
    List<Customer> listCustomers();
    BankAccount getBankAccount(String accountId) throws BankAccountNotFoundException;
    void debit(String accountId, double amount, String description) throws BankAccountNotFoundException, BalanceNotSufficientException;
    void credit(String accountId, double amount, String description) throws BankAccountNotFoundException;
    void transfer(String accountIdSource,String accountIdDestination ,double amount) throws BankAccountNotFoundException, BalanceNotSufficientException;

    List<BankAccount> bankAccountList();
}
