package com.bankapp.accounts.dto;

import org.springframework.http.HttpStatus;
import java.time.LocalDateTime;

public class ErrorResponseDto {
    private String apiPath;
    private HttpStatus errorCode;
    private String errorMessage;
    private LocalDateTime errorTime;
}
