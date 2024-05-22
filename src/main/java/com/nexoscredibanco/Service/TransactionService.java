package com.nexoscredibanco.Service;

import com.nexoscredibanco.Entity.Card;
import com.nexoscredibanco.Entity.Transaction;
import com.nexoscredibanco.Exception.*;
import com.nexoscredibanco.Repository.CardRepository;
import com.nexoscredibanco.Repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;

    private final CardRepository cardRepository;

    @Autowired
    public TransactionService(TransactionRepository transactionRepository, CardRepository cardRepository) {
        this.transactionRepository = transactionRepository;
        this.cardRepository = cardRepository;
    }

    public Transaction makePurchase(Long cardNumber, double amount) {
        Optional<Card> card = cardRepository.findByCardNumber(cardNumber);
        if(card.isPresent()) {
            if ((!card.get().isActive() || card.get().getExpirationDate().isBefore(LocalDateTime.now().toLocalDate()))) {
                throw new CardNotValidException("Card is not valid for purchase");
            }
            if (card.get().getBalance() < amount) {
                throw new InsufficientBalanceException("Insufficient balance");
            }
            card.get().setBalance(card.get().getBalance() - amount);
            cardRepository.save(card.get());
            return transactionRepository.save(new Transaction(null, cardNumber, amount, LocalDateTime.now(), false));
        }
        throw new CardNotFoundException("Card not exist");
    }

    public Transaction cancelTransaction(Long transactionId, Long cardNumber) {
        Optional<Transaction> transactionOptional = transactionRepository.findById(transactionId);
        if (transactionOptional.isPresent()) {
            Transaction transaction = transactionOptional.get();
            if (transaction.isCanceled()) {
                throw new TransactionAlreadyCanceledException("Transaction already canceled");
            }
            if (transaction.getTimestamp().isBefore(LocalDateTime.now().minusHours(24))) {
                throw new CancelTimeTransactionException("Cannot cancel transaction after 24 hours");
            }
            if(cardNumber.equals(transaction.getCardNumber())) {
                Optional<Card> card = cardRepository.findByCardNumber(cardNumber);
                transaction.setCanceled(true);
                transactionRepository.save(transaction);
                card.get().setBalance(card.get().getBalance() + transaction.getAmount());
                cardRepository.save(card.get());
                return transaction;
            }
            else{
                throw new TransactionNotFoundException("The transaction is not with this card");
            }
        }
        throw new TransactionNotFoundException("Transaction not found");
    }

    public Transaction getTransaction(Long transactionId) {
        return transactionRepository.findById(transactionId)
                .orElseThrow(() -> new TransactionNotFoundException("Transaction not found"));
    }
}
