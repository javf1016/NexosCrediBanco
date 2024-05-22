package com.nexoscredibanco.Controller;

import com.nexoscredibanco.DTO.CancelTransactionRequest;
import com.nexoscredibanco.DTO.PurchaseRequest;
import com.nexoscredibanco.Entity.Transaction;
import com.nexoscredibanco.Exception.*;
import com.nexoscredibanco.Service.TransactionService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TransactionControllerTest {

    @Mock
    private TransactionService transactionService;

    @InjectMocks
    private TransactionController transactionController;

    @Test
    void makePurchase_ShouldReturnCreatedTransaction() {
        PurchaseRequest request = new PurchaseRequest();
        request.setCardId(1L);
        request.setPrice(100.0);
        Transaction transaction = new Transaction();
        when(transactionService.makePurchase(request.getCardId(), request.getPrice())).thenReturn(transaction);

        ResponseEntity<?> response = transactionController.makePurchase(request);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(transaction, response.getBody());
    }

    @Test
    void makePurchase_ShouldReturnBadRequestOnCardNotFoundException() {
        PurchaseRequest request = new PurchaseRequest();
        request.setCardId(1L);
        request.setPrice(100.0);
        when(transactionService.makePurchase(request.getCardId(), request.getPrice())).thenThrow(new CardNotFoundException("Card not exist"));

        ResponseEntity<?> response = transactionController.makePurchase(request);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Card not exist", response.getBody());
    }

    @Test
    void getTransaction_ShouldReturnTransaction() {
        Long transactionId = 1L;
        Transaction transaction = new Transaction();
        when(transactionService.getTransaction(transactionId)).thenReturn(transaction);

        ResponseEntity<?> response = transactionController.getTransaction(transactionId);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(transaction, response.getBody());
    }

    @Test
    void getTransaction_ShouldReturnBadRequestOnTransactionNotFoundException() {
        Long transactionId = 1L;
        when(transactionService.getTransaction(transactionId)).thenThrow(new TransactionNotFoundException("Transaction not found"));

        ResponseEntity<?> response = transactionController.getTransaction(transactionId);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Transaction not found", response.getBody());
    }

    @Test
    void cancelTransaction_ShouldReturnOkTransaction() {
        CancelTransactionRequest request = new CancelTransactionRequest();
        request.setCardId(1L);
        request.setTransactionId(1L);
        Transaction transaction = new Transaction();
        when(transactionService.cancelTransaction(request.getTransactionId(), request.getCardId())).thenReturn(transaction);

        ResponseEntity<?> response = transactionController.cancelTransaction(request);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(transaction, response.getBody());
    }

    @Test
    void cancelTransaction_ShouldReturnBadRequestOnTransactionAlreadyCanceledException() {
        CancelTransactionRequest request = new CancelTransactionRequest();
        request.setCardId(1L);
        request.setTransactionId(1L);
        when(transactionService.cancelTransaction(request.getTransactionId(), request.getCardId())).thenThrow(new TransactionAlreadyCanceledException("Transaction already canceled"));

        ResponseEntity<?> response = transactionController.cancelTransaction(request);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Transaction already canceled", response.getBody());
    }
}
