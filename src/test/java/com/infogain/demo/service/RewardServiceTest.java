package com.infogain.demo.service;

import org.junit.Assert;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class RewardServiceTest {

    @CsvSource({
        "120.0d, 90",
        "0.0d, 0",
        "50.0d, 0",
        "100.0d, 50",
        "150.0d, 200"
    })
    @ParameterizedTest
    void pointsCalculationOk(double purchaseValue, int expectedPoints) {
        Assert.assertEquals(RewardService.pointsCalculation(purchaseValue), expectedPoints);
    }

    @CsvSource({
            "120.0d, 91",
            "0.0d, 1",
            "50.0d, 1",
            "100.0d, 51",
            "150.0d, 201"
    })
    @ParameterizedTest
    void pointsCalculationFail(double purchaseValue, int expectedPoints) {
        Assert.assertNotEquals(RewardService.pointsCalculation(purchaseValue), expectedPoints);
    }
}