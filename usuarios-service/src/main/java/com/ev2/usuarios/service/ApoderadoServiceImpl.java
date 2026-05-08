package com.ev2.usuarios.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ev2.usuarios.dto.ApoderadoRequestDTO;
import com.ev2.usuarios.dto.ApoderadoResponseDTO;
import com.ev2.usuarios.model.Apoderado;
import com.ev2.usuarios.model.Estudiante;
import com.ev2.usuarios.repository.ApoderadoRepository;
import com.ev2.usuarios.repository.EstudianteRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ApoderadoServiceImpl implements ApoderadoService {

    private final ApoderadoRepository apoderadoRepository;
    private final EstudianteRepository estudianteRepository;

    @Override
    public ApoderadoResponseDTO guardar(ApoderadoRequestDTO apoderadoRequestDTO) {

        List<Estudiante> estudiantes = estudianteRepository
                .findAllById(apoderadoRequestDTO.getEstudiantesIds());

        Apoderado apoderado = new Apoderado();

        apoderado.setNombre(apoderadoRequestDTO.getNombre());
        apoderado.setApellido(apoderadoRequestDTO.getApellido());
        apoderado.setEmail(apoderadoRequestDTO.getEmail());
        apoderado.setEstudiantes(estudiantes);

        Apoderado apoderadoGuardado = apoderadoRepository.save(apoderado);

        return convertirADTO(apoderadoGuardado);
    }

    private ApoderadoResponseDTO convertirADTO(Apoderado apoderado) {

        ApoderadoResponseDTO dto = new ApoderadoResponseDTO();

        dto.setId(apoderado.getId());
        dto.setNombre(apoderado.getNombre());
        dto.setApellido(apoderado.getApellido());
        dto.setEmail(apoderado.getEmail());

        List<Long> estudiantesIds = apoderado.getEstudiantes()
                .stream()
                .map(Estudiante::getId)
                .toList();

        dto.setEstudiantesIds(estudiantesIds);

        return dto;
    }
}