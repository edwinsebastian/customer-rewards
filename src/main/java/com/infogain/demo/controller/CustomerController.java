package com.infogain.demo.controller;

import com.infogain.demo.enums.ResourceStateEnum;
import com.infogain.demo.model.*;
import com.infogain.demo.service.CustomerServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Controller
@RequestMapping("/v1/customer")
public class CustomerController implements ICrudController<CustomerModel, CustomerDTO, UpdatedDTO> {
    private final CustomerServiceImpl service;

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
}