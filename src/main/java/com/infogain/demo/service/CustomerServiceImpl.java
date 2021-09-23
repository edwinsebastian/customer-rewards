package com.infogain.demo.service;

import com.infogain.demo.exception.ResourceNotFoundException;
import com.infogain.demo.model.CustomerModel;
import com.infogain.demo.model.TransactionRewardDTO;
import com.infogain.demo.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RequiredArgsConstructor
@Service
public class CustomerServiceImpl implements ICrudService<CustomerModel> {
    private final CustomerRepository repository;
    private final RewardService rewardService;

    Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);

    @Override
    public CustomerModel createEntity(CustomerModel model) {
        logger.info("createEntity {}", model);
        return repository.save(model);
    }

    @Override
    public CustomerModel getEntity(UUID id) {
        logger.info("getEntity {}", id);
        CustomerModel customerModel = null;
        try {
            customerModel = repository.findById(id).get();
        }catch (NoSuchElementException ex){
            throw new ResourceNotFoundException("Customer not found.");
        }

        return customerModel;
    }

    @Override
    public List<CustomerModel> getEntities() {
        logger.info("getEntities");
        return StreamSupport
                .stream(repository.findAll().spliterator(), true)
                .collect(Collectors.toList());
    }

    @Override
    public UUID updateEntity(UUID id, CustomerModel model) {
        logger.info("updateEntity {} {}", id, model);
        model.setId(id);
        return repository.save(model).getId();
    }

    @Override
    public UUID deleteEntity(UUID id) {
        logger.info("delete {} ", id);
        //validate if customer exist
        this.getEntity(id);
        repository.deleteById(id);
        return id;
    }

    public CustomerModel getCustomerByPersonalId(String personalId){
        logger.info("getCustomerByPersonalId {} ", personalId);
        return repository.getCustomerModelByPersonalIdIsLike(personalId).get();
    }

    public UUID getIdByPersonalId(String personalId){
        logger.info("getIdByPersonalId {} ", personalId);
        return getCustomerByPersonalId(personalId).getId();
    }

    public List<TransactionRewardDTO> getCustomerPointsReward(@PathVariable UUID customerId, @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date fromDate, @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date toDate){
        logger.info("getCustomerPointsReward param: {} {} {}", customerId, fromDate, toDate);
        //validate if customer exist
        this.getEntity(customerId);

        return rewardService.getTransactionsFromCustomerBetweenDates(customerId, fromDate, toDate);
    }
}
