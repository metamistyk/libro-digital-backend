package com.ev2.usuarios.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ev2.usuarios.dto.ApoderadoRequestDTO;
import com.ev2.usuarios.dto.ApoderadoResponseDTO;
import com.ev2.usuarios.service.ApoderadoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/apoderados")
@RequiredArgsConstructor
public class ApoderadoController {

    private final ApoderadoService apoderadoService;

    @PostMapping
    public ApoderadoResponseDTO guardar(@RequestBody ApoderadoRequestDTO apoderadoRequestDTO) {
        return apoderadoService.guardar(apoderadoRequestDTO);
    }
}