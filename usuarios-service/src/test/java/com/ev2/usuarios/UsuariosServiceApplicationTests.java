package com.ev2.usuarios;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.ev2.usuarios.controller.ApoderadoController;
import com.ev2.usuarios.controller.EstudianteController;
import com.ev2.usuarios.controller.RolController;
import com.ev2.usuarios.controller.UsuarioController;
import com.ev2.usuarios.dto.ApoderadoRequestDTO;
import com.ev2.usuarios.dto.ApoderadoResponseDTO;
import com.ev2.usuarios.dto.EstudianteRequestDTO;
import com.ev2.usuarios.dto.EstudianteResponseDTO;
import com.ev2.usuarios.dto.RolRequestDTO;
import com.ev2.usuarios.dto.RolResponseDTO;
import com.ev2.usuarios.dto.UsuarioRequestDTO;
import com.ev2.usuarios.dto.UsuarioResponseDTO;
import com.ev2.usuarios.model.Apoderado;
import com.ev2.usuarios.model.Estudiante;
import com.ev2.usuarios.model.Rol;
import com.ev2.usuarios.model.Usuario;
import com.ev2.usuarios.repository.ApoderadoRepository;
import com.ev2.usuarios.repository.EstudianteRepository;
import com.ev2.usuarios.repository.RolRepository;
import com.ev2.usuarios.repository.UsuarioRepository;
import com.ev2.usuarios.service.ApoderadoServiceImpl;
import com.ev2.usuarios.service.EstudianteServiceImpl;
import com.ev2.usuarios.service.RolServiceImpl;
import com.ev2.usuarios.service.UsuarioServiceImpl;

import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(MockitoExtension.class)
class UsuariosServiceApplicationTests {

    // ── Mocks ─────────────────────────────────────────────────────────────────

    @Mock
    private EstudianteRepository estudianteRepository;

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private RolRepository rolRepository;

    @Mock
    private ApoderadoRepository apoderadoRepository;

    @InjectMocks
    private EstudianteServiceImpl estudianteService;

    @InjectMocks
    private UsuarioServiceImpl usuarioService;

    @InjectMocks
    private RolServiceImpl rolService;

    @InjectMocks
    private ApoderadoServiceImpl apoderadoService;

    // ── MockMvc ───────────────────────────────────────────────────────────────

    private MockMvc mockMvcEstudiante;
    private MockMvc mockMvcUsuario;
    private MockMvc mockMvcRol;
    private MockMvc mockMvcApoderado;
    private ObjectMapper objectMapper;

    // ── Datos de prueba ───────────────────────────────────────────────────────

    private Rol rolBase;
    private RolRequestDTO requestRol;

    private Estudiante estudianteBase;
    private EstudianteRequestDTO requestEstudiante;

    private Usuario usuarioBase;
    private UsuarioRequestDTO requestUsuario;

    private Apoderado apoderadoBase;
    private ApoderadoRequestDTO requestApoderado;

    @BeforeEach
    void setUp() {

        EstudianteController estudianteController = new EstudianteController(estudianteService);
        UsuarioController usuarioController = new UsuarioController(usuarioService);
        RolController rolController = new RolController(rolService);
        ApoderadoController apoderadoController = new ApoderadoController(apoderadoService);

        mockMvcEstudiante = MockMvcBuilders.standaloneSetup(estudianteController).build();
        mockMvcUsuario = MockMvcBuilders.standaloneSetup(usuarioController).build();
        mockMvcRol = MockMvcBuilders.standaloneSetup(rolController).build();
        mockMvcApoderado = MockMvcBuilders.standaloneSetup(apoderadoController).build();

        objectMapper = new ObjectMapper();

        rolBase = new Rol();
        rolBase.setId(1L);
        rolBase.setNombre("docente");

        requestRol = new RolRequestDTO();
        requestRol.setNombre("docente");

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

        apoderadoBase = new Apoderado();
        apoderadoBase.setId(1L);
        apoderadoBase.setNombre("Carlos");
        apoderadoBase.setApellido("Soto");
        apoderadoBase.setEmail("carlos@test.com");
        apoderadoBase.setEstudiantes(List.of(estudianteBase));

        requestApoderado = new ApoderadoRequestDTO();
        requestApoderado.setNombre("Carlos");
        requestApoderado.setApellido("Soto");
        requestApoderado.setEmail("carlos@test.com");
        requestApoderado.setEstudiantesIds(List.of(1L));
    }

