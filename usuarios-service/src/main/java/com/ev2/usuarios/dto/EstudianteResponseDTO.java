package com.ev2.usuarios.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EstudianteResponseDTO {

    private Long id;

    private String nombre;

    private String apellido;

    private String email;

    private Long cursoId;
}