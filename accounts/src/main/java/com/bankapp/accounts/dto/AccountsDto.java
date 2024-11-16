package com.bankapp.accounts.dto;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class AccountsDto {
    @NotEmpty
    @Pattern(regexp = "(^$|[0-9]{10})")
    private long accountNumber;
    @NotEmpty
    private String accountType;
    @NotEmpty
    private String branchAddress;
    private CustomerDto customer;
}
