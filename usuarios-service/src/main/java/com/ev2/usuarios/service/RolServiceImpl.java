package com.ev2.usuarios.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ev2.usuarios.dto.RolRequestDTO;
import com.ev2.usuarios.dto.RolResponseDTO;
import com.ev2.usuarios.model.Rol;
import com.ev2.usuarios.repository.RolRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RolServiceImpl implements RolService {

    private final RolRepository rolRepository;

    @Override
    public RolResponseDTO guardar(RolRequestDTO rolRequestDTO) {

        Rol rol = new Rol();

        rol.setNombre(rolRequestDTO.getNombre());

        Rol rolGuardado = rolRepository.save(rol);

        return convertirADTO(rolGuardado);
    }

    @Override
    public List<RolResponseDTO> listarTodos() {

        return rolRepository.findAll()
                .stream()
                .map(this::convertirADTO)
                .toList();
    }

    private RolResponseDTO convertirADTO(Rol rol) {

        RolResponseDTO dto = new RolResponseDTO();

        dto.setId(rol.getId());
        dto.setNombre(rol.getNombre());

        return dto;
    }
}