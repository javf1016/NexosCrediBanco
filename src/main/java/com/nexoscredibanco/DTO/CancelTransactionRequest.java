package com.nexoscredibanco.DTO;

import lombok.Data;

@Data
public class CancelTransactionRequest {
    private Long cardId;
    private Long transactionId;
}