package com.br.crud.employee.service;

import com.br.crud.employee.dto.EmployeeDTO;
import com.br.crud.employee.exception.BusinessException;
import com.br.crud.employee.model.Employee;
import com.br.crud.employee.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EmployeeServiceTest {

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    @Mock
    private EmployeeRepository employeeRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveThrowsBusinessExceptionWhenNisExists() {
        EmployeeDTO employeeDTO = new EmployeeDTO(UUID.randomUUID(), "John", "Doe", "john.doe@example.com", "12345678901");

        when(employeeRepository.existsByNis(employeeDTO.nis())).thenReturn(true);

        BusinessException thrown = assertThrows(BusinessException.class, () -> {
            employeeService.save(employeeDTO);
        });

        assertEquals("NIS já cadastrado!", thrown.getMessage());
        verify(employeeRepository, times(1)).existsByNis(employeeDTO.nis());
        verify(employeeRepository, times(0)).save(any());
    }

    @Test
    void testSaveThrowsBusinessExceptionWhenEmailExists() {
        EmployeeDTO employeeDTO = new EmployeeDTO(UUID.randomUUID(), "John", "Doe", "john.doe@example.com", "12345678901");

        when(employeeRepository.existsByNis(employeeDTO.nis())).thenReturn(false);
        when(employeeRepository.existsByEmail(employeeDTO.email())).thenReturn(true);

        BusinessException thrown = assertThrows(BusinessException.class, () -> {
            employeeService.save(employeeDTO);
        });

        assertEquals("E-mail já cadastrado!", thrown.getMessage());
        verify(employeeRepository, times(1)).existsByNis(employeeDTO.nis());
        verify(employeeRepository, times(1)).existsByEmail(employeeDTO.email());
        verify(employeeRepository, times(0)).save(any());
    }

    @Test
    void testUpdateThrowsBusinessExceptionWhenNisExists() {
        UUID employeeId = UUID.randomUUID();
        EmployeeDTO employeeDTO = new EmployeeDTO(employeeId, "John", "Doe", "john.doe@example.com", "12345678901");

        when(employeeRepository.existsByNis(employeeDTO.nis())).thenReturn(true);

        BusinessException thrown = assertThrows(BusinessException.class, () -> {
            employeeService.update(employeeId, employeeDTO);
        });

        assertEquals("NIS já cadastrado!", thrown.getMessage());
        verify(employeeRepository, times(1)).existsByNis(employeeDTO.nis());
        verify(employeeRepository, times(0)).save(any());
    }

    @Test
    void testUpdateThrowsBusinessExceptionWhenEmailExists() {
        UUID employeeId = UUID.randomUUID();
        EmployeeDTO employeeDTO = new EmployeeDTO(employeeId, "John", "Doe", "john.doe@example.com", "12345678901");

        when(employeeRepository.existsByNis(employeeDTO.nis())).thenReturn(false);
        when(employeeRepository.existsByEmail(employeeDTO.email())).thenReturn(true);

        BusinessException thrown = assertThrows(BusinessException.class, () -> {
            employeeService.update(employeeId, employeeDTO);
        });

        assertEquals("E-mail já cadastrado!", thrown.getMessage());
        verify(employeeRepository, times(1)).existsByNis(employeeDTO.nis());
        verify(employeeRepository, times(1)).existsByEmail(employeeDTO.email());
        verify(employeeRepository, times(0)).save(any());
    }
}
