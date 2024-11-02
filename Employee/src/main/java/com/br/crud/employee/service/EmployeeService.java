package com.br.crud.employee.service;

import com.br.crud.employee.dto.EmployeeDTO;
import com.br.crud.employee.model.Employee;

import java.util.UUID;

public interface EmployeeService extends GenericService<Employee, UUID, EmployeeDTO> {

}
