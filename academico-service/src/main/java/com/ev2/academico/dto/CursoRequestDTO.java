package com.ev2.academico.dto;

import jakarta.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CursoRequestDTO {

    @NotBlank
    private String nombre;

    @NotBlank
    private String nivel;

    @NotBlank
    private String seccion;
}