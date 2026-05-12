package com.ev2.academico.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ev2.academico.dto.PeriodoAcademicoRequestDTO;
import com.ev2.academico.dto.PeriodoAcademicoResponseDTO;
import com.ev2.academico.model.PeriodoAcademico;
import com.ev2.academico.repository.PeriodoAcademicoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PeriodoAcademicoServiceImpl implements PeriodoAcademicoService {

    private final PeriodoAcademicoRepository periodoAcademicoRepository;

    @Override
    public PeriodoAcademicoResponseDTO guardar(PeriodoAcademicoRequestDTO periodoAcademicoRequestDTO) {

        PeriodoAcademico periodoAcademico = new PeriodoAcademico();

        periodoAcademico.setNombre(periodoAcademicoRequestDTO.getNombre());
        periodoAcademico.setFechaInicio(periodoAcademicoRequestDTO.getFechaInicio());
        periodoAcademico.setFechaTermino(periodoAcademicoRequestDTO.getFechaTermino());
        periodoAcademico.setActivo(periodoAcademicoRequestDTO.getActivo());

        PeriodoAcademico periodoAcademicoGuardado = periodoAcademicoRepository.save(periodoAcademico);

        return convertirADTO(periodoAcademicoGuardado);
    }

    @Override
    public List<PeriodoAcademicoResponseDTO> listarTodos() {

        return periodoAcademicoRepository.findAll()
                .stream()
                .map(this::convertirADTO)
                .toList();
    }

    @Override
    public List<PeriodoAcademicoResponseDTO> listarActivos() {

        return periodoAcademicoRepository.findByActivoTrue()
                .stream()
                .map(this::convertirADTO)
                .toList();
    }

    @Override
    public PeriodoAcademicoResponseDTO buscarPorId(Long id) {

        PeriodoAcademico periodoAcademico = periodoAcademicoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Periodo academico no encontrado"));

        return convertirADTO(periodoAcademico);
    }

    @Override
    public PeriodoAcademicoResponseDTO actualizar(
            Long id,
            PeriodoAcademicoRequestDTO periodoAcademicoRequestDTO) {

        PeriodoAcademico periodoAcademico = periodoAcademicoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Periodo academico no encontrado"));

        periodoAcademico.setNombre(periodoAcademicoRequestDTO.getNombre());
        periodoAcademico.setFechaInicio(periodoAcademicoRequestDTO.getFechaInicio());
        periodoAcademico.setFechaTermino(periodoAcademicoRequestDTO.getFechaTermino());
        periodoAcademico.setActivo(periodoAcademicoRequestDTO.getActivo());

        PeriodoAcademico periodoAcademicoActualizado = periodoAcademicoRepository.save(periodoAcademico);

        return convertirADTO(periodoAcademicoActualizado);
    }

    @Override
    public void eliminar(Long id) {

        PeriodoAcademico periodoAcademico = periodoAcademicoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Periodo academico no encontrado"));

        periodoAcademicoRepository.delete(periodoAcademico);
    }

    private PeriodoAcademicoResponseDTO convertirADTO(PeriodoAcademico periodoAcademico) {

        PeriodoAcademicoResponseDTO dto = new PeriodoAcademicoResponseDTO();

        dto.setId(periodoAcademico.getId());
        dto.setNombre(periodoAcademico.getNombre());
        dto.setFechaInicio(periodoAcademico.getFechaInicio());
        dto.setFechaTermino(periodoAcademico.getFechaTermino());
        dto.setActivo(periodoAcademico.getActivo());

        return dto;
    }
}