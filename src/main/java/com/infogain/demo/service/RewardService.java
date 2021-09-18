package com.infogain.demo.service;

import com.infogain.demo.model.TransactionDTO;
import com.infogain.demo.model.TransactionRewardDTO;
import com.infogain.demo.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RequiredArgsConstructor
@Service
public class RewardService {
    private final TransactionRepository transactionRepository;

    public List<TransactionRewardDTO> getTransactionsFromCustomerBetweenDates(UUID customerId, Date fromDate, Date toDate){

        return StreamSupport
            .stream(transactionRepository.getAllByCustomerModel_IdAndDateIsBetween(customerId, fromDate, toDate).spliterator(), true)
             .map(transactionModel ->
                new TransactionRewardDTO(
                    new TransactionDTO(transactionModel),
                    pointsCalculation(transactionModel.getValue())
                )

             )
            .collect(Collectors.toList());
    }

    static public int pointsCalculation(double value){
        int points = twoPointsOverHundredDollar(value);
        points += onePointOverFiftyDollar(value);

        return points;
    }

    static private int twoPointsOverHundredDollar(double value){
        Long n = Math.round(value) - 100;
        if(n <= 0){
            return 0;
        }

        return n.intValue() * 2;
    }

    static private int onePointOverFiftyDollar(double value){
        double d = value/50;
        Long n = Math.round(d);
        if (n < 1){
            return 0;
        }

        return (n.intValue() - 1) * 50;
    }
}
