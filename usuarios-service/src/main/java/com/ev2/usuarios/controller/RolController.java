package com.ev2.usuarios.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ev2.usuarios.dto.RolRequestDTO;
import com.ev2.usuarios.dto.RolResponseDTO;
import com.ev2.usuarios.service.RolService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/roles")
@RequiredArgsConstructor
public class RolController {

    private final RolService rolService;

    @PostMapping
    public RolResponseDTO guardar(@RequestBody RolRequestDTO rolRequestDTO) {
        return rolService.guardar(rolRequestDTO);
    }
}