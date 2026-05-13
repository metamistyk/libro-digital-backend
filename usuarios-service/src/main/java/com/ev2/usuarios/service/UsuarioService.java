package com.ev2.usuarios.service;

import java.util.List;

import com.ev2.usuarios.dto.UsuarioRequestDTO;
import com.ev2.usuarios.dto.UsuarioResponseDTO;

public interface UsuarioService {

    UsuarioResponseDTO guardar(UsuarioRequestDTO usuarioRequestDTO);

    List<UsuarioResponseDTO> listarTodos();
}