package com.br.crud.employee.service;

import com.br.crud.employee.dto.EmployeeDTO;
import com.br.crud.employee.exception.BusinessException;
import com.br.crud.employee.model.Employee;
import com.br.crud.employee.repository.EmployeeRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class EmployeeServiceImpl extends GenericServiceImpl<Employee, UUID, EmployeeDTO> implements EmployeeService {

    final EmployeeRepository employeeRepository;

    protected EmployeeServiceImpl(JpaRepository<Employee, UUID> repository, EmployeeRepository employeeRepository) {
        super(repository);
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee convertToModel(EmployeeDTO dto, UUID uuid) {
        Employee employee = new Employee();

        BeanUtils.copyProperties(dto, employee);

        if (uuid != null) {
            employee.setId(uuid);
        }
        return employee;
    }

    @Override
    public  Employee save(EmployeeDTO employeeDTO) throws BusinessException{
        if (employeeRepository.existsByNis(employeeDTO.nis())) {
            throw new BusinessException("NIS já cadastrado!");
        } else if (employeeRepository.existsByEmail(employeeDTO.email())) {
            throw new BusinessException("E-mail já cadastrado!");
        }

        Employee employee = convertToModel(employeeDTO, null);
        return employeeRepository.save(employee);
    }
}
