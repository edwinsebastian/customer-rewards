package com.infogain.demo.controller;

import com.infogain.demo.enums.ResourceStateEnum;
import com.infogain.demo.model.*;
import com.infogain.demo.service.CustomerServiceImpl;
import com.infogain.demo.service.RewardService;
import lombok.RequiredArgsConstructor;
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
    private final RewardService rewardService;

    @Override
    public ResponseEntity<UpdatedDTO> createResource(CustomerDTO customerDTO) {
        CustomerModel customerModel = CustomerDTO.toCustomerModel(customerDTO);
        UpdatedDTO updatedDTO = new UpdatedDTO(service.createEntity(customerModel).getId(), ResourceStateEnum.CREATED);

        return ResponseEntity.ok(updatedDTO);
    }

    @Override
    public ResponseEntity<CustomerModel> getResource(UUID id) {
        return ResponseEntity.ok(service.getEntity(id));
    }

    @Override
    public ResponseEntity<List<CustomerModel>> getResources() {
        return ResponseEntity.ok(service.getEntities());
    }

    @Override
    public ResponseEntity<UpdatedDTO> updateResource(UUID id, CustomerDTO customerDTO) {
        CustomerModel customerModel = CustomerDTO.toCustomerModel(customerDTO);
        UpdatedDTO updatedDTO = new UpdatedDTO(service.updateEntity(id, customerModel), ResourceStateEnum.UPDATED);

        return ResponseEntity.ok(updatedDTO);
    }

    @GetMapping("/{customerId}/reward")
    public ResponseEntity<RewardDTO> getCustomerTransactions(@PathVariable UUID customerId, @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date fromDate, @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date toDate){
        RewardDTO rewardDTO = new RewardDTO(rewardService.getTransactionsFromCustomerBetweenDates(customerId, fromDate, toDate));
        return ResponseEntity.ok(rewardDTO);
    }
}