package com.ev2.asistencia.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class NotaRequestDTO {

    @NotNull
    private Long estudianteId;

    @NotNull
    private Long asignaturaId;

    @NotNull
    @Min(1)
    @Max(7)
    private Double nota;

    private String descripcion;
}