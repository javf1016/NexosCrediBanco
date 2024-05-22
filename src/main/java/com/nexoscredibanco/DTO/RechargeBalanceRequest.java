package com.nexoscredibanco.DTO;

import lombok.Data;

@Data
public class RechargeBalanceRequest {
    private Long cardId;
    private double balance;
}