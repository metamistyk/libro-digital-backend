package com.ev2.academico.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ev2.academico.dto.CursoRequestDTO;
import com.ev2.academico.dto.CursoResponseDTO;
import com.ev2.academico.model.Curso;
import com.ev2.academico.repository.AsignacionDocenteRepository;
import com.ev2.academico.repository.AsignaturaRepository;
import com.ev2.academico.repository.CursoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CursoServiceImpl implements CursoService {

    private final CursoRepository cursoRepository;
    private final AsignaturaRepository asignaturaRepository;
    private final AsignacionDocenteRepository asignacionDocenteRepository;

    @Override
    public CursoResponseDTO guardar(CursoRequestDTO cursoRequestDTO) {

        Curso curso = new Curso();

        curso.setNombre(cursoRequestDTO.getNombre());
        curso.setNivel(cursoRequestDTO.getNivel());
        curso.setSeccion(cursoRequestDTO.getSeccion());

        Curso cursoGuardado = cursoRepository.save(curso);

        return convertirADTO(cursoGuardado);
    }

    @Override
    public List<CursoResponseDTO> listarTodos() {

        return cursoRepository.findAll()
                .stream()
                .map(this::convertirADTO)
                .toList();
    }

    @Override
    public CursoResponseDTO buscarPorId(Long id) {

        Curso curso = cursoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Curso no encontrado"));

        return convertirADTO(curso);
    }

    @Override
    public CursoResponseDTO actualizar(Long id, CursoRequestDTO cursoRequestDTO) {

        Curso curso = cursoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Curso no encontrado"));

        curso.setNombre(cursoRequestDTO.getNombre());
        curso.setNivel(cursoRequestDTO.getNivel());
        curso.setSeccion(cursoRequestDTO.getSeccion());

        Curso cursoActualizado = cursoRepository.save(curso);

        return convertirADTO(cursoActualizado);
    }

    @Override
    public void eliminar(Long id) {

        Curso curso = cursoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Curso no encontrado"));

        boolean tieneAsignaturas = !asignaturaRepository.findByCursoId(id).isEmpty();
        boolean tieneAsignacionesDocentes = !asignacionDocenteRepository.findByCursoId(id).isEmpty();

        if (tieneAsignaturas || tieneAsignacionesDocentes) {
            throw new RuntimeException("No se puede eliminar el curso porque tiene asignaturas o asignaciones docentes asociadas");
        }

        cursoRepository.delete(curso);
    }

    private CursoResponseDTO convertirADTO(Curso curso) {

        CursoResponseDTO dto = new CursoResponseDTO();

        dto.setId(curso.getId());
        dto.setNombre(curso.getNombre());
        dto.setNivel(curso.getNivel());
        dto.setSeccion(curso.getSeccion());

        return dto;
    }
}