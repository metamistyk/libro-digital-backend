package com.ev2.usuarios.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ev2.usuarios.dto.EstudianteRequestDTO;
import com.ev2.usuarios.dto.EstudianteResponseDTO;
import com.ev2.usuarios.model.Estudiante;
import com.ev2.usuarios.repository.EstudianteRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EstudianteServiceImpl implements EstudianteService {

    private final EstudianteRepository estudianteRepository;

    @Override
    public EstudianteResponseDTO guardar(EstudianteRequestDTO estudianteRequestDTO) {

        Estudiante estudiante = new Estudiante();

        estudiante.setNombre(estudianteRequestDTO.getNombre());
        estudiante.setApellido(estudianteRequestDTO.getApellido());
        estudiante.setEmail(estudianteRequestDTO.getEmail());
        estudiante.setCursoId(estudianteRequestDTO.getCursoId());

        Estudiante estudianteGuardado = estudianteRepository.save(estudiante);

        return convertirADTO(estudianteGuardado);
    }

    @Override
    public List<EstudianteResponseDTO> listarTodos() {

        return estudianteRepository.findAll()
                .stream()
                .map(this::convertirADTO)
                .toList();
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
    
    @Override
    public EstudianteResponseDTO buscarPorId(Long id) {
        Estudiante estudiante = estudianteRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Estudiante no encontrado: " + id));
        return convertirADTO(estudiante);
    }
}