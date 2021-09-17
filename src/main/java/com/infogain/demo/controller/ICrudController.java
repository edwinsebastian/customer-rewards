package com.infogain.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

public interface ICrudController<T> {

    @PostMapping
    ResponseEntity<T> createCustomer(@RequestBody T model);

    @GetMapping("/{id}")
    ResponseEntity<T> getCustomer(@PathVariable UUID id);

    @GetMapping
    ResponseEntity<List<T>> getCostumers();

    @PutMapping("/{id}")
    ResponseEntity<UUID> updateCustomer(@PathVariable UUID id, @RequestBody T customerModel);
}
