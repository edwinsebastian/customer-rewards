package com.infogain.demo.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Date;
import java.util.UUID;

@RequiredArgsConstructor
@Data
public class TransactionDTO {
    private double value;
    private Date date = new Date();
    private UUID customerId;

    public TransactionDTO(TransactionModel transactionModel){
        this.value = transactionModel.getValue();
        this.date = transactionModel.getDate();
        this.customerId = transactionModel.getCustomerModel().getId();
    }

    public static TransactionModel toTransactionModel(TransactionDTO transactionDTO){
        return new TransactionModel(transactionDTO.getValue(), new Date());
    }
}
