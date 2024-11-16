package com.bankapp.loans.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;

@Getter @Setter @ToString
@AllArgsConstructor @NoArgsConstructor
public class LoansDto {
    @NotEmpty
    private String mobileNumber;
    @NotEmpty
    private String loanNumber;
    @NotEmpty
    private String loanType;
    @Positive
    private int totalLoan;
    @PositiveOrZero
    private int amountPaid;
    @PositiveOrZero
    private int outstandingAmount;
}
