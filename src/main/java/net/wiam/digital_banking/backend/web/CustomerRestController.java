package net.wiam.digital_banking.backend.web;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.wiam.digital_banking.backend.entities.Customer;
import net.wiam.digital_banking.backend.services.BankAccountService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@Slf4j
public class CustomerRestController {
    private BankAccountService bankAccountService;
    @GetMapping("/customers")
    public List<Customer> customers(){
        return bankAccountService.listCustomers();
    }
}
