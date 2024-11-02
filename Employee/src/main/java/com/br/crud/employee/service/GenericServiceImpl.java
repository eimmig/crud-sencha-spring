package com.br.crud.employee.service;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public abstract class GenericServiceImpl<T, ID, D> implements GenericService<T, ID, D> {

    protected final JpaRepository<T, ID> repository;

    protected GenericServiceImpl(JpaRepository<T, ID> repository) {
        this.repository = repository;
    }

    @Override
    public abstract T convertToModel(D dto, ID id);

    // Salva um novo item a partir do DTO
    @Override
    @Transactional
    public T save(D dto) throws Exception {
        T model = convertToModel(dto, null);
        return repository.save(model);
    }

    // Obtém todos os itens
    @Override
    public List<T> getAll() {
        return repository.findAll();
    }

    // Obtém um item pelo ID
    @Override
    public Optional<T> getById(ID id) {
        return repository.findById(id);
    }

    // Exclui um item pelo ID
    @Override
    @Transactional
    public boolean delete(ID id) throws RuntimeException {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        throw new RuntimeException("Item não encontrado");
    }

    // Atualiza um item pelo ID, a partir do DTO
    @Override
    @Transactional
    public T update(ID id, D dto) throws RuntimeException {
        if (repository.existsById(id)) {
            T model = convertToModel(dto, id);
            return repository.save(model);
        }
        throw new RuntimeException("Item não encontrado");
    }
}
