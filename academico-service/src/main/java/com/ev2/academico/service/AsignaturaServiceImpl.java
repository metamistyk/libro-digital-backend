package com.ev2.academico.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ev2.academico.dto.AsignaturaRequestDTO;
import com.ev2.academico.dto.AsignaturaResponseDTO;
import com.ev2.academico.model.Asignatura;
import com.ev2.academico.model.Curso;
import com.ev2.academico.repository.AsignacionDocenteRepository;
import com.ev2.academico.repository.AsignaturaRepository;
import com.ev2.academico.repository.CursoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AsignaturaServiceImpl implements AsignaturaService {

    private final AsignaturaRepository asignaturaRepository;
    private final CursoRepository cursoRepository;
    private final AsignacionDocenteRepository asignacionDocenteRepository;

    @Override
    public AsignaturaResponseDTO guardar(AsignaturaRequestDTO asignaturaRequestDTO) {

        Curso curso = cursoRepository.findById(asignaturaRequestDTO.getCursoId())
                .orElseThrow(() -> new RuntimeException("Curso no encontrado"));

        Asignatura asignatura = new Asignatura();

        asignatura.setNombre(asignaturaRequestDTO.getNombre());
        asignatura.setCodigo(asignaturaRequestDTO.getCodigo());
        asignatura.setCurso(curso);

        Asignatura asignaturaGuardada = asignaturaRepository.save(asignatura);

        return convertirADTO(asignaturaGuardada);
    }

    @Override
    public List<AsignaturaResponseDTO> listarTodas() {

        return asignaturaRepository.findAll()
                .stream()
                .map(this::convertirADTO)
                .toList();
    }

    @Override
    public List<AsignaturaResponseDTO> listarPorCurso(Long cursoId) {

        return asignaturaRepository.findByCursoId(cursoId)
                .stream()
                .map(this::convertirADTO)
                .toList();
    }

    @Override
    public AsignaturaResponseDTO buscarPorId(Long id) {

        Asignatura asignatura = asignaturaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Asignatura no encontrada"));

        return convertirADTO(asignatura);
    }

    @Override
    public AsignaturaResponseDTO actualizar(Long id, AsignaturaRequestDTO asignaturaRequestDTO) {

        Asignatura asignatura = asignaturaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Asignatura no encontrada"));

        Curso curso = cursoRepository.findById(asignaturaRequestDTO.getCursoId())
                .orElseThrow(() -> new RuntimeException("Curso no encontrado"));

        asignatura.setNombre(asignaturaRequestDTO.getNombre());
        asignatura.setCodigo(asignaturaRequestDTO.getCodigo());
        asignatura.setCurso(curso);

        Asignatura asignaturaActualizada = asignaturaRepository.save(asignatura);

        return convertirADTO(asignaturaActualizada);
    }

    @Override
    public void eliminar(Long id) {

        Asignatura asignatura = asignaturaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Asignatura no encontrada"));

        boolean tieneAsignacionesDocentes = !asignacionDocenteRepository.findByAsignaturaId(id).isEmpty();

        if (tieneAsignacionesDocentes) {
            throw new RuntimeException("No se puede eliminar la asignatura porque tiene asignaciones docentes asociadas");
        }

        asignaturaRepository.delete(asignatura);
    }

    private AsignaturaResponseDTO convertirADTO(Asignatura asignatura) {

        AsignaturaResponseDTO dto = new AsignaturaResponseDTO();

        dto.setId(asignatura.getId());
        dto.setNombre(asignatura.getNombre());
        dto.setCodigo(asignatura.getCodigo());
        dto.setCursoId(asignatura.getCurso().getId());
        dto.setNombreCurso(asignatura.getCurso().getNombre());

        return dto;
    }
}