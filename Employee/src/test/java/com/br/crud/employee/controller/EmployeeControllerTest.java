package com.br.crud.employee.controller;

import com.br.crud.employee.dto.EmployeeDTO;
import com.br.crud.employee.model.Employee;
import com.br.crud.employee.service.EmployeeService;
import com.br.crud.employee.service.GenericService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class EmployeeControllerTest {

    @InjectMocks
    private EmployeeController employeeController;

    @Mock
    private EmployeeService employeeService;

    private MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(employeeController).build();
    }

    @Test
    void testSave() throws Exception {
        EmployeeDTO employeeDTO = new EmployeeDTO(UUID.randomUUID(), "John", "Doe", "john.doe@example.com", "12345678901");
        Employee employee = new Employee(employeeDTO.id(), employeeDTO.name(), employeeDTO.surname(), employeeDTO.email(), employeeDTO.nis());

        when(employeeService.save(any(EmployeeDTO.class))).thenReturn(employee);

        mockMvc.perform(post("/api/employees")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(employeeDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("John"));

        verify(employeeService, times(1)).save(any(EmployeeDTO.class));
    }

    @Test
    void testGetById() throws Exception {
        UUID employeeId = UUID.randomUUID();
        Employee employee = new Employee(employeeId, "John", "Doe", "john.doe@example.com", "12345678901");

        when(employeeService.getById(employeeId)).thenReturn(Optional.of(employee));

        mockMvc.perform(get("/api/employees/{id}", employeeId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("John"));

        verify(employeeService, times(1)).getById(employeeId);
    }

    @Test
    void testGetAll() throws Exception {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(UUID.randomUUID(), "John", "Doe", "john.doe@example.com", "12345678901"));
        employees.add(new Employee(UUID.randomUUID(), "Jane", "Doe", "jane.doe@example.com", "12345678902"));

        when(employeeService.getAll()).thenReturn(employees);

        mockMvc.perform(get("/api/employees"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));

        verify(employeeService, times(1)).getAll();
    }

    @Test
    void testUpdate() throws Exception {
        UUID employeeId = UUID.randomUUID();
        EmployeeDTO employeeDTO = new EmployeeDTO(employeeId, "John", "Doe", "john.doe@example.com", "12345678901");
        Employee employee = new Employee(employeeDTO.id(), employeeDTO.name(), employeeDTO.surname(), employeeDTO.email(), employeeDTO.nis());

        when(employeeService.update(any(UUID.class), any(EmployeeDTO.class))).thenReturn(employee);

        mockMvc.perform(put("/api/employees/{id}", employeeId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(employeeDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("John"));

        verify(employeeService, times(1)).update(eq(employeeId), any(EmployeeDTO.class));
    }

    @Test
    void testDelete() throws Exception {
        UUID employeeId = UUID.randomUUID();
        when(employeeService.delete(employeeId)).thenReturn(true);

        mockMvc.perform(delete("/api/employees/{id}", employeeId))
                .andExpect(status().isOk())
                .andExpect(content().string("Item removido com sucesso!"));

        verify(employeeService, times(1)).delete(employeeId);
    }
}
