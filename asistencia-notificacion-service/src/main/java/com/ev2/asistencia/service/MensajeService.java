package com.ev2.asistencia.service;

import java.util.List;

import com.ev2.asistencia.dto.MensajeRequestDTO;
import com.ev2.asistencia.dto.MensajeResponseDTO;

public interface MensajeService {

    MensajeResponseDTO enviar(MensajeRequestDTO dto);

    List<MensajeResponseDTO> obtenerRecibidos(Long destinatarioId);

    List<MensajeResponseDTO> obtenerConversacion(Long usuarioId1, Long usuarioId2);
}