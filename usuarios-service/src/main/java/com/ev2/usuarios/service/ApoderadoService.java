package com.ev2.usuarios.service;

import com.ev2.usuarios.dto.ApoderadoRequestDTO;
import com.ev2.usuarios.dto.ApoderadoResponseDTO;

public interface ApoderadoService {

    ApoderadoResponseDTO guardar(ApoderadoRequestDTO apoderadoRequestDTO);
}