    // ── Tests EstudianteService ───────────────────────────────────────────────

    @Test
    void guardarEstudiante_debeRetornarDTO() {
        when(estudianteRepository.save(any(Estudiante.class))).thenReturn(estudianteBase);
        EstudianteResponseDTO resultado = estudianteService.guardar(requestEstudiante);
        assertNotNull(resultado);
        assertEquals("Juan", resultado.getNombre());
        verify(estudianteRepository, times(1)).save(any(Estudiante.class));
    }

    @Test
    void listarEstudiantes_debeRetornarLista() {
        when(estudianteRepository.findAll()).thenReturn(List.of(estudianteBase));
        List<EstudianteResponseDTO> resultado = estudianteService.listarTodos();
        assertNotNull(resultado);
        assertEquals(1, resultado.size());
    }

    @Test
    void listarEstudiantes_listaVacia_debeRetornarListaVacia() {
        when(estudianteRepository.findAll()).thenReturn(List.of());
        List<EstudianteResponseDTO> resultado = estudianteService.listarTodos();
        assertTrue(resultado.isEmpty());
    }

    @Test
    void buscarEstudiantePorId_debeRetornarDTO() {
        when(estudianteRepository.findById(1L)).thenReturn(Optional.of(estudianteBase));
        EstudianteResponseDTO resultado = estudianteService.buscarPorId(1L);
        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
    }

