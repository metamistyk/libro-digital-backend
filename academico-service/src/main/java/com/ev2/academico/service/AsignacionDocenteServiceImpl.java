package com.ev2.academico.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ev2.academico.dto.AsignacionDocenteRequestDTO;
import com.ev2.academico.dto.AsignacionDocenteResponseDTO;
import com.ev2.academico.model.AsignacionDocente;
import com.ev2.academico.model.Asignatura;
import com.ev2.academico.model.Curso;
import com.ev2.academico.model.PeriodoAcademico;
import com.ev2.academico.repository.AsignacionDocenteRepository;
import com.ev2.academico.repository.AsignaturaRepository;
import com.ev2.academico.repository.CursoRepository;
import com.ev2.academico.repository.PeriodoAcademicoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AsignacionDocenteServiceImpl implements AsignacionDocenteService {

    private final AsignacionDocenteRepository asignacionDocenteRepository;
    private final CursoRepository cursoRepository;
    private final AsignaturaRepository asignaturaRepository;
    private final PeriodoAcademicoRepository periodoAcademicoRepository;

    @Override
    public AsignacionDocenteResponseDTO guardar(AsignacionDocenteRequestDTO asignacionDocenteRequestDTO) {

        Curso curso = cursoRepository.findById(asignacionDocenteRequestDTO.getCursoId())
                .orElseThrow(() -> new RuntimeException("Curso no encontrado"));

        Asignatura asignatura = asignaturaRepository.findById(asignacionDocenteRequestDTO.getAsignaturaId())
                .orElseThrow(() -> new RuntimeException("Asignatura no encontrada"));

        PeriodoAcademico periodoAcademico = periodoAcademicoRepository
                .findById(asignacionDocenteRequestDTO.getPeriodoAcademicoId())
                .orElseThrow(() -> new RuntimeException("Periodo academico no encontrado"));

        AsignacionDocente asignacionDocente = new AsignacionDocente();

        asignacionDocente.setDocenteId(asignacionDocenteRequestDTO.getDocenteId());
        asignacionDocente.setCurso(curso);
        asignacionDocente.setAsignatura(asignatura);
        asignacionDocente.setPeriodoAcademico(periodoAcademico);

        AsignacionDocente asignacionDocenteGuardada = asignacionDocenteRepository.save(asignacionDocente);

        return convertirADTO(asignacionDocenteGuardada);
    }

    @Override
    public List<AsignacionDocenteResponseDTO> listarTodas() {

        return asignacionDocenteRepository.findAll()
                .stream()
                .map(this::convertirADTO)
                .toList();
    }

    @Override
    public List<AsignacionDocenteResponseDTO> listarPorDocente(Long docenteId) {

        return asignacionDocenteRepository.findByDocenteId(docenteId)
                .stream()
                .map(this::convertirADTO)
                .toList();
    }

    @Override
    public List<AsignacionDocenteResponseDTO> listarPorCurso(Long cursoId) {

        return asignacionDocenteRepository.findByCursoId(cursoId)
                .stream()
                .map(this::convertirADTO)
                .toList();
    }

    @Override
    public AsignacionDocenteResponseDTO buscarPorId(Long id) {

        AsignacionDocente asignacionDocente = asignacionDocenteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Asignacion docente no encontrada"));

        return convertirADTO(asignacionDocente);
    }

    @Override
    public AsignacionDocenteResponseDTO actualizar(
            Long id,
            AsignacionDocenteRequestDTO asignacionDocenteRequestDTO) {

        AsignacionDocente asignacionDocente = asignacionDocenteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Asignacion docente no encontrada"));

        Curso curso = cursoRepository.findById(asignacionDocenteRequestDTO.getCursoId())
                .orElseThrow(() -> new RuntimeException("Curso no encontrado"));

        Asignatura asignatura = asignaturaRepository.findById(asignacionDocenteRequestDTO.getAsignaturaId())
                .orElseThrow(() -> new RuntimeException("Asignatura no encontrada"));

        PeriodoAcademico periodoAcademico = periodoAcademicoRepository
                .findById(asignacionDocenteRequestDTO.getPeriodoAcademicoId())
                .orElseThrow(() -> new RuntimeException("Periodo academico no encontrado"));

        asignacionDocente.setDocenteId(asignacionDocenteRequestDTO.getDocenteId());
        asignacionDocente.setCurso(curso);
        asignacionDocente.setAsignatura(asignatura);
        asignacionDocente.setPeriodoAcademico(periodoAcademico);

        AsignacionDocente asignacionDocenteActualizada = asignacionDocenteRepository.save(asignacionDocente);

        return convertirADTO(asignacionDocenteActualizada);
    }

    @Override
    public void eliminar(Long id) {

        AsignacionDocente asignacionDocente = asignacionDocenteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Asignacion docente no encontrada"));

        asignacionDocenteRepository.delete(asignacionDocente);
    }

    private AsignacionDocenteResponseDTO convertirADTO(AsignacionDocente asignacionDocente) {

        AsignacionDocenteResponseDTO dto = new AsignacionDocenteResponseDTO();

        dto.setId(asignacionDocente.getId());

        dto.setDocenteId(asignacionDocente.getDocenteId());

        dto.setCursoId(asignacionDocente.getCurso().getId());
        dto.setNombreCurso(asignacionDocente.getCurso().getNombre());

        dto.setAsignaturaId(asignacionDocente.getAsignatura().getId());
        dto.setNombreAsignatura(asignacionDocente.getAsignatura().getNombre());

        dto.setPeriodoAcademicoId(asignacionDocente.getPeriodoAcademico().getId());
        dto.setNombrePeriodoAcademico(asignacionDocente.getPeriodoAcademico().getNombre());

        return dto;
    }
}