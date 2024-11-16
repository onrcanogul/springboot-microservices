package com.bankapp.cards.repository;

import com.bankapp.cards.entity.Cards;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CardRepository extends JpaRepository<Cards, Long> {
    List<Cards> findByMobileNumber(String mobileNumber); //example to practice list :)
    Optional<Cards> findByCardNumber(String cardNumber);
}
