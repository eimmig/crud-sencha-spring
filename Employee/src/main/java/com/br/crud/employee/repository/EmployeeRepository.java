package com.br.crud.employee.repository;

import com.br.crud.employee.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, UUID> {
    boolean existsByNis(String nis);
    boolean existsByEmail(String email);
}
