package com.bankapp.accounts.service.impl;

import com.bankapp.accounts.constants.AccountsConstants;
import com.bankapp.accounts.dto.AccountsDto;
import com.bankapp.accounts.dto.CustomerDto;
import com.bankapp.accounts.entity.Accounts;
import com.bankapp.accounts.entity.Customer;
import com.bankapp.accounts.exception.CustomerAlreadyExistException;
import com.bankapp.accounts.exception.ResourceNotFoundException;
import com.bankapp.accounts.mapper.AccountsMapper;
import com.bankapp.accounts.mapper.CustomerMapper;
import com.bankapp.accounts.repository.AccountRepository;
import com.bankapp.accounts.repository.CustomerRepository;
import com.bankapp.accounts.service.IAccountService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class AccountService implements IAccountService {
    private AccountRepository accountRepository;
    private CustomerRepository customerRepository;

    @Override
    public AccountsDto fetchAccount(String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(() -> new ResourceNotFoundException("Customer not found"));
        Accounts account = accountRepository.findByCustomerId(customer.getId()).orElseThrow(() -> new ResourceNotFoundException("Account not found"));
        AccountsDto dto = AccountsMapper.toDto(account, new AccountsDto());
        dto.setCustomer(CustomerMapper.toDto(customer, new CustomerDto()));
        return dto;
    }

    @Override
    public void create(CustomerDto dto) {
        Customer customer = CustomerMapper.toEntity(new Customer(), dto);
        Optional<Customer> optional = customerRepository.findByMobileNumber(dto.getMobileNumber());
        if(optional.isPresent()){
            throw new CustomerAlreadyExistException("Customer already exist with mobile number: "+dto.getMobileNumber());
        }
        Customer savedCustomer = customerRepository.save(customer);
        accountRepository.save(createNewAccount(savedCustomer));
    }

    @Override
    public void update(AccountsDto dto) {
        Accounts account = accountRepository.findByAccountNumber(dto.getAccountNumber())
                .orElseThrow(() -> new ResourceNotFoundException("Account not found"));
        Customer customer = customerRepository.findById(account.getCustomerId())
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found"));
        Customer mappedCustomer = CustomerMapper.toEntity(customer, dto.getCustomer());
        Accounts mappedAccount = AccountsMapper.toEntity(dto, account);
        accountRepository.save(mappedAccount);
        customerRepository.save(mappedCustomer);
    }

    @Override
    @Transactional
    public void delete(String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found"));
        Accounts account = accountRepository.findByCustomerId(customer.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Account not found"));
        accountRepository.delete(account);
        customerRepository.delete(customer);
    }

    private Accounts createNewAccount(Customer customer){
        //there is no relation because of the loosely coupled design
        Accounts acc = new Accounts();
        acc.setCustomerId(customer.getId());
        long randomAccNumber = 1000000000L + new Random().nextInt(900000000);
        acc.setAccountNumber(randomAccNumber);
        acc.setAccountType(AccountsConstants.SAVINGS);
        acc.setBranchAddress(AccountsConstants.ADDRESS);
        return acc;
    }
}
