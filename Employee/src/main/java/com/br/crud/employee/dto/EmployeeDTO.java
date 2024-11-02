package com.br.crud.employee.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.util.UUID;

public record EmployeeDTO(
        UUID id,
        @NotBlank(message = "O nome é obrigatório")
        @Size(min = 2, max = 30, message = "O nome deve ter entre 2 e 30 caracteres")
        String name,

        @NotBlank(message = "O sobrenome é obrigatório")
        @Size(min = 2, max = 50, message = "O sobrenome deve ter entre 2 e 50 caracteres")
        String surname,

        @NotBlank(message = "O e-mail é obrigatório")
        @Email(message = "E-mail deve ser válido")
        String email,

        @NotBlank(message = "O Número do NIS (PIS) é obrigatório")
        @Pattern(regexp = "\\d+", message = "O NIS deve conter apenas números")
        @Size(min = 11, max = 11, message = "O NIS deve ter 11 dígitos")
        String nis
) {
}
