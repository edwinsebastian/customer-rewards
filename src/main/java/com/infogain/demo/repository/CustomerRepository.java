package com.infogain.demo.repository;

import com.infogain.demo.model.CustomerModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CustomerRepository extends CrudRepository<CustomerModel, UUID> {
    Optional<CustomerModel> getCustomerModelByPersonalIdIsLike(String personalId);
}
