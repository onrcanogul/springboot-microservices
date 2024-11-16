package com.bankapp.cards.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ResponseDto {
    private int statusCode;
    private String statusMessage;
}
