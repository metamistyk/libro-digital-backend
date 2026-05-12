package com.ev2.academico.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AsignacionDocenteRequestDTO {

    private Long docenteId;

    private Long cursoId;

    private Long asignaturaId;

    private Long periodoAcademicoId;
}