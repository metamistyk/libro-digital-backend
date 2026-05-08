package com.ev2.academico.service;

import org.springframework.stereotype.Service;

import com.ev2.academico.dto.CursoRequestDTO;
import com.ev2.academico.dto.CursoResponseDTO;
import com.ev2.academico.model.Curso;
import com.ev2.academico.repository.CursoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CursoServiceImpl implements CursoService {

    private final CursoRepository cursoRepository;

    @Override
    public CursoResponseDTO guardar(CursoRequestDTO cursoRequestDTO) {

        Curso curso = new Curso();

        curso.setNombre(cursoRequestDTO.getNombre());
        curso.setNivel(cursoRequestDTO.getNivel());
        curso.setSeccion(cursoRequestDTO.getSeccion());

        Curso cursoGuardado = cursoRepository.save(curso);

        return convertirADTO(cursoGuardado);
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