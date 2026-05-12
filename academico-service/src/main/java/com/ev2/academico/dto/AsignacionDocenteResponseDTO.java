package com.ev2.academico.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AsignacionDocenteResponseDTO {

    private Long id;

    private Long docenteId;

    private Long cursoId;

    private String nombreCurso;

    private Long asignaturaId;

    private String nombreAsignatura;

    private Long periodoAcademicoId;

    private String nombrePeriodoAcademico;
}