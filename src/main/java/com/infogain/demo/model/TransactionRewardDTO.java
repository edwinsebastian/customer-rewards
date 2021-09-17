package com.infogain.demo.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
public class TransactionRewardDTO{
    private TransactionDTO transactionDTO;
    private int rewardPoints;

    public TransactionRewardDTO(TransactionDTO transactionDTO, int rewardPoints){
        this.transactionDTO = transactionDTO;
        this.rewardPoints = rewardPoints;
    }
}
