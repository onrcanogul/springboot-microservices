package com.bankapp.cards.service;

import com.bankapp.cards.dto.CardDto;

import java.util.List;

public interface ICardService {
    List<CardDto> fetch(String mobileNumber);
    void create(String mobileNumber);
    void update(CardDto dto);
    void delete(String mobileNumber);
}
