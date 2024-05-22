package com.nexoscredibanco.Controller;

import com.nexoscredibanco.DTO.ActivateCardRequest;
import com.nexoscredibanco.DTO.RechargeBalanceRequest;
import com.nexoscredibanco.Entity.Card;
import com.nexoscredibanco.Exception.CardNotFoundException;
import com.nexoscredibanco.Exception.InvalidProductIdException;
import com.nexoscredibanco.Service.CardService;
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
class CardControllerTest {

    @Mock
    private CardService cardService;

    @InjectMocks
    private CardController cardController;

    @Test
    void generateCardNumber_ShouldReturnCreatedCard() {
        Long productId = 1L;
        Card card = new Card();
        when(cardService.generateCard(productId)).thenReturn(card);

        ResponseEntity<?> response = cardController.generateCardNumber(productId);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(card, response.getBody());
    }

    @Test
    void generateCardNumber_ShouldReturnBadRequestOnInvalidProductIdException() {
        Long productId = 1L;
        when(cardService.generateCard(productId)).thenThrow(new InvalidProductIdException("Invalid product ID"));

        ResponseEntity<?> response = cardController.generateCardNumber(productId);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Invalid product ID", response.getBody());
    }

    @Test
    void activateCard_ShouldReturnCreatedCard() {
        ActivateCardRequest request = new ActivateCardRequest();
        request.setCardId(1L);
        Card card = new Card();
        when(cardService.activateCard(request.getCardId())).thenReturn(card);

        ResponseEntity<?> response = cardController.activateCard(request);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(card, response.getBody());
    }

    @Test
    void activateCard_ShouldReturnBadRequestOnCardNotFoundException() {
        ActivateCardRequest request = new ActivateCardRequest();
        request.setCardId(1L);
        when(cardService.activateCard(request.getCardId())).thenThrow(new CardNotFoundException("Card not found"));

        ResponseEntity<?> response = cardController.activateCard(request);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Card not found", response.getBody());
    }

    @Test
    void blockCard_ShouldReturnNoContent() {
        Long cardId = 1L;

        ResponseEntity<?> response = cardController.blockCard(cardId);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(cardService, times(1)).blockCard(cardId);
    }

    @Test
    void blockCard_ShouldReturnBadRequestOnCardNotFoundException() {
        Long cardId = 1L;
        doThrow(new CardNotFoundException("Card not found")).when(cardService).blockCard(cardId);

        ResponseEntity<?> response = cardController.blockCard(cardId);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Card not found", response.getBody());
    }

    @Test
    void rechargeBalance_ShouldReturnCreatedCard() {
        RechargeBalanceRequest request = new RechargeBalanceRequest();
        request.setCardId(1L);
        request.setBalance(100.0);
        Card card = new Card();
        when(cardService.rechargeBalance(request.getCardId(), request.getBalance())).thenReturn(card);

        ResponseEntity<?> response = cardController.rechargeBalance(request);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(card, response.getBody());
    }

    @Test
    void rechargeBalance_ShouldReturnBadRequestOnCardNotFoundException() {
        RechargeBalanceRequest request = new RechargeBalanceRequest();
        request.setCardId(1L);
        request.setBalance(100.0);
        when(cardService.rechargeBalance(request.getCardId(), request.getBalance())).thenThrow(new CardNotFoundException("Card not found"));

        ResponseEntity<?> response = cardController.rechargeBalance(request);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Card not found", response.getBody());
    }

    @Test
    void getBalance_ShouldReturnBalance() {
        Long cardId = 1L;
        double balance = 100.0;
        when(cardService.getBalance(cardId)).thenReturn(balance);

        ResponseEntity<?> response = cardController.getBalance(cardId);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(balance, response.getBody());
    }

    @Test
    void getBalance_ShouldReturnBadRequestOnCardNotFoundException() {
        Long cardId = 1L;
        when(cardService.getBalance(cardId)).thenThrow(new CardNotFoundException("Card not found"));

        ResponseEntity<?> response = cardController.getBalance(cardId);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Card not found", response.getBody());
    }
}
