package com.ev2.asistencia.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ev2.asistencia.dto.MensajeRequestDTO;
import com.ev2.asistencia.dto.MensajeResponseDTO;
import com.ev2.asistencia.model.Mensaje;
import com.ev2.asistencia.repository.MensajeRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MensajeServiceImpl implements MensajeService {

    private final MensajeRepository mensajeRepository;

    @Override
    public MensajeResponseDTO enviar(MensajeRequestDTO dto) {

        Mensaje mensaje = new Mensaje();
        mensaje.setRemitenteId(dto.getRemitenteId());
        mensaje.setDestinatarioId(dto.getDestinatarioId());
        mensaje.setContenido(dto.getContenido());
        mensaje.setFechaEnvio(LocalDateTime.now());
        mensaje.setLeido(false);

        return convertirADTO(mensajeRepository.save(mensaje));
    }

    @Override
    public List<MensajeResponseDTO> obtenerRecibidos(Long destinatarioId) {

        return mensajeRepository
            .findByDestinatarioId(destinatarioId)
            .stream()
            .map(this::convertirADTO)
            .toList();
    }

    @Override
    public List<MensajeResponseDTO> obtenerConversacion(Long usuarioId1, Long usuarioId2) {

        return mensajeRepository
            .findByRemitenteIdAndDestinatarioIdOrRemitenteIdAndDestinatarioId(
                usuarioId1, usuarioId2,
                usuarioId2, usuarioId1)
            .stream()
            .map(this::convertirADTO)
            .toList();
    }

    private MensajeResponseDTO convertirADTO(Mensaje mensaje) {

        MensajeResponseDTO dto = new MensajeResponseDTO();
        dto.setId(mensaje.getId());
        dto.setRemitenteId(mensaje.getRemitenteId());
        dto.setDestinatarioId(mensaje.getDestinatarioId());
        dto.setContenido(mensaje.getContenido());
        dto.setFechaEnvio(mensaje.getFechaEnvio());
        dto.setLeido(mensaje.getLeido());

        return dto;
    }
}