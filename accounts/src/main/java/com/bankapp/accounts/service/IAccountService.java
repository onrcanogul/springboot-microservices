package com.bankapp.accounts.service;
import com.bankapp.accounts.dto.AccountsDto;
import com.bankapp.accounts.dto.CustomerDto;

import java.util.List;

public interface IAccountService {
    AccountsDto fetchAccount(String mobileNumber);
    void create(CustomerDto dto);
    void update(AccountsDto dto);
    void delete(String mobileNumber);
}
