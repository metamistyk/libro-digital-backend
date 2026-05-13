package com.ev2.academico.dto;

import jakarta.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AsignacionDocenteRequestDTO {

    @NotNull
    private Long docenteId;

    @NotNull
    private Long cursoId;

    @NotNull
    private Long asignaturaId;

    @NotNull
    private Long periodoAcademicoId;
}