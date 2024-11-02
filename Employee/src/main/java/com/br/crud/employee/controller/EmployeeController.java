package com.br.crud.employee.controller;

import com.br.crud.employee.exception.BusinessException;
import com.br.crud.employee.model.Employee;
import com.br.crud.employee.service.EmployeeService;
import com.br.crud.employee.dto.EmployeeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("api/employees")
public class EmployeeController extends GenericController<Employee, UUID, EmployeeDTO> {

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        super(employeeService);
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<String> handleCustomException(BusinessException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }
}
