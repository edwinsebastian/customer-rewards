package com.infogain.demo.service;

import com.infogain.demo.model.Model;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ICrudService<T extends Model> {

    T createEntity(T model);

    T getEntity(UUID id);

    List<T> getEntities();

    UUID updateEntity(UUID id, T model);

    UUID delete(UUID id);
}