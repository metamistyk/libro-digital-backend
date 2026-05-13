package com.ev2.academico.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PeriodoAcademicoRequestDTO {

    @NotBlank
    private String nombre;

    @NotNull
    private LocalDate fechaInicio;

    @NotNull
    private LocalDate fechaTermino;

    @NotNull
    private Boolean activo;
}