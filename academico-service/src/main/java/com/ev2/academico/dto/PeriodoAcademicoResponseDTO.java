package com.ev2.academico.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PeriodoAcademicoResponseDTO {

    private Long id;

    private String nombre;

    private LocalDate fechaInicio;

    private LocalDate fechaTermino;

    private Boolean activo;
}