package com.nexoscredibanco.Controller;

import com.nexoscredibanco.DTO.CancelTransactionRequest;
import com.nexoscredibanco.Entity.Transaction;
import com.nexoscredibanco.DTO.PurchaseRequest;
import com.nexoscredibanco.Exception.*;
import com.nexoscredibanco.Service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    private final TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping("/purchase" )
    public ResponseEntity<?> makePurchase(@RequestBody PurchaseRequest request) {
        try {
            Transaction transaction = transactionService.makePurchase(request.getCardId(), request.getPrice());
            return new ResponseEntity<>(transaction, HttpStatus.CREATED);
        } catch (CardNotFoundException | InsufficientBalanceException | CardNotValidException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    @GetMapping("/{transactionId}")
    public ResponseEntity<?> getTransaction(@PathVariable Long transactionId) {
        try{
            Transaction transaction = transactionService.getTransaction(transactionId);
            return new ResponseEntity<>(transaction, HttpStatus.CREATED);
        } catch (TransactionNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    @PostMapping("/annulation")
    public ResponseEntity<?> cancelTransaction(@RequestBody CancelTransactionRequest request) {
        try{
            Transaction transaction = transactionService.cancelTransaction(request.getTransactionId(), request.getCardId());
            return new ResponseEntity<>(transaction, HttpStatus.OK);
        } catch (TransactionAlreadyCanceledException | CancelTimeTransactionException | TransactionNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }
}
