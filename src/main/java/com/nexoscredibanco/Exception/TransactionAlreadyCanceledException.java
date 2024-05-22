package com.nexoscredibanco.Exception;

public class TransactionAlreadyCanceledException extends RuntimeException {
    public TransactionAlreadyCanceledException(String message) {
        super(message);
    }
}