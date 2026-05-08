package com.ev2.academico.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CursoResponseDTO {

    private Long id;
    private String nombre;
    private String nivel;
    private String seccion;
}