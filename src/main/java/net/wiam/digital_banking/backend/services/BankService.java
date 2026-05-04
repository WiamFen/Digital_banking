package net.wiam.digital_banking.backend.services;

import net.wiam.digital_banking.backend.entities.BankAccount;
import net.wiam.digital_banking.backend.entities.CurrentAccount;
import net.wiam.digital_banking.backend.entities.SavingAccount;
import net.wiam.digital_banking.backend.repositories.BankAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class BankService {
    @Autowired
    private BankAccountRepository bankAccountRepository;
    public void consulter() {
        BankAccount bankAccount=
                bankAccountRepository.findById("14606806-e563-4866-9f5e-866fb76afe64").orElse(null);
        if(bankAccount!=null){
            System.out.println("***************");
            System.out.println(bankAccount.getId());
            System.out.println(bankAccount.getBalance());
            System.out.println(bankAccount.getStatus());
            System.out.println(bankAccount.getCreatedAt());
            System.out.println(bankAccount.getCustomer().getName());
            System.out.println(bankAccount.getClass().getSimpleName());
            if(bankAccount instanceof CurrentAccount){
                System.out.println("Over Draft => "+((CurrentAccount)bankAccount).getOverDraft());
            }else if(bankAccount instanceof SavingAccount){
                System.out.println("Rate => "+((SavingAccount)bankAccount).getInterestRate());
            }
            bankAccount.getAccountOperations().forEach(op->{
                System.out.println("=================");
                System.out.println(op.getType()+"\t"+op.getOperationDate()+"\t"+op.getAmount());
            });}
    }
}
