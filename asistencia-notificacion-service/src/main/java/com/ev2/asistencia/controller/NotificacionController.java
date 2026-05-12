package com.ev2.asistencia.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ev2.asistencia.dto.NotificacionRequestDTO;
import com.ev2.asistencia.dto.NotificacionResponseDTO;
import com.ev2.asistencia.service.NotificacionService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/notificaciones")
@RequiredArgsConstructor
public class NotificacionController {

    private final NotificacionService notificacionService;

    @PostMapping
    public NotificacionResponseDTO guardar(@RequestBody NotificacionRequestDTO notificacionRequestDTO) {
        return notificacionService.guardar(notificacionRequestDTO);
    }

    @GetMapping
    public List<NotificacionResponseDTO> listarTodas() {
        return notificacionService.listarTodas();
    }
}