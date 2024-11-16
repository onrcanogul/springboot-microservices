package com.bankapp.accounts.dto;
import lombok.Data;

@Data
public class AccountsDto {

    private long accountNumber;
    private String accountType;
    private String branchAddress;
}
