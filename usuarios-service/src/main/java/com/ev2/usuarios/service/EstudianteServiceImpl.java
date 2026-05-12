package com.ev2.usuarios.service;

import org.springframework.stereotype.Service;

import com.ev2.usuarios.client.CursoClient;
import com.ev2.usuarios.dto.EstudianteRequestDTO;
import com.ev2.usuarios.dto.EstudianteResponseDTO;
import com.ev2.usuarios.model.Estudiante;
import com.ev2.usuarios.repository.EstudianteRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EstudianteServiceImpl implements EstudianteService {

    private final EstudianteRepository estudianteRepository;
    private final CursoClient cursoClient;

    @Override
    public EstudianteResponseDTO guardar(EstudianteRequestDTO estudianteRequestDTO) {

        Boolean cursoValido = cursoClient.validarCurso(estudianteRequestDTO.getCursoId());

        if (!cursoValido) {
            throw new RuntimeException("No se puede registrar el estudiante porque el curso no existe o academico-service no está disponible");
        }

        Estudiante estudiante = new Estudiante();

        estudiante.setNombre(estudianteRequestDTO.getNombre());
        estudiante.setApellido(estudianteRequestDTO.getApellido());
        estudiante.setEmail(estudianteRequestDTO.getEmail());
        estudiante.setCursoId(estudianteRequestDTO.getCursoId());

        Estudiante estudianteGuardado = estudianteRepository.save(estudiante);

        return convertirADTO(estudianteGuardado);
    }

    private EstudianteResponseDTO convertirADTO(Estudiante estudiante) {

        EstudianteResponseDTO dto = new EstudianteResponseDTO();

        dto.setId(estudiante.getId());
        dto.setNombre(estudiante.getNombre());
        dto.setApellido(estudiante.getApellido());
        dto.setEmail(estudiante.getEmail());
        dto.setCursoId(estudiante.getCursoId());

        return dto;
    }
}