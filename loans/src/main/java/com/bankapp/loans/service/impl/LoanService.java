package com.bankapp.loans.service.impl;

import com.bankapp.loans.constants.LoansConstants;
import com.bankapp.loans.dto.LoansDto;
import com.bankapp.loans.entity.Loans;
import com.bankapp.loans.exception.ResourceNotFoundException;
import com.bankapp.loans.mapper.LoansMapper;
import com.bankapp.loans.repository.LoansRepository;
import com.bankapp.loans.service.ILoanService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class LoanService implements ILoanService {
    private final LoansRepository repository;
    @Override
    public LoansDto get(String mobileNumber) {
        Loans loan = repository.findByMobileNumber(mobileNumber).orElseThrow(() -> new ResourceNotFoundException("loan not found"));
        LoansDto dto = LoansMapper.toDto(loan, new LoansDto());
        return dto;
    }

    @Override
    public void create(String mobileNumber) {
        Optional<Loans> optionalLoans= repository.findByMobileNumber(mobileNumber);
        if(optionalLoans.isPresent()){
            throw new ResourceNotFoundException("Loan already registered with given mobileNumber "+mobileNumber);
        }
        repository.save(createNewLoan(mobileNumber));
    }

    private Loans createNewLoan(String mobileNumber) {
        Loans newLoan = new Loans();
        long randomLoanNumber = 100000000000L + new Random().nextInt(900000000);
        newLoan.setLoanNumber(Long.toString(randomLoanNumber));
        newLoan.setMobileNumber(mobileNumber);
        newLoan.setLoanType(LoansConstants.HOME_LOAN);
        newLoan.setTotalLoan(LoansConstants.NEW_LOAN_LIMIT);
        newLoan.setAmountPaid(0);
        newLoan.setOutstandingAmount(LoansConstants.NEW_LOAN_LIMIT);
        return newLoan;
    }

    @Override
    public void update(LoansDto dto) {
        Loans loan = repository.findByMobileNumber(dto.getMobileNumber())
                .orElseThrow(() -> new ResourceNotFoundException("Loan not found"));
        LoansMapper.toEntity(dto, loan);
        repository.save(loan);
    }

    @Override
    public void delete(String mobileNumber) {
        Loans loan = repository.findByMobileNumber(mobileNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Loan not found"));
        repository.delete(loan);
    }
}
