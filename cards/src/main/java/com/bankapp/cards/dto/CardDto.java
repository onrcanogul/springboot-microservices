package com.bankapp.cards.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Data
public class CardDto {
    @NotEmpty
    @Pattern(regexp = "(^$|[0-9]{10})")
    private String mobileNumber;
    @NotEmpty
    @Pattern(regexp = "(^$|[0-9]{11})")
    private String cardNumber;
    @NotEmpty
    private String cardType;
    @PositiveOrZero
    private int totalLimit;
    @PositiveOrZero
    private int amountUsed;
    @PositiveOrZero
    private int availableAmount;
}
