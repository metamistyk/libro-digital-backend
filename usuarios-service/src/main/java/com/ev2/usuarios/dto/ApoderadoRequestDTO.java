package com.ev2.usuarios.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ApoderadoRequestDTO {

    private String nombre;

    private String apellido;

    private String email;

    private List<Long> estudiantesIds;
}