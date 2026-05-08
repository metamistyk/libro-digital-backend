package com.ev2.usuarios.service;

import com.ev2.usuarios.dto.EstudianteRequestDTO;
import com.ev2.usuarios.dto.EstudianteResponseDTO;

public interface EstudianteService {

    EstudianteResponseDTO guardar(EstudianteRequestDTO estudianteRequestDTO);
}