package com.bankapp.loans.service;

import com.bankapp.loans.dto.LoansDto;

public interface ILoanService {
    LoansDto get(String mobileNumber);
    void create(String mobileNumber);
    void update(LoansDto dto);
    void delete(String mobileNumber);
}
