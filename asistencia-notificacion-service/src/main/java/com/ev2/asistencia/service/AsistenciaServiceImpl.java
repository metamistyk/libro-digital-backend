package com.ev2.asistencia.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ev2.asistencia.dto.AsistenciaRequestDTO;
import com.ev2.asistencia.dto.AsistenciaResponseDTO;
import com.ev2.asistencia.event.AsistenciaRegistradaEvent;
import com.ev2.asistencia.kafka.KafkaProducerService;
import com.ev2.asistencia.model.Asistencia;
import com.ev2.asistencia.repository.AsistenciaRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AsistenciaServiceImpl implements AsistenciaService {

    private final AsistenciaRepository asistenciaRepository;
    private final KafkaProducerService kafkaProducerService;

    @Override
    public AsistenciaResponseDTO guardar(AsistenciaRequestDTO asistenciaRequestDTO) {

        Asistencia asistencia = new Asistencia();

        asistencia.setEstudianteId(asistenciaRequestDTO.getEstudianteId());
        asistencia.setEstado(asistenciaRequestDTO.getEstado());
        asistencia.setFechaHora(LocalDateTime.now());

        Asistencia asistenciaGuardada = asistenciaRepository.save(asistencia);

        AsistenciaRegistradaEvent event = new AsistenciaRegistradaEvent(
                asistenciaGuardada.getId(),
                asistenciaGuardada.getEstudianteId(),
                asistenciaGuardada.getFechaHora(),
                asistenciaGuardada.getEstado().name());

        kafkaProducerService.enviarAsistenciaRegistrada(event);

        return convertirADTO(asistenciaGuardada);
    }

    @Override
    public List<AsistenciaResponseDTO> listarPorEstudiante(Long estudianteId) {

        return asistenciaRepository.findByEstudianteId(estudianteId)
                .stream()
                .map(this::convertirADTO)
                .toList();
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