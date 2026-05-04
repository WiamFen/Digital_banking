package net.wiam.digital_banking.backend.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.wiam.digital_banking.backend.enums.OperationType;

import java.util.Date;

@Data @NoArgsConstructor @AllArgsConstructor

public class AccountOperation {
    private Long id;
    private Date operationDate;
    private double amount;
    private OperationType type;
    private BankAccount bankAccount;
}
