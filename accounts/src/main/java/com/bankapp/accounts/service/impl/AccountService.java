package com.bankapp.accounts.service.impl;

import com.bankapp.accounts.dto.CustomerDto;
import com.bankapp.accounts.repository.AccountRepository;
import com.bankapp.accounts.repository.CustomerRepository;
import com.bankapp.accounts.service.IAccountService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AccountService implements IAccountService {
    private AccountRepository accountRepository;
    private CustomerRepository customerRepository;

    @Override
    public void create(CustomerDto dto) {

    }
}
