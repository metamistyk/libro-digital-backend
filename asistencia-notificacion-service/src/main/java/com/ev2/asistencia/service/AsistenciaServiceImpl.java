package com.ev2.asistencia.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.ev2.asistencia.dto.AsistenciaRequestDTO;
import com.ev2.asistencia.dto.AsistenciaResponseDTO;
import com.ev2.asistencia.model.Asistencia;
import com.ev2.asistencia.repository.AsistenciaRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AsistenciaServiceImpl implements AsistenciaService {

    private final AsistenciaRepository asistenciaRepository;

    @Override
    public AsistenciaResponseDTO guardar(AsistenciaRequestDTO asistenciaRequestDTO) {

        Asistencia asistencia = new Asistencia();

        asistencia.setEstudianteId(asistenciaRequestDTO.getEstudianteId());
        asistencia.setEstado(asistenciaRequestDTO.getEstado());
        asistencia.setFechaHora(LocalDateTime.now());

        Asistencia asistenciaGuardada = asistenciaRepository.save(asistencia);

        return convertirADTO(asistenciaGuardada);
    }

    private AsistenciaResponseDTO convertirADTO(Asistencia asistencia) {

        AsistenciaResponseDTO dto = new AsistenciaResponseDTO();

        dto.setId(asistencia.getId());
        dto.setEstudianteId(asistencia.getEstudianteId());
        dto.setFechaHora(asistencia.getFechaHora());
        dto.setEstado(asistencia.getEstado());

        return dto;
    }
}