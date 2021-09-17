package com.infogain.demo.controller;

import com.infogain.demo.model.CustomerModel;
import com.infogain.demo.service.CustomerServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Controller
@RequestMapping("/v1/customer")
public class CustomerController implements ICrudController<CustomerModel> {
    private final CustomerServiceImpl service;

    @Override
    public ResponseEntity<CustomerModel> createCustomer(CustomerModel customerModel) {
        return ResponseEntity.ok(service.createEntity(customerModel));
    }

    @Override
    public ResponseEntity<CustomerModel> getCustomer(UUID id) {
        return ResponseEntity.ok(service.getEntity(id));
    }

    @Override
    public ResponseEntity<List<CustomerModel>> getCostumers() {
        return ResponseEntity.ok(service.getEntities());
    }

    @Override
    public ResponseEntity<UUID> updateCustomer(UUID id, CustomerModel customerModel) {
        return ResponseEntity.ok(service.updateEntity(id, customerModel));
    }
}