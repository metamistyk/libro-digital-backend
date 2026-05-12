package com.ev2.academico.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AsignaturaRequestDTO {

    private String nombre;

    private String codigo;

    private Long cursoId;
}