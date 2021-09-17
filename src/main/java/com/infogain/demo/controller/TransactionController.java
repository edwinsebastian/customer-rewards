package com.infogain.demo.controller;

import com.infogain.demo.enums.ResourceStateEnum;
import com.infogain.demo.model.TransactionDTO;
import com.infogain.demo.model.TransactionModel;
import com.infogain.demo.model.UpdatedDTO;
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
public class TransactionController implements ICrudController<TransactionModel, TransactionDTO, UpdatedDTO>{
    private final TransactionServiceImpl service;

    @Override
    public ResponseEntity<UpdatedDTO> createResource(TransactionDTO transactionDTO) {
        TransactionModel transactionModel = TransactionDTO.toTransactionModel(transactionDTO);
        UpdatedDTO updatedDTO = new UpdatedDTO(
            service.createTransactionForCustomer(
                transactionDTO.getCustomerId(),
                transactionModel
            ).getId(),
            ResourceStateEnum.CREATED);

        return ResponseEntity.ok(updatedDTO);
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
    public ResponseEntity<UpdatedDTO> updateResource(UUID id, TransactionDTO transactionDTO) {
        TransactionModel transactionModel = TransactionDTO.toTransactionModel(transactionDTO);
        UpdatedDTO updatedDTO = new UpdatedDTO(service.updateEntity(id, transactionModel), ResourceStateEnum.UPDATED);

        return ResponseEntity.ok(updatedDTO);
    }
}
