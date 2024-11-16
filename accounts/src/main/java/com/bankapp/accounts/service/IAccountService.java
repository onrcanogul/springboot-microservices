package com.bankapp.accounts.service;
import com.bankapp.accounts.dto.CustomerDto;

public interface IAccountService {
    void create(CustomerDto dto);
}
