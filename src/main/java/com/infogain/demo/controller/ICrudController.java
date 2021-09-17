package com.infogain.demo.controller;

import com.infogain.demo.exception.BadArgumentsException;
import com.infogain.demo.model.Model;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

public interface ICrudController<T extends Model> {

    @PostMapping
    ResponseEntity<T> createResource(@RequestBody T model);

    @GetMapping("/{id}")
    ResponseEntity<T> getResource(@PathVariable UUID id);

    @GetMapping
    ResponseEntity<List<T>> getResources();

    @PutMapping("/{id}")
    ResponseEntity<UUID> updateResource(@PathVariable UUID id, @RequestBody T customerModel);

    default void preventUpdateThroughPost(T model){
        if (model.getId() != null) {
            throw new BadArgumentsException("To create this resource id property must be null.");
        }
    }
}
