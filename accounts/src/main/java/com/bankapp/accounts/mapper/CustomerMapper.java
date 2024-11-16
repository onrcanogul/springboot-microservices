package com.bankapp.accounts.mapper;

import com.bankapp.accounts.dto.CustomerDto;
import com.bankapp.accounts.entity.Customer;

public class CustomerMapper {
    public static CustomerDto toDto(Customer customer, CustomerDto dto) {
        dto.setName(customer.getName());
        dto.setEmail(customer.getEmail());
        dto.setMobileNumber(customer.getMobileNumber());
        return dto;
    }
    public static Customer toEntity(Customer customer, CustomerDto dto) {
        customer.setName(dto.getName());
        customer.setEmail(dto.getEmail());
        customer.setMobileNumber(dto.getMobileNumber());
        return customer;
    }
}