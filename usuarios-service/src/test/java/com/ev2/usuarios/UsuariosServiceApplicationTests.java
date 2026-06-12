package com.ev2.usuarios;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.ev2.usuarios.dto.EstudianteRequestDTO;
import com.ev2.usuarios.dto.EstudianteResponseDTO;
import com.ev2.usuarios.dto.UsuarioRequestDTO;
import com.ev2.usuarios.dto.UsuarioResponseDTO;
import com.ev2.usuarios.model.Estudiante;
import com.ev2.usuarios.model.Rol;
import com.ev2.usuarios.model.Usuario;
import com.ev2.usuarios.repository.EstudianteRepository;
import com.ev2.usuarios.repository.RolRepository;
import com.ev2.usuarios.repository.UsuarioRepository;
import com.ev2.usuarios.service.EstudianteServiceImpl;
import com.ev2.usuarios.service.UsuarioServiceImpl;

@ExtendWith(MockitoExtension.class)
class UsuariosServiceApplicationTests {

    // ── Mocks para EstudianteService ──────────────────────────────────────────

    @Mock
    private EstudianteRepository estudianteRepository;

    @InjectMocks
    private EstudianteServiceImpl estudianteService;

    // ── Mocks para UsuarioService ─────────────────────────────────────────────

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private RolRepository rolRepository;

    @InjectMocks
    private UsuarioServiceImpl usuarioService;

    // ── Datos de prueba ───────────────────────────────────────────────────────

    private Estudiante estudianteBase;
    private EstudianteRequestDTO requestEstudiante;

    private Rol rolBase;
    private Usuario usuarioBase;
    private UsuarioRequestDTO requestUsuario;

    @BeforeEach
    void setUp() {

        // Estudiante
        estudianteBase = new Estudiante();
        estudianteBase.setId(1L);
        estudianteBase.setNombre("Juan");
        estudianteBase.setApellido("Pérez");
        estudianteBase.setEmail("juan@test.com");
        estudianteBase.setCursoId(10L);

        requestEstudiante = new EstudianteRequestDTO();
        requestEstudiante.setNombre("Juan");
        requestEstudiante.setApellido("Pérez");
        requestEstudiante.setEmail("juan@test.com");
        requestEstudiante.setCursoId(10L);

        // Rol y Usuario
        rolBase = new Rol();
        rolBase.setId(1L);
        rolBase.setNombre("docente");

        usuarioBase = new Usuario();
        usuarioBase.setId(1L);
        usuarioBase.setNombre("María");
        usuarioBase.setApellido("González");
        usuarioBase.setEmail("maria@test.com");
        usuarioBase.setRol(rolBase);

        requestUsuario = new UsuarioRequestDTO();
        requestUsuario.setNombre("María");
        requestUsuario.setApellido("González");
        requestUsuario.setEmail("maria@test.com");
        requestUsuario.setRolId(1L);
    }

    // ── Tests EstudianteService ───────────────────────────────────────────────

    @Test
    void guardarEstudiante_debeRetornarDTO() {

        when(estudianteRepository.save(any(Estudiante.class)))
            .thenReturn(estudianteBase);

        EstudianteResponseDTO resultado = estudianteService.guardar(requestEstudiante);

        assertNotNull(resultado);
        assertEquals("Juan", resultado.getNombre());
        assertEquals("Pérez", resultado.getApellido());
        assertEquals("juan@test.com", resultado.getEmail());
        assertEquals(10L, resultado.getCursoId());
        verify(estudianteRepository, times(1)).save(any(Estudiante.class));
    }

    @Test
    void listarEstudiantes_debeRetornarLista() {

        when(estudianteRepository.findAll())
            .thenReturn(List.of(estudianteBase));

        List<EstudianteResponseDTO> resultado = estudianteService.listarTodos();

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals("Juan", resultado.get(0).getNombre());
        verify(estudianteRepository, times(1)).findAll();
    }

    @Test
    void listarEstudiantes_listaVacia_debeRetornarListaVacia() {

        when(estudianteRepository.findAll())
            .thenReturn(List.of());

        List<EstudianteResponseDTO> resultado = estudianteService.listarTodos();

        assertNotNull(resultado);
        assertTrue(resultado.isEmpty());
    }

    @Test
    void buscarEstudiantePorId_debeRetornarDTO() {

        when(estudianteRepository.findById(1L))
            .thenReturn(Optional.of(estudianteBase));

        EstudianteResponseDTO resultado = estudianteService.buscarPorId(1L);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
        assertEquals("Juan", resultado.getNombre());
    }

    @Test
    void buscarEstudiantePorId_noExiste_debeLanzarExcepcion() {

        when(estudianteRepository.findById(99L))
            .thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> {
            estudianteService.buscarPorId(99L);
        });
    }

    // ── Tests UsuarioService ──────────────────────────────────────────────────

    @Test
    void guardarUsuario_debeRetornarDTO() {

        when(rolRepository.findById(1L))
            .thenReturn(Optional.of(rolBase));

        when(usuarioRepository.save(any(Usuario.class)))
            .thenReturn(usuarioBase);

        UsuarioResponseDTO resultado = usuarioService.guardar(requestUsuario);

        assertNotNull(resultado);
        assertEquals("María", resultado.getNombre());
        assertEquals("González", resultado.getApellido());
        assertEquals("maria@test.com", resultado.getEmail());
        assertEquals("docente", resultado.getNombreRol());
        verify(usuarioRepository, times(1)).save(any(Usuario.class));
    }

    @Test
    void guardarUsuario_rolNoExiste_debeLanzarExcepcion() {

        when(rolRepository.findById(99L))
            .thenReturn(Optional.empty());

        requestUsuario.setRolId(99L);

        assertThrows(RuntimeException.class, () -> {
            usuarioService.guardar(requestUsuario);
        });
    }

    @Test
    void listarUsuarios_debeRetornarLista() {

        when(usuarioRepository.findAll())
            .thenReturn(List.of(usuarioBase));

        List<UsuarioResponseDTO> resultado = usuarioService.listarTodos();

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals("María", resultado.get(0).getNombre());
        assertEquals("docente", resultado.get(0).getNombreRol());
        verify(usuarioRepository, times(1)).findAll();
    }

    @Test
    void listarUsuarios_listaVacia_debeRetornarListaVacia() {

        when(usuarioRepository.findAll())
            .thenReturn(List.of());

        List<UsuarioResponseDTO> resultado = usuarioService.listarTodos();

        assertNotNull(resultado);
        assertTrue(resultado.isEmpty());
    }
}