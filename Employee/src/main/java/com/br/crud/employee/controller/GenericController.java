package com.br.crud.employee.controller;

import com.br.crud.employee.service.GenericService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@Controller
public abstract class GenericController<T, ID, D> {

    private final GenericService<T, ID, D> genericService;

    @Autowired
    public GenericController(GenericService<T, ID, D> genericService) {
        this.genericService = genericService;
    }

    @PostMapping
    public ResponseEntity<T> save(@RequestBody @Valid D item) throws Exception {

        T createdItem = genericService.save(item);
        return new ResponseEntity<>(createdItem, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<T> getById(@PathVariable ID id) {
        Optional<T> item = genericService.getById(id);
        return item.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<T>> getAll() {
        List<T> items = genericService.getAll();
        return new ResponseEntity<>(items, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<T> update(@PathVariable ID id, @RequestBody @Valid D updatedItem)  throws Exception {
        T item = genericService.update(id, updatedItem);
        return new ResponseEntity<>(item, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable ID id) throws RuntimeException {
        boolean deleted = genericService.delete(id);
        return deleted ? ResponseEntity.ok("Item removido com sucesso!") : ResponseEntity.notFound().build();
    }
}
