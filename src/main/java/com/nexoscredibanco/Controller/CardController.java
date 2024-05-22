package com.nexoscredibanco.Controller;

import com.nexoscredibanco.DTO.ActivateCardRequest;
import com.nexoscredibanco.Entity.Card;
import com.nexoscredibanco.DTO.RechargeBalanceRequest;
import com.nexoscredibanco.Exception.CardNotActiveException;
import com.nexoscredibanco.Exception.CardNotFoundException;
import com.nexoscredibanco.Exception.InvalidProductIdException;
import com.nexoscredibanco.Exception.InvalidProductIdOnAccountHolderException;
import com.nexoscredibanco.Service.CardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/card")
@Tag(name="Card Operations")
public class CardController {

    private final CardService cardService;
    @Autowired
    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @Operation(summary = "Generate Card Number")
    @GetMapping("/{productId}/number")
    public ResponseEntity<?> generateCardNumber(@PathVariable Long productId) {
        try{
            Card cardNumber = cardService.generateCard(productId);
            return new ResponseEntity<>(cardNumber, HttpStatus.CREATED);
        } catch (InvalidProductIdException | InvalidProductIdOnAccountHolderException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    @Operation(summary = "Activate a Card")
    @PostMapping("/enroll")
    public ResponseEntity<?> activateCard(@RequestBody ActivateCardRequest request) {
        try{
            Card card = cardService.activateCard(request.getCardId());
            return new ResponseEntity<>(card, HttpStatus.CREATED);
        } catch (CardNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    @Operation(summary = "Delete a Card")
    @DeleteMapping("/{cardId}")
    public ResponseEntity<?> blockCard(@PathVariable Long cardId) {
        try {
            cardService.blockCard(cardId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (CardNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    @Operation(summary = "Recharge Balance a Card")
    @PostMapping("/balance")
    public ResponseEntity<?> rechargeBalance(@RequestBody RechargeBalanceRequest request) {
        try {
            Card card = cardService.rechargeBalance(request.getCardId(), request.getBalance());
            return new ResponseEntity<>(card, HttpStatus.CREATED);
        } catch (CardNotFoundException | CardNotActiveException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    @Operation(summary = "Get Balance a Card")
    @GetMapping("/balance/{cardId}")
    public ResponseEntity<?> getBalance(@PathVariable Long cardId) {
        try {
            double balance = cardService.getBalance(cardId);
            return new ResponseEntity<>(balance, HttpStatus.CREATED);
        } catch (CardNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }
}
