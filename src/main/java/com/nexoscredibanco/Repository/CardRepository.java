package com.nexoscredibanco.Repository;

import com.nexoscredibanco.Entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {

    Optional<Card> findByProductId(Long productId);

    Optional<Card> findByCardNumber(Long cardNumber);

    Optional<Card> findById(Long cardId);
}