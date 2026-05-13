package com.ev2.usuarios.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.ev2.usuarios.dto.UsuarioRequestDTO;
import com.ev2.usuarios.dto.UsuarioResponseDTO;
import com.ev2.usuarios.service.UsuarioService;

import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping
    public UsuarioResponseDTO guardar(
            @Valid @RequestBody
            UsuarioRequestDTO usuarioRequestDTO) {

        return usuarioService.guardar(
                usuarioRequestDTO);
    }

    @GetMapping
    public List<UsuarioResponseDTO> listarTodos() {

        return usuarioService.listarTodos();
    }
}