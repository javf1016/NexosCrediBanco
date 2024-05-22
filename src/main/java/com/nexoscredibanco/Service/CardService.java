package com.nexoscredibanco.Service;

import com.nexoscredibanco.Entity.AccountHolder;
import com.nexoscredibanco.Entity.Card;
import com.nexoscredibanco.Entity.Product;
import com.nexoscredibanco.Exception.CardNotActiveException;
import com.nexoscredibanco.Exception.CardNotFoundException;
import com.nexoscredibanco.Exception.InvalidProductIdException;
import com.nexoscredibanco.Exception.InvalidProductIdOnAccountHolderException;
import com.nexoscredibanco.Repository.AccountHolderRepository;
import com.nexoscredibanco.Repository.CardRepository;
import com.nexoscredibanco.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;

@Service
public class CardService {

    private final CardRepository cardRepository;

    private final ProductRepository productRepository;

    private final AccountHolderRepository accountHolderRepository;

    @Autowired
    public CardService(CardRepository cardRepository, ProductRepository productRepository, AccountHolderRepository accountHolderRepository) {
        this.cardRepository = cardRepository;
        this.productRepository = productRepository;
        this.accountHolderRepository = accountHolderRepository;
    }



    public Card generateCard(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new InvalidProductIdException("Invalid product ID"));
        AccountHolder accountHolder = accountHolderRepository.findByProductsProductId(productId)
                .orElseThrow(() -> new InvalidProductIdOnAccountHolderException("Invalid product ID on AccounHolder"));

        Long cardNumber = generateCardNumber(productId);
        Card existingCard = cardRepository.findByProductId(productId).orElse(null);

        if (existingCard != null) {
            existingCard.setCardNumber(cardNumber);
            existingCard.setExpirationDate(LocalDate.now().plusYears(3));
            existingCard.setActive(false);
            existingCard.setBalance(0.0);
            return cardRepository.save(existingCard);
        }

        Card newCard = new Card(null, product.getProductId(), cardNumber, accountHolder, LocalDate.now().plusYears(3), false, 0.0);
        return cardRepository.save(newCard);
    }

    public Card activateCard(Long cardNumber) {
        Card card = cardRepository.findByCardNumber(cardNumber)
                .orElseThrow(() -> new CardNotFoundException("Card not found"));
        card.setActive(true);
        return cardRepository.save(card);
    }

    public void blockCard(Long cardId) {
        Card card = cardRepository.findById(cardId).orElseThrow(() -> new CardNotFoundException("Card not found"));
        cardRepository.delete(card);
    }

    public Card rechargeBalance(Long cardNumber, double amount) {
        Card card = cardRepository.findByCardNumber(cardNumber)
                .orElseThrow(() -> new CardNotFoundException("Card not found"));
        if(card.isActive()) {
            card.setBalance(card.getBalance() + amount);
            return cardRepository.save(card);
        }
        else {
            throw new CardNotActiveException("Card not active");
        }
    }

    public double getBalance(Long cardId) {
        Card card = cardRepository.findById(cardId)
                .orElseThrow(() -> new CardNotFoundException("Card not found"));
        return card.getBalance();
    }

    private long generateCardNumber(Long productId) {
        long cardNumber = productId * 1000000L; // Los primeros 6 dígitos corresponden al ID del producto

        // Generar los siguientes 10 dígitos aleatorios
        long randomPart = (long) (Math.random() * 10000000000000000L); // Genera un número aleatorio de 10 dígitos
        cardNumber += randomPart; // Combinar los dígitos del ID del producto con los aleatorios

        return cardNumber;
    }
}