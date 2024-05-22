package com.nexoscredibanco.DTO;

import lombok.Data;

@Data
public class PurchaseRequest {
    private Long cardId;
    private double price;
}