package com.bankapp.accounts.service.client;

import com.bankapp.accounts.dto.CardDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class CardsFallback implements CardsFeignClient{
    @Override
    public ResponseEntity<CardDto> get(String mobileNumber) {
        return null;
    }
}
