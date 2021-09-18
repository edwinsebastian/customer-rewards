package com.infogain.demo.controller;

import com.infogain.demo.enums.ResourceStateEnum;
import com.infogain.demo.model.*;
import com.infogain.demo.service.CustomerServiceImpl;
import com.infogain.demo.service.RewardService;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

import static org.mockito.ArgumentMatchers.any;

@RunWith(SpringRunner.class)
@SpringBootTest
class CustomerControllerTest {

    @Autowired
    CustomerController controller;

    @MockBean
    CustomerServiceImpl service;

    @MockBean
    RewardService rewardService;

    static String customerName;
    static String customerPersonalId;
    static CustomerModel customerModel;
    static List<CustomerModel> customerModelList = new ArrayList<>();
    static CustomerDTO customerDTO;

    static UpdatedDTO updatedDTOCREATED;
    static UpdatedDTO updatedDTOUPDATED;

    static double transactionValue;
    static int calculatedPoints;
    static Date transactionDate;
    static TransactionModel transactionModel;
    static TransactionDTO transactionDTO;
    static TransactionRewardDTO transactionRewardDTO;
    static List<TransactionRewardDTO> transactionRewardList = new ArrayList<>();
    static RewardDTO rewardDTO;

    @BeforeAll
    public static void setUp(){
        customerName = "user";
        customerPersonalId = "123456789";
        customerModel = new CustomerModel(customerName, customerPersonalId);
        customerDTO = new CustomerDTO(customerModel);
        customerModelList.add(customerModel);

        updatedDTOCREATED = new UpdatedDTO(customerModel.getId(), ResourceStateEnum.CREATED);
        updatedDTOUPDATED = new UpdatedDTO(customerModel.getId(), ResourceStateEnum.UPDATED);

        transactionValue = 120;
        //for a value of 120 must be 90 points
        calculatedPoints = RewardService.pointsCalculation(transactionValue);
        transactionDate = new Date();
        transactionModel = new TransactionModel(transactionValue, transactionDate);
        transactionDTO = new TransactionDTO(transactionModel);
        transactionRewardDTO = new TransactionRewardDTO(transactionDTO, calculatedPoints);
        transactionRewardList.add(transactionRewardDTO);
        rewardDTO = new RewardDTO(transactionRewardList);
    }

    @Test
    void createResource() {
        Mockito.when(service.createEntity(any())).thenReturn(customerModel);

        ResponseEntity<UpdatedDTO> httpResponse = controller.createResource(customerDTO);

        Assert.assertEquals(httpResponse.getStatusCode(), HttpStatus.OK);
        Assert.assertEquals(httpResponse.getBody().getId(), updatedDTOCREATED.getId());
        Assert.assertEquals(httpResponse.getBody().getState(), updatedDTOCREATED.getState());
    }

    @Test
    void getResource() {
        Mockito.when(service.getEntity(any())).thenReturn(customerModel);

        ResponseEntity<CustomerModel> httpResponse = controller.getResource(customerModel.getId());

        Assert.assertEquals(httpResponse.getStatusCode(), HttpStatus.OK);
        Assert.assertEquals(httpResponse.getBody().getId(), customerModel.getId());
        Assert.assertEquals(httpResponse.getBody().getName(), customerModel.getName());
        Assert.assertEquals(httpResponse.getBody().getPersonalId(), customerModel.getPersonalId());
        Assert.assertEquals(httpResponse.getBody().getTransactions(), customerModel.getTransactions());
    }

    @Test
    void getResources() {
        Mockito.when(service.getEntities()).thenReturn(customerModelList);

        ResponseEntity<List<CustomerModel>> httpResponse = controller.getResources();

        Assert.assertEquals(httpResponse.getStatusCode(), HttpStatus.OK);
        Assert.assertEquals(httpResponse.getBody().get(0).getId(), customerModelList.get(0).getId());
        Assert.assertEquals(httpResponse.getBody().get(0).getName(), customerModelList.get(0).getName());
        Assert.assertEquals(httpResponse.getBody().get(0).getPersonalId(), customerModelList.get(0).getPersonalId());
        Assert.assertEquals(httpResponse.getBody().get(0).getTransactions(), customerModelList.get(0).getTransactions());
    }

    @Test
    void updateResource() {
        Mockito.when(service.updateEntity(any(), any())).thenReturn(customerModel.getId());

        ResponseEntity<UpdatedDTO> httpResponse = controller.updateResource(customerModel.getId(), customerDTO);

        Assert.assertEquals(httpResponse.getStatusCode(), HttpStatus.OK);
        Assert.assertEquals(httpResponse.getBody().getId(), updatedDTOUPDATED.getId());
        Assert.assertEquals(httpResponse.getBody().getState(), updatedDTOUPDATED.getState());
    }

    @Test
    void getCustomerPointsReward() {
        Mockito.when(rewardService.getTransactionsFromCustomerBetweenDates(any(), any(), any())).thenReturn(transactionRewardList);

        ResponseEntity<RewardDTO> httpResponse = controller.getCustomerPointsReward(customerModel.getId(), new Date(), new Date());

        Assert.assertEquals(httpResponse.getStatusCode(), HttpStatus.OK);
        Assert.assertEquals(httpResponse.getBody().getTransactions().get(0).getRewardPoints(), calculatedPoints);
        Assert.assertEquals(httpResponse.getBody().getTransactions().get(0).getTransactionDTO().getCustomerId(), transactionRewardList.get(0).getTransactionDTO().getCustomerId());
        Assertions.assertEquals(httpResponse.getBody().getTransactions().get(0).getTransactionDTO().getValue(), transactionRewardList.get(0).getTransactionDTO().getValue());
        Assert.assertEquals(httpResponse.getBody().getTransactions().get(0).getTransactionDTO().getDate(), transactionRewardList.get(0).getTransactionDTO().getDate());
        Assert.assertEquals(httpResponse.getBody().getTotalRewardPoints(), rewardDTO.getTotalRewardPoints());
    }
}