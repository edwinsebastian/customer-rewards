package com.infogain.demo.repository;

import com.infogain.demo.model.TransactionModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TransactionRepository extends CrudRepository<TransactionModel, UUID> {
}
