package net.wiam.digital_banking.backend.dtos;

import lombok.Data;


@Data
public class CustomerDTO {
    private Long id;
    private String name;
    private String email;
}
