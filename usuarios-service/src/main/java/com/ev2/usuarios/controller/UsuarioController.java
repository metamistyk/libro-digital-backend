package com.ev2.usuarios.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ev2.usuarios.dto.UsuarioRequestDTO;
import com.ev2.usuarios.dto.UsuarioResponseDTO;
import com.ev2.usuarios.service.UsuarioService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping
    public UsuarioResponseDTO guardar(@RequestBody UsuarioRequestDTO usuarioRequestDTO) {
        return usuarioService.guardar(usuarioRequestDTO);
    }

    @GetMapping
    public List<UsuarioResponseDTO> listarTodos() {
        return usuarioService.listarTodos();
    }
}