package com.bankapp.cards.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class CardAlreadyExistException extends RuntimeException{
    public CardAlreadyExistException(String message) {
        super(message);
    }
}
