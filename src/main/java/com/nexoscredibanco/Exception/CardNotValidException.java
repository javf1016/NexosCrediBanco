package com.nexoscredibanco.Exception;

public class CardNotValidException extends RuntimeException {
    public CardNotValidException(String message) {
        super(message);
    }
}