package com.bankapp.cards.service.impl;
import com.bankapp.cards.constants.CardsConstants;
import com.bankapp.cards.dto.CardDto;
import com.bankapp.cards.entity.Cards;
import com.bankapp.cards.exception.CardAlreadyExistException;
import com.bankapp.cards.exception.ResourceNotFoundException;
import com.bankapp.cards.mapper.CardMapper;
import com.bankapp.cards.repository.CardRepository;
import com.bankapp.cards.service.ICardService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
@AllArgsConstructor
public class CardService implements ICardService {
    private CardRepository repository;
    @Override
    public List<CardDto> fetch(String mobileNumber) {
        //example of list
        List<Cards> cards = repository.findByMobileNumber(mobileNumber).stream().toList();
        List<CardDto> dto = new ArrayList<>();
        cards.forEach((card)->{
            dto.add(CardMapper.toDto(card, new CardDto()));
        });
        return dto;
    }

    @Override
    public void create(String mobileNumber) {
        List<Cards> cards = repository.findByMobileNumber(mobileNumber);
        if(cards.stream().count() > 0) {
            throw new CardAlreadyExistException("Card already registered with given mobile number");
        }
        repository.save(createNewCard(mobileNumber));
    }

    @Override
    public void update(CardDto dto) {
        Cards card = repository.findByCardNumber(dto.getCardNumber()).orElseThrow(() -> new ResourceNotFoundException("Card not found"));
        CardMapper.toEntity(dto, card);
        repository.save(card);
    }

    @Override
    public void delete(String mobileNumber) {
        List<Cards> cards = repository.findByMobileNumber(mobileNumber);
        if(cards.stream().count() == 0) {
            throw new ResourceNotFoundException("Card not found");
        }
        repository.delete(cards.getFirst());
    }

    private Cards createNewCard(String mobileNumber){
        Cards card = new Cards();
        long randomCardNumber = 10000000000L + new Random().nextInt(900000000);
        card.setCardNumber(Long.toString(randomCardNumber));
        card.setCardType(CardsConstants.CREDIT_CARD);
        card.setTotalLimit(CardsConstants.NEW_CARD_LIMIT);
        card.setAmountUsed(0);
        card.setAvailableAmount(CardsConstants.NEW_CARD_LIMIT);
        card.setMobileNumber(mobileNumber);
        return card;
    }
}
