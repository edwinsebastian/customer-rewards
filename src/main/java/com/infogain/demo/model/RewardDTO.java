package com.infogain.demo.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
@Data
public class RewardDTO {
    private List<TransactionRewardDTO> transactions;
    private int totalRewardPoints = 0;

    public RewardDTO(List<TransactionRewardDTO> transactions){
        this.transactions = transactions;
        this.totalRewardPoints = transactions
                .parallelStream()
                .mapToInt(transactionRewardDTO -> transactionRewardDTO.getRewardPoints())
                .sum();
    }
}
