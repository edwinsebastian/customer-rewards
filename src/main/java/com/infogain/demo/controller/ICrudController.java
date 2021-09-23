package com.infogain.demo.controller;

import com.infogain.demo.model.Model;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

public interface ICrudController<T extends Model, R, S> {

    @PostMapping
    ResponseEntity<S> createResource(@RequestBody R model);

    @GetMapping("/{id}")
    ResponseEntity<T> getResource(@PathVariable UUID id);

    @GetMapping
    ResponseEntity<List<T>> getResources();

    @PutMapping("/{id}")
    ResponseEntity<S> updateResource(@PathVariable UUID id, @RequestBody R customerModel);

    @DeleteMapping("/{id}")
    ResponseEntity<S> deleteResource(@PathVariable UUID id);
}
