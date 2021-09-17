package com.infogain.demo.model;

import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
public class TransactionDTO {
    private double value;
    private Date date = new Date();
    private UUID customerId;

    public static TransactionModel toTransactionModel(TransactionDTO transactionDTO){
        return new TransactionModel(transactionDTO.getValue(), new Date());
    }
}
