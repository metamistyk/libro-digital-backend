package com.ev2.asistencia.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ev2.asistencia.dto.MensajeRequestDTO;
import com.ev2.asistencia.dto.MensajeResponseDTO;
import com.ev2.asistencia.service.MensajeService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/mensajes")
@RequiredArgsConstructor
public class MensajeController {

    private final MensajeService mensajeService;

    @PostMapping
    public MensajeResponseDTO enviar(
            @Valid @RequestBody MensajeRequestDTO dto) {

        return mensajeService.enviar(dto);
    }

    @GetMapping("/recibidos")
    public List<MensajeResponseDTO> obtenerRecibidos(
            @RequestParam Long destinatarioId) {

        return mensajeService.obtenerRecibidos(destinatarioId);
    }

    @GetMapping("/conversacion")
    public List<MensajeResponseDTO> obtenerConversacion(
            @RequestParam Long usuarioId1,
            @RequestParam Long usuarioId2) {

        return mensajeService.obtenerConversacion(usuarioId1, usuarioId2);
    }
}