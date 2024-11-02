package com.br.crud.employee.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.io.Serial;
import java.util.UUID;

@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "employee")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Employee  extends  GenericModel{
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private UUID id;

    @NotBlank(message = "O nome é obrigatório")
    @Size(min = 2, max = 30, message = "O nome deve ter entre 2 e 30 caracteres")
    @Column(length = 30, nullable = false)
    private String name;

    @NotBlank(message = "O sobrenome é obrigatório")
    @Size(min = 2, max = 50, message = "O sobrenome deve ter entre 2 e 50 caracteres")
    @Column(length = 50, nullable = false)
    private String surname;

    @NotBlank(message = "O e-mail é obrigatório")
    @Email(message = "E-mail deve ser válido")
    @Column(unique = true, nullable = false)
    private String email;

    @NotBlank(message = "O Número do NIS (PIS) é obrigatório")
    @Pattern(regexp = "\\d+", message = "O NIS deve conter apenas números")
    @Size(min = 11, max = 11, message = "O NIS deve ter 11 dígitos")
    @Column(length = 11, unique = true, nullable = false)
    private String nis;
}
