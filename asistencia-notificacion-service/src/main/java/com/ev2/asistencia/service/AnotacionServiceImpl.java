package com.ev2.asistencia.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.ev2.asistencia.dto.AnotacionRequestDTO;
import com.ev2.asistencia.dto.AnotacionResponseDTO;
import com.ev2.asistencia.model.Anotacion;
import com.ev2.asistencia.repository.AnotacionRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AnotacionServiceImpl implements AnotacionService {

    private final AnotacionRepository anotacionRepository;

    @Override
    public AnotacionResponseDTO guardar(AnotacionRequestDTO anotacionRequestDTO) {

        Anotacion anotacion = new Anotacion();

        anotacion.setEstudianteId(anotacionRequestDTO.getEstudianteId());
        anotacion.setDescripcion(anotacionRequestDTO.getDescripcion());
        anotacion.setTipo(anotacionRequestDTO.getTipo());

        anotacion.setFechaCreacion(LocalDateTime.now());

        Anotacion anotacionGuardada = anotacionRepository.save(anotacion);

        return convertirADTO(anotacionGuardada);
    }

    private AnotacionResponseDTO convertirADTO(Anotacion anotacion) {

        AnotacionResponseDTO dto = new AnotacionResponseDTO();

        dto.setId(anotacion.getId());
        dto.setEstudianteId(anotacion.getEstudianteId());
        dto.setDescripcion(anotacion.getDescripcion());
        dto.setFechaCreacion(anotacion.getFechaCreacion());
        dto.setTipo(anotacion.getTipo());

        return dto;
    }
}