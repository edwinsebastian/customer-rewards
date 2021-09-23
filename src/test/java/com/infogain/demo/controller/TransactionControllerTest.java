package com.infogain.demo.controller;

import com.infogain.demo.enums.ResourceStateEnum;
import com.infogain.demo.model.*;
import com.infogain.demo.service.TransactionServiceImpl;
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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;

@RunWith(SpringRunner.class)
@SpringBootTest
class TransactionControllerTest {

    @Autowired
    TransactionController controller;

    @MockBean
    TransactionServiceImpl service;

    static UpdatedDTO updatedDTOCREATED;
    static UpdatedDTO updatedDTOUPDATED;
    static UpdatedDTO updatedDTODELETED;

    static double transactionValue;
    static Date transactionDate;
    static TransactionModel transactionModel;
    static TransactionDTO transactionDTO;
    static List<TransactionModel> transactionModelList = new ArrayList<>();

    @BeforeAll
    public static void setUp(){
        transactionValue = 120;
        transactionDate = new Date();
        transactionModel = new TransactionModel(transactionValue, transactionDate);
        transactionModelList.add(transactionModel);
        transactionDTO = new TransactionDTO(transactionModel);

        updatedDTOCREATED = new UpdatedDTO(transactionModel.getId(), ResourceStateEnum.CREATED);
        updatedDTOUPDATED = new UpdatedDTO(transactionModel.getId(), ResourceStateEnum.UPDATED);
        updatedDTODELETED = new UpdatedDTO(transactionModel.getId(), ResourceStateEnum.DELETED);
    }

    @Test
    void createResource() {
        Mockito.when(service.createTransactionForCustomer(any(), any())).thenReturn(transactionModel);

        ResponseEntity<UpdatedDTO> httpResponse = controller.createResource(transactionDTO);

        Assert.assertEquals(httpResponse.getStatusCode(), HttpStatus.OK);
        Assert.assertEquals(httpResponse.getBody().getId(), updatedDTOCREATED.getId());
        Assert.assertEquals(httpResponse.getBody().getState(), updatedDTOCREATED.getState());
    }

    @Test
    void getResource() {
        Mockito.when(service.getEntity(any())).thenReturn(transactionModel);

        ResponseEntity<TransactionModel> httpResponse = controller.getResource(transactionModel.getId());

        Assert.assertEquals(httpResponse.getStatusCode(), HttpStatus.OK);
        Assert.assertEquals(httpResponse.getBody().getId(), transactionModel.getId());
        Assert.assertEquals(httpResponse.getBody().getCustomerModel().getName(), transactionModel.getCustomerModel().getName());
        Assert.assertEquals(httpResponse.getBody().getCustomerModel().getPersonalId(), transactionModel.getCustomerModel().getPersonalId());
        Assertions.assertEquals(httpResponse.getBody().getValue(), transactionModel.getValue());
    }

    @Test
    void getResources() {
        Mockito.when(service.getEntities()).thenReturn(transactionModelList);

        ResponseEntity<List<TransactionModel>> httpResponse = controller.getResources();

        Assert.assertEquals(httpResponse.getStatusCode(), HttpStatus.OK);
        Assert.assertEquals(httpResponse.getBody().get(0).getId(), transactionModelList.get(0).getId());
        Assertions.assertEquals(httpResponse.getBody().get(0).getValue(), transactionModelList.get(0).getValue());
        Assert.assertEquals(httpResponse.getBody().get(0).getDate(), transactionModelList.get(0).getDate());
        Assert.assertEquals(httpResponse.getBody().get(0).getCustomerModel().getName(), transactionModelList.get(0).getCustomerModel().getName());
        Assert.assertEquals(httpResponse.getBody().get(0).getCustomerModel().getPersonalId(), transactionModelList.get(0).getCustomerModel().getPersonalId());
        Assert.assertEquals(httpResponse.getBody().get(0).getCustomerModel().getTransactions().size(), transactionModelList.get(0).getCustomerModel().getTransactions().size());
    }

    @Test
    void updateResource() {
        Mockito.when(service.updateEntity(any(), any())).thenReturn(transactionModel.getId());

        ResponseEntity<UpdatedDTO> httpResponse = controller.updateResource(transactionModel.getId(), transactionDTO);

        Assert.assertEquals(httpResponse.getStatusCode(), HttpStatus.OK);
        Assert.assertEquals(httpResponse.getBody().getId(), updatedDTOUPDATED.getId());
        Assert.assertEquals(httpResponse.getBody().getState(), updatedDTOUPDATED.getState());
    }

    @Test
    void deleteResource() {
        Mockito.when(service.deleteEntity(any())).thenReturn(transactionModel.getId());

        ResponseEntity<UpdatedDTO> httpResponse = controller.deleteResource(transactionModel.getId());

        Assert.assertEquals(httpResponse.getStatusCode(), HttpStatus.OK);
        Assert.assertEquals(httpResponse.getBody().getId(), updatedDTODELETED.getId());
        Assert.assertEquals(httpResponse.getBody().getState(), updatedDTODELETED.getState());
    }
}