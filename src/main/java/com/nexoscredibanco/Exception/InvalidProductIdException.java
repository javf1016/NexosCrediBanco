package com.nexoscredibanco.Exception;

public class InvalidProductIdException extends RuntimeException {
    public InvalidProductIdException(String message) {
        super(message);
    }
}