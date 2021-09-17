package com.infogain.demo.controller;

import com.infogain.demo.model.TransactionModel;
import com.infogain.demo.service.TransactionServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Controller
@RequestMapping("/v1/transaction")
public class TransactionController implements ICrudController<TransactionModel>{
    private final TransactionServiceImpl service;

    @Override
    public ResponseEntity<TransactionModel> createResource(TransactionModel transactionModel) {
        preventUpdateThroughPost(transactionModel);
        return ResponseEntity.ok(service.createEntity(transactionModel));
    }

    @Override
    public ResponseEntity<TransactionModel> getResource(UUID id) {
        return ResponseEntity.ok(service.getEntity(id));
    }

    @Override
    public ResponseEntity<List<TransactionModel>> getResources() {
        return ResponseEntity.ok(service.getEntities());
    }

    @Override
    public ResponseEntity<UUID> updateResource(UUID id, TransactionModel customerModel) {
        return ResponseEntity.ok(service.updateEntity(id, customerModel));
    }
}
