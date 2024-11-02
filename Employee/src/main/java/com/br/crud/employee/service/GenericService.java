package com.br.crud.employee.service;

import java.util.List;
import java.util.Optional;

public interface GenericService<T, ID, D> {

    T save(D dto) throws Exception;

    List<T> getAll();

    Optional<T> getById(ID id);

    boolean delete(ID id) throws RuntimeException;

    T update(ID id, D dto) throws RuntimeException;

    T convertToModel(D dto, ID id);
}
