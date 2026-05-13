package com.ev2.usuarios.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ev2.usuarios.model.Rol;
import com.ev2.usuarios.repository.RolRepository;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner inicializarDatos(RolRepository rolRepository) {
        return args -> {
            crearRolSiNoExiste(rolRepository, "admin");
            crearRolSiNoExiste(rolRepository, "docente");
            crearRolSiNoExiste(rolRepository, "estudiante");
        };
    }

    private void crearRolSiNoExiste(RolRepository rolRepository, String nombreRol) {
        boolean existeRol = rolRepository.findAll()
                .stream()
                .anyMatch(rol -> rol.getNombre().equalsIgnoreCase(nombreRol));

        if (!existeRol) {
            Rol rol = new Rol();
            rol.setNombre(nombreRol);
            rolRepository.save(rol);
        }
    }
}