package com.bankapp.accounts.mapper;

import com.bankapp.accounts.dto.AccountsDto;
import com.bankapp.accounts.entity.Accounts;

public class AccountsMapper {
    public static AccountsDto toDto(Accounts accounts, AccountsDto dto) {
        dto.setAccountNumber(accounts.getAccountNumber());
        dto.setAccountType(accounts.getAccountType());
        dto.setBranchAddress(accounts.getBranchAddress());
        return dto;
    }

    public static Accounts toEntity(AccountsDto dto, Accounts accounts) {
        accounts.setAccountNumber(dto.getAccountNumber());
        accounts.setAccountType(dto.getAccountType());
        accounts.setBranchAddress(dto.getBranchAddress());
        return accounts;
    }
}
