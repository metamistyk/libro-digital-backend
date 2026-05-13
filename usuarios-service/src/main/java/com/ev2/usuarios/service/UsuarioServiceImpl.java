package com.ev2.usuarios.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ev2.usuarios.dto.UsuarioRequestDTO;
import com.ev2.usuarios.dto.UsuarioResponseDTO;
import com.ev2.usuarios.model.Rol;
import com.ev2.usuarios.model.Usuario;
import com.ev2.usuarios.repository.RolRepository;
import com.ev2.usuarios.repository.UsuarioRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final RolRepository rolRepository;

    @Override
    public UsuarioResponseDTO guardar(UsuarioRequestDTO usuarioRequestDTO) {

        Rol rol = rolRepository.findById(usuarioRequestDTO.getRolId())
                .orElseThrow(() -> new RuntimeException("Rol no encontrado"));

        Usuario usuario = new Usuario();

        usuario.setNombre(usuarioRequestDTO.getNombre());
        usuario.setApellido(usuarioRequestDTO.getApellido());
        usuario.setEmail(usuarioRequestDTO.getEmail());
        usuario.setRol(rol);

        Usuario usuarioGuardado = usuarioRepository.save(usuario);

        return convertirADTO(usuarioGuardado);
    }

    @Override
    public List<UsuarioResponseDTO> listarTodos() {

        return usuarioRepository.findAll()
                .stream()
                .map(this::convertirADTO)
                .toList();
    }

    private UsuarioResponseDTO convertirADTO(Usuario usuario) {

        UsuarioResponseDTO dto = new UsuarioResponseDTO();

        dto.setId(usuario.getId());
        dto.setNombre(usuario.getNombre());
        dto.setApellido(usuario.getApellido());
        dto.setEmail(usuario.getEmail());

        if (usuario.getRol() != null) {
            dto.setRolId(usuario.getRol().getId());
            dto.setNombreRol(usuario.getRol().getNombre());
        }

        return dto;
    }
}