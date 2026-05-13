package com.ev2.asistencia.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ev2.asistencia.dto.NotaRequestDTO;
import com.ev2.asistencia.dto.NotaResponseDTO;
import com.ev2.asistencia.model.Nota;
import com.ev2.asistencia.repository.NotaRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NotaServiceImpl
        implements NotaService {

    private final NotaRepository notaRepository;

    @Override
    public NotaResponseDTO guardar(
            NotaRequestDTO notaRequestDTO) {

        Nota nota = new Nota();

        nota.setEstudianteId(
                notaRequestDTO.getEstudianteId());

        nota.setAsignaturaId(
                notaRequestDTO.getAsignaturaId());

        nota.setNota(
                notaRequestDTO.getNota());

        nota.setDescripcion(
                notaRequestDTO.getDescripcion());

        nota.setFechaRegistro(
                LocalDateTime.now());

        Nota notaGuardada =
                notaRepository.save(nota);

        return convertirADTO(notaGuardada);
    }

    @Override
    public List<NotaResponseDTO> listarPorEstudiante(
            Long estudianteId) {

        return notaRepository
                .findByEstudianteId(estudianteId)
                .stream()
                .map(this::convertirADTO)
                .toList();
    }

    private NotaResponseDTO convertirADTO(
            Nota nota) {

        NotaResponseDTO dto =
                new NotaResponseDTO();

        dto.setId(nota.getId());
        dto.setEstudianteId(nota.getEstudianteId());
        dto.setAsignaturaId(nota.getAsignaturaId());
        dto.setNota(nota.getNota());
        dto.setDescripcion(nota.getDescripcion());
        dto.setFechaRegistro(nota.getFechaRegistro());

        return dto;
    }
}