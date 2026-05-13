package com.ev2.usuarios.service;

import java.util.List;

import com.ev2.usuarios.dto.RolRequestDTO;
import com.ev2.usuarios.dto.RolResponseDTO;

public interface RolService {

    RolResponseDTO guardar(RolRequestDTO rolRequestDTO);

    List<RolResponseDTO> listarTodos();
}