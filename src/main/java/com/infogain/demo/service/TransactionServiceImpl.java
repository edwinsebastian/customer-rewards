package com.infogain.demo.service;

import com.infogain.demo.exception.ResourceNotFoundException;
import com.infogain.demo.model.CustomerModel;
import com.infogain.demo.model.TransactionModel;
import com.infogain.demo.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private final CustomerServiceImpl customerService;

    Logger logger = LoggerFactory.getLogger(TransactionServiceImpl.class);

    public TransactionModel createTransactionForCustomer(UUID customerId, TransactionModel transactionModel){
        logger.info("createTransactionForCustomer {} {}", customerId, transactionModel);
        CustomerModel customerModel = customerService.getEntity(customerId);
        transactionModel.setCustomerModel(customerModel);

        return createEntity(transactionModel);
    }

    @Override
    public TransactionModel createEntity(TransactionModel model) {
        logger.info("createEntity {}", model);
        return repository.save(model);
    }

    @Override
    public TransactionModel getEntity(UUID id) {
        logger.info("getEntity {}", id);
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
        logger.info("getEntities");
        return StreamSupport
                .stream(repository.findAll().spliterator(), true)
                .collect(Collectors.toList());
    }

    @Override
    public UUID updateEntity(UUID id, TransactionModel model) {
        logger.info("updateEntity {} {}", id, model);
        TransactionModel transactionModel = getEntity(id);
        transactionModel.setValue(model.getValue());

        return repository.save(transactionModel).getId();
    }

    @Override
    public UUID delete(UUID id) {
        logger.info("delete {} ", id);
        repository.deleteById(id);
        return id;
    }
}