    @Test
    void buscarEstudiantePorId_noExiste_debeLanzarExcepcion() {
        when(estudianteRepository.findById(99L)).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> estudianteService.buscarPorId(99L));
    }

    // ── Tests UsuarioService ──────────────────────────────────────────────────

    @Test
    void guardarUsuario_debeRetornarDTO() {
        when(rolRepository.findById(1L)).thenReturn(Optional.of(rolBase));
        when(usuarioRepository.save(any(Usuario.class))).thenReturn(usuarioBase);
        UsuarioResponseDTO resultado = usuarioService.guardar(requestUsuario);
        assertNotNull(resultado);
        assertEquals("María", resultado.getNombre());
        verify(usuarioRepository, times(1)).save(any(Usuario.class));
    }

    @Test
    void guardarUsuario_rolNoExiste_debeLanzarExcepcion() {
        when(rolRepository.findById(99L)).thenReturn(Optional.empty());
        requestUsuario.setRolId(99L);
        assertThrows(RuntimeException.class, () -> usuarioService.guardar(requestUsuario));
    }

    @Test
    void listarUsuarios_debeRetornarLista() {
        when(usuarioRepository.findAll()).thenReturn(List.of(usuarioBase));
        List<UsuarioResponseDTO> resultado = usuarioService.listarTodos();
        assertNotNull(resultado);
        assertEquals(1, resultado.size());
    }

    @Test
    void listarUsuarios_listaVacia_debeRetornarListaVacia() {
        when(usuarioRepository.findAll()).thenReturn(List.of());
        List<UsuarioResponseDTO> resultado = usuarioService.listarTodos();
        assertTrue(resultado.isEmpty());
    }

    // ── Tests RolService ──────────────────────────────────────────────────────

    @Test
    void guardarRol_debeRetornarDTO() {
        when(rolRepository.save(any(Rol.class))).thenReturn(rolBase);
        RolResponseDTO resultado = rolService.guardar(requestRol);
        assertNotNull(resultado);
        assertEquals("docente", resultado.getNombre());
        verify(rolRepository, times(1)).save(any(Rol.class));
    }

    @Test
    void listarRoles_debeRetornarLista() {
        when(rolRepository.findAll()).thenReturn(List.of(rolBase));
        List<RolResponseDTO> resultado = rolService.listarTodos();
        assertNotNull(resultado);
        assertEquals(1, resultado.size());
    }

    @Test
    void listarRoles_listaVacia_debeRetornarListaVacia() {
        when(rolRepository.findAll()).thenReturn(List.of());
        List<RolResponseDTO> resultado = rolService.listarTodos();
        assertTrue(resultado.isEmpty());
    }

    // ── Tests ApoderadoService ────────────────────────────────────────────────

    @Test
    void guardarApoderado_debeRetornarDTO() {
        when(estudianteRepository.findAllById(List.of(1L)))
            .thenReturn(List.of(estudianteBase));
        when(apoderadoRepository.save(any(Apoderado.class))).thenReturn(apoderadoBase);
        ApoderadoResponseDTO resultado = apoderadoService.guardar(requestApoderado);
        assertNotNull(resultado);
        assertEquals("Carlos", resultado.getNombre());
        verify(apoderadoRepository, times(1)).save(any(Apoderado.class));
    }

    @Test
    void guardarApoderado_sinEstudiantes_debeRetornarDTO() {
        when(estudianteRepository.findAllById(List.of(1L))).thenReturn(List.of());
        apoderadoBase.setEstudiantes(List.of());
        when(apoderadoRepository.save(any(Apoderado.class))).thenReturn(apoderadoBase);
        ApoderadoResponseDTO resultado = apoderadoService.guardar(requestApoderado);
        assertNotNull(resultado);
        assertTrue(resultado.getEstudiantesIds().isEmpty());
    }

    // ── Tests EstudianteController MockMvc ────────────────────────────────────

    @Test
    void controllerListarEstudiantes_debeRetornar200() throws Exception {
        when(estudianteRepository.findAll()).thenReturn(List.of(estudianteBase));
        mockMvcEstudiante.perform(get("/api/v1/estudiantes")
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
    }

    @Test
    void controllerBuscarEstudiantePorId_debeRetornar200() throws Exception {
        when(estudianteRepository.findById(1L)).thenReturn(Optional.of(estudianteBase));
        mockMvcEstudiante.perform(get("/api/v1/estudiantes/1")
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
    }

    @Test
    void controllerGuardarEstudiante_debeRetornar200() throws Exception {
        when(estudianteRepository.save(any(Estudiante.class))).thenReturn(estudianteBase);
        mockMvcEstudiante.perform(post("/api/v1/estudiantes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestEstudiante)))
            .andExpect(status().isOk());
    }

    // ── Tests UsuarioController MockMvc ───────────────────────────────────────

    @Test
    void controllerListarUsuarios_debeRetornar200() throws Exception {
        when(usuarioRepository.findAll()).thenReturn(List.of(usuarioBase));
        mockMvcUsuario.perform(get("/api/v1/usuarios")
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
    }

    @Test
    void controllerGuardarUsuario_debeRetornar200() throws Exception {
        when(rolRepository.findById(1L)).thenReturn(Optional.of(rolBase));
        when(usuarioRepository.save(any(Usuario.class))).thenReturn(usuarioBase);
        mockMvcUsuario.perform(post("/api/v1/usuarios")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestUsuario)))
            .andExpect(status().isOk());
    }

    // ── Tests RolController MockMvc ───────────────────────────────────────────

    @Test
    void controllerListarRoles_debeRetornar200() throws Exception {
        when(rolRepository.findAll()).thenReturn(List.of(rolBase));
        mockMvcRol.perform(get("/api/v1/roles")
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
    }

    @Test
    void controllerGuardarRol_debeRetornar200() throws Exception {
        when(rolRepository.save(any(Rol.class))).thenReturn(rolBase);
        mockMvcRol.perform(post("/api/v1/roles")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestRol)))
            .andExpect(status().isOk());
    }

    // ── Tests ApoderadoController MockMvc ─────────────────────────────────────

    @Test
    void controllerGuardarApoderado_debeRetornar200() throws Exception {
        when(estudianteRepository.findAllById(List.of(1L)))
            .thenReturn(List.of(estudianteBase));
        when(apoderadoRepository.save(any(Apoderado.class))).thenReturn(apoderadoBase);
        mockMvcApoderado.perform(post("/api/v1/apoderados")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestApoderado)))
            .andExpect(status().isOk());
    }
}