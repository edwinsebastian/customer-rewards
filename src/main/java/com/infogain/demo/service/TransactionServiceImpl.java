package com.infogain.demo.service;

import com.infogain.demo.exception.ResourceNotFoundException;
import com.infogain.demo.model.TransactionModel;
import com.infogain.demo.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RequiredArgsConstructor
@Service
public class TransactionServiceImpl implements ICrudService<TransactionModel> {
    private final TransactionRepository repository;

    @Override
    public TransactionModel createEntity(TransactionModel model) {
        return repository.save(model);
    }

    @Override
    public TransactionModel getEntity(UUID id) {
        TransactionModel transactionModel = null;
        try {
            transactionModel = repository.findById(id).get();
        }catch (NoSuchElementException ex){
            throw new ResourceNotFoundException("Transaction not found.");
        }

        return transactionModel;
    }

    @Override
    public List<TransactionModel> getEntities() {
        return StreamSupport
                .stream(repository.findAll().spliterator(), true)
                .collect(Collectors.toList());
    }

    @Override
    public UUID updateEntity(UUID id, TransactionModel model) {
        model.setId(id);
        return repository.save(model).getId();
    }

    @Override
    public UUID delete(UUID id) {
        repository.deleteById(id);
        return id;
    }
}
