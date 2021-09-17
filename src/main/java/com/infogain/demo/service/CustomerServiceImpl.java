package com.infogain.demo.service;

import com.infogain.demo.model.CustomerModel;
import com.infogain.demo.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RequiredArgsConstructor
@Service
public class CustomerServiceImpl implements ICrudService<CustomerModel> {
    private final CustomerRepository repository;

    @Override
    public CustomerModel createEntity(CustomerModel model) {
        return repository.save(model);
    }

    @Override
    public CustomerModel getEntity(UUID id) {
        return repository.findById(id).get();
    }

    @Override
    public List<CustomerModel> getEntities() {
        return StreamSupport
                .stream(repository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    @Override
    public UUID updateEntity(UUID id, CustomerModel model) {
        model.setId(id);
        return repository.save(model).getId();
    }

    @Override
    public UUID delete(UUID id) {
        repository.deleteById(id);
        return id;
    }

    public CustomerModel getCustomerByPersonalId(String personalId){
        return repository.getCustomerModelByPersonalIdIsLike(personalId).get();
    }

    public UUID getIdByPersonalId(String personalId){
        return getCustomerByPersonalId(personalId).getId();
    }
}
