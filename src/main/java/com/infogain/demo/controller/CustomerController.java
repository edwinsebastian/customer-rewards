package com.infogain.demo.controller;

import com.infogain.demo.enums.ResourceStateEnum;
import com.infogain.demo.model.*;
import com.infogain.demo.service.CustomerServiceImpl;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Controller
@RequestMapping("/v1/customer")
public class CustomerController implements ICrudController<CustomerModel, CustomerDTO, UpdatedDTO> {
    private final CustomerServiceImpl service;

    Logger logger = LoggerFactory.getLogger(CustomerController.class);

    @Override
    public ResponseEntity<UpdatedDTO> createResource(CustomerDTO customerDTO) {
        logger.info("createResource param: {}", customerDTO);
        CustomerModel customerModel = CustomerDTO.toCustomerModel(customerDTO);
        UpdatedDTO updatedDTO = new UpdatedDTO(service.createEntity(customerModel).getId(), ResourceStateEnum.CREATED);

        return ResponseEntity.ok(updatedDTO);
    }

    @Override
    public ResponseEntity<CustomerModel> getResource(UUID id) {
        logger.info("getResource param: {}", id);
        return ResponseEntity.ok(service.getEntity(id));
    }

    @Override
    public ResponseEntity<List<CustomerModel>> getResources() {
        return ResponseEntity.ok(service.getEntities());
    }

    @Override
    public ResponseEntity<UpdatedDTO> updateResource(UUID id, CustomerDTO customerDTO) {
        logger.info("updateResource param: {} {}", id, customerDTO);
        CustomerModel customerModel = CustomerDTO.toCustomerModel(customerDTO);
        UpdatedDTO updatedDTO = new UpdatedDTO(service.updateEntity(id, customerModel), ResourceStateEnum.UPDATED);

        return ResponseEntity.ok(updatedDTO);
    }

    @Override
    public ResponseEntity<UpdatedDTO> deleteResource(UUID id) {
        logger.info("deleteResource param: {}", id);
        UpdatedDTO updatedDTO = new UpdatedDTO(service.deleteEntity(id), ResourceStateEnum.DELETED);

        return ResponseEntity.ok(updatedDTO);
    }

    @GetMapping("/{customerId}/reward")
    public ResponseEntity<RewardDTO> getCustomerPointsReward(@PathVariable UUID customerId, @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date fromDate, @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date toDate){
        logger.info("getCustomerPointsReward param: {} {} {}", customerId, fromDate, toDate);
        RewardDTO rewardDTO = new RewardDTO(service.getCustomerPointsReward(customerId, fromDate, toDate));

        return ResponseEntity.ok(rewardDTO);
    }
}