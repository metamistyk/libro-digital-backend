package com.ev2.academico;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.time.LocalDate;
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

import com.ev2.academico.controller.AsignacionDocenteController;
import com.ev2.academico.controller.AsignaturaController;
import com.ev2.academico.controller.CursoController;
import com.ev2.academico.controller.PeriodoAcademicoController;
import com.ev2.academico.dto.AsignacionDocenteRequestDTO;
import com.ev2.academico.dto.AsignacionDocenteResponseDTO;
import com.ev2.academico.dto.AsignaturaRequestDTO;
import com.ev2.academico.dto.AsignaturaResponseDTO;
import com.ev2.academico.dto.CursoRequestDTO;
import com.ev2.academico.dto.CursoResponseDTO;
import com.ev2.academico.dto.PeriodoAcademicoRequestDTO;
import com.ev2.academico.dto.PeriodoAcademicoResponseDTO;
import com.ev2.academico.model.AsignacionDocente;
import com.ev2.academico.model.Asignatura;
import com.ev2.academico.model.Curso;
import com.ev2.academico.model.PeriodoAcademico;
import com.ev2.academico.repository.AsignacionDocenteRepository;
import com.ev2.academico.repository.AsignaturaRepository;
import com.ev2.academico.repository.CursoRepository;
import com.ev2.academico.repository.PeriodoAcademicoRepository;
import com.ev2.academico.service.AsignacionDocenteServiceImpl;
import com.ev2.academico.service.AsignaturaServiceImpl;
import com.ev2.academico.service.CursoServiceImpl;
import com.ev2.academico.service.PeriodoAcademicoServiceImpl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@ExtendWith(MockitoExtension.class)
class AcademicoServiceApplicationTests {

    // ── Mocks ─────────────────────────────────────────────────────────────────

    @Mock
    private CursoRepository cursoRepository;

    @Mock
    private AsignaturaRepository asignaturaRepository;

    @Mock
    private AsignacionDocenteRepository asignacionDocenteRepository;

    @Mock
    private PeriodoAcademicoRepository periodoAcademicoRepository;

    @InjectMocks
    private CursoServiceImpl cursoService;

    @InjectMocks
    private AsignaturaServiceImpl asignaturaService;

    @InjectMocks
    private PeriodoAcademicoServiceImpl periodoAcademicoService;

    @InjectMocks
    private AsignacionDocenteServiceImpl asignacionDocenteService;

    // ── MockMvc ───────────────────────────────────────────────────────────────

    private MockMvc mockMvcCurso;
    private MockMvc mockMvcAsignatura;
    private MockMvc mockMvcPeriodo;
    private MockMvc mockMvcAsignacion;
    private ObjectMapper objectMapper;

    // ── Datos de prueba ───────────────────────────────────────────────────────

    private Curso cursoBase;
    private CursoRequestDTO requestCurso;

    private Asignatura asignaturaBase;
    private AsignaturaRequestDTO requestAsignatura;

    private PeriodoAcademico periodoBase;
    private PeriodoAcademicoRequestDTO requestPeriodo;

    private AsignacionDocente asignacionBase;
    private AsignacionDocenteRequestDTO requestAsignacion;

    @BeforeEach
    void setUp() {

        CursoController cursoController = new CursoController(cursoService);
        AsignaturaController asignaturaController = new AsignaturaController(asignaturaService);
        PeriodoAcademicoController periodoController = new PeriodoAcademicoController(periodoAcademicoService);
        AsignacionDocenteController asignacionController = new AsignacionDocenteController(asignacionDocenteService);

        mockMvcCurso = MockMvcBuilders.standaloneSetup(cursoController).build();
        mockMvcAsignatura = MockMvcBuilders.standaloneSetup(asignaturaController).build();
        mockMvcPeriodo = MockMvcBuilders.standaloneSetup(periodoController).build();
        mockMvcAsignacion = MockMvcBuilders.standaloneSetup(asignacionController).build();

        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        cursoBase = new Curso();
        cursoBase.setId(1L);
        cursoBase.setNombre("1° Básico A");
        cursoBase.setNivel("Básico");
        cursoBase.setSeccion("A");

        requestCurso = new CursoRequestDTO();
        requestCurso.setNombre("1° Básico A");
        requestCurso.setNivel("Básico");
        requestCurso.setSeccion("A");

        asignaturaBase = new Asignatura();
        asignaturaBase.setId(1L);
        asignaturaBase.setNombre("Matemáticas");
        asignaturaBase.setCodigo("MAT001");
        asignaturaBase.setCurso(cursoBase);

        requestAsignatura = new AsignaturaRequestDTO();
        requestAsignatura.setNombre("Matemáticas");
        requestAsignatura.setCodigo("MAT001");
        requestAsignatura.setCursoId(1L);

        periodoBase = new PeriodoAcademico();
        periodoBase.setId(1L);
        periodoBase.setNombre("2025-1");
        periodoBase.setFechaInicio(LocalDate.of(2025, 3, 1));
        periodoBase.setFechaTermino(LocalDate.of(2025, 7, 31));
        periodoBase.setActivo(true);

        requestPeriodo = new PeriodoAcademicoRequestDTO();
        requestPeriodo.setNombre("2025-1");
        requestPeriodo.setFechaInicio(LocalDate.of(2025, 3, 1));
        requestPeriodo.setFechaTermino(LocalDate.of(2025, 7, 31));
        requestPeriodo.setActivo(true);

        asignacionBase = new AsignacionDocente();
        asignacionBase.setId(1L);
        asignacionBase.setDocenteId(10L);
        asignacionBase.setCurso(cursoBase);
        asignacionBase.setAsignatura(asignaturaBase);
        asignacionBase.setPeriodoAcademico(periodoBase);

        requestAsignacion = new AsignacionDocenteRequestDTO();
        requestAsignacion.setDocenteId(10L);
        requestAsignacion.setCursoId(1L);
        requestAsignacion.setAsignaturaId(1L);
        requestAsignacion.setPeriodoAcademicoId(1L);
    }

    // ── Tests CursoService ────────────────────────────────────────────────────

    @Test
    void guardarCurso_debeRetornarDTO() {
        when(cursoRepository.save(any(Curso.class))).thenReturn(cursoBase);
        CursoResponseDTO resultado = cursoService.guardar(requestCurso);
        assertNotNull(resultado);
        assertEquals("1° Básico A", resultado.getNombre());
        verify(cursoRepository, times(1)).save(any(Curso.class));
    }

    @Test
    void listarCursos_debeRetornarLista() {
        when(cursoRepository.findAll()).thenReturn(List.of(cursoBase));
        List<CursoResponseDTO> resultado = cursoService.listarTodos();
        assertNotNull(resultado);
        assertEquals(1, resultado.size());
    }

    @Test
    void listarCursos_listaVacia_debeRetornarListaVacia() {
        when(cursoRepository.findAll()).thenReturn(List.of());
        List<CursoResponseDTO> resultado = cursoService.listarTodos();
        assertTrue(resultado.isEmpty());
    }

    @Test
    void buscarCursoPorId_debeRetornarDTO() {
        when(cursoRepository.findById(1L)).thenReturn(Optional.of(cursoBase));
        CursoResponseDTO resultado = cursoService.buscarPorId(1L);
        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
    }

    @Test
    void buscarCursoPorId_noExiste_debeLanzarExcepcion() {
        when(cursoRepository.findById(99L)).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> cursoService.buscarPorId(99L));
    }

    @Test
    void eliminarCurso_sinDependencias_debeEliminar() {
        when(cursoRepository.findById(1L)).thenReturn(Optional.of(cursoBase));
        when(asignaturaRepository.findByCursoId(1L)).thenReturn(List.of());
        when(asignacionDocenteRepository.findByCursoId(1L)).thenReturn(List.of());
        cursoService.eliminar(1L);
        verify(cursoRepository, times(1)).delete(cursoBase);
    }

    @Test
    void eliminarCurso_conAsignaturas_debeLanzarExcepcion() {
        when(cursoRepository.findById(1L)).thenReturn(Optional.of(cursoBase));
        when(asignaturaRepository.findByCursoId(1L)).thenReturn(List.of(asignaturaBase));
        assertThrows(RuntimeException.class, () -> cursoService.eliminar(1L));
        verify(cursoRepository, never()).delete(any());
    }

    // ── Tests AsignaturaService ───────────────────────────────────────────────

    @Test
    void guardarAsignatura_debeRetornarDTO() {
        when(cursoRepository.findById(1L)).thenReturn(Optional.of(cursoBase));
        when(asignaturaRepository.save(any(Asignatura.class))).thenReturn(asignaturaBase);
        AsignaturaResponseDTO resultado = asignaturaService.guardar(requestAsignatura);
        assertNotNull(resultado);
        assertEquals("Matemáticas", resultado.getNombre());
        verify(asignaturaRepository, times(1)).save(any(Asignatura.class));
    }

    @Test
    void guardarAsignatura_cursoNoExiste_debeLanzarExcepcion() {
        when(cursoRepository.findById(99L)).thenReturn(Optional.empty());
        requestAsignatura.setCursoId(99L);
        assertThrows(RuntimeException.class, () -> asignaturaService.guardar(requestAsignatura));
    }

    @Test
    void listarAsignaturas_debeRetornarLista() {
        when(asignaturaRepository.findAll()).thenReturn(List.of(asignaturaBase));
        List<AsignaturaResponseDTO> resultado = asignaturaService.listarTodas();
        assertNotNull(resultado);
        assertEquals(1, resultado.size());
    }

    @Test
    void listarAsignaturasPorCurso_debeRetornarLista() {
        when(asignaturaRepository.findByCursoId(1L)).thenReturn(List.of(asignaturaBase));
        List<AsignaturaResponseDTO> resultado = asignaturaService.listarPorCurso(1L);
        assertNotNull(resultado);
        assertEquals(1, resultado.size());
    }

    @Test
    void eliminarAsignatura_conAsignaciones_debeLanzarExcepcion() {
        when(asignaturaRepository.findById(1L)).thenReturn(Optional.of(asignaturaBase));
        when(asignacionDocenteRepository.findByAsignaturaId(1L))
            .thenReturn(List.of(new AsignacionDocente()));
        assertThrows(RuntimeException.class, () -> asignaturaService.eliminar(1L));
        verify(asignaturaRepository, never()).delete(any());
    }

    // ── Tests PeriodoAcademicoService ─────────────────────────────────────────

    @Test
    void guardarPeriodo_debeRetornarDTO() {
        when(periodoAcademicoRepository.save(any(PeriodoAcademico.class)))
            .thenReturn(periodoBase);
        PeriodoAcademicoResponseDTO resultado = periodoAcademicoService.guardar(requestPeriodo);
        assertNotNull(resultado);
        assertEquals("2025-1", resultado.getNombre());
        verify(periodoAcademicoRepository, times(1)).save(any(PeriodoAcademico.class));
    }

    @Test
    void listarPeriodos_debeRetornarLista() {
        when(periodoAcademicoRepository.findAll()).thenReturn(List.of(periodoBase));
        List<PeriodoAcademicoResponseDTO> resultado = periodoAcademicoService.listarTodos();
        assertNotNull(resultado);
        assertEquals(1, resultado.size());
    }

    @Test
    void listarPeriodosActivos_debeRetornarLista() {
        when(periodoAcademicoRepository.findByActivoTrue()).thenReturn(List.of(periodoBase));
        List<PeriodoAcademicoResponseDTO> resultado = periodoAcademicoService.listarActivos();
        assertNotNull(resultado);
        assertEquals(1, resultado.size());
    }

    @Test
    void buscarPeriodoPorId_debeRetornarDTO() {
        when(periodoAcademicoRepository.findById(1L)).thenReturn(Optional.of(periodoBase));
        PeriodoAcademicoResponseDTO resultado = periodoAcademicoService.buscarPorId(1L);
        assertNotNull(resultado);
        assertEquals("2025-1", resultado.getNombre());
    }

    @Test
    void buscarPeriodoPorId_noExiste_debeLanzarExcepcion() {
        when(periodoAcademicoRepository.findById(99L)).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> periodoAcademicoService.buscarPorId(99L));
    }

    @Test
    void eliminarPeriodo_debeEliminar() {
        when(periodoAcademicoRepository.findById(1L)).thenReturn(Optional.of(periodoBase));
        periodoAcademicoService.eliminar(1L);
        verify(periodoAcademicoRepository, times(1)).delete(periodoBase);
    }

    // ── Tests AsignacionDocenteService ────────────────────────────────────────

    @Test
    void guardarAsignacion_debeRetornarDTO() {
        when(cursoRepository.findById(1L)).thenReturn(Optional.of(cursoBase));
        when(asignaturaRepository.findById(1L)).thenReturn(Optional.of(asignaturaBase));
        when(periodoAcademicoRepository.findById(1L)).thenReturn(Optional.of(periodoBase));
        when(asignacionDocenteRepository.save(any(AsignacionDocente.class)))
            .thenReturn(asignacionBase);
        AsignacionDocenteResponseDTO resultado =
            asignacionDocenteService.guardar(requestAsignacion);
        assertNotNull(resultado);
        assertEquals(10L, resultado.getDocenteId());
        verify(asignacionDocenteRepository, times(1)).save(any(AsignacionDocente.class));
    }

    @Test
    void listarAsignaciones_debeRetornarLista() {
        when(asignacionDocenteRepository.findAll()).thenReturn(List.of(asignacionBase));
        List<AsignacionDocenteResponseDTO> resultado =
            asignacionDocenteService.listarTodas();
        assertNotNull(resultado);
        assertEquals(1, resultado.size());
    }

    @Test
    void listarAsignacionesPorDocente_debeRetornarLista() {
        when(asignacionDocenteRepository.findByDocenteId(10L))
            .thenReturn(List.of(asignacionBase));
        List<AsignacionDocenteResponseDTO> resultado =
            asignacionDocenteService.listarPorDocente(10L);
        assertNotNull(resultado);
        assertEquals(1, resultado.size());
    }

    @Test
    void listarAsignacionesPorCurso_debeRetornarLista() {
        when(asignacionDocenteRepository.findByCursoId(1L))
            .thenReturn(List.of(asignacionBase));
        List<AsignacionDocenteResponseDTO> resultado =
            asignacionDocenteService.listarPorCurso(1L);
        assertNotNull(resultado);
        assertEquals(1, resultado.size());
    }

    @Test
    void buscarAsignacionPorId_debeRetornarDTO() {
        when(asignacionDocenteRepository.findById(1L))
            .thenReturn(Optional.of(asignacionBase));
        AsignacionDocenteResponseDTO resultado =
            asignacionDocenteService.buscarPorId(1L);
        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
    }

    @Test
    void buscarAsignacionPorId_noExiste_debeLanzarExcepcion() {
        when(asignacionDocenteRepository.findById(99L)).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () ->
            asignacionDocenteService.buscarPorId(99L));
    }

    @Test
    void eliminarAsignacion_debeEliminar() {
        when(asignacionDocenteRepository.findById(1L))
            .thenReturn(Optional.of(asignacionBase));
        asignacionDocenteService.eliminar(1L);
        verify(asignacionDocenteRepository, times(1)).delete(asignacionBase);
    }

    // ── Tests CursoController MockMvc ─────────────────────────────────────────

    @Test
    void controllerListarCursos_debeRetornar200() throws Exception {
        when(cursoRepository.findAll()).thenReturn(List.of(cursoBase));
        mockMvcCurso.perform(get("/api/v1/cursos")
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
    }

    @Test
    void controllerBuscarCursoPorId_debeRetornar200() throws Exception {
        when(cursoRepository.findById(1L)).thenReturn(Optional.of(cursoBase));
        mockMvcCurso.perform(get("/api/v1/cursos/1")
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
    }

    @Test
    void controllerGuardarCurso_debeRetornar200() throws Exception {
        when(cursoRepository.save(any(Curso.class))).thenReturn(cursoBase);
        mockMvcCurso.perform(post("/api/v1/cursos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestCurso)))
            .andExpect(status().isOk());
    }

    @Test
    void controllerEliminarCurso_debeRetornar200() throws Exception {
        when(cursoRepository.findById(1L)).thenReturn(Optional.of(cursoBase));
        when(asignaturaRepository.findByCursoId(1L)).thenReturn(List.of());
        when(asignacionDocenteRepository.findByCursoId(1L)).thenReturn(List.of());
        mockMvcCurso.perform(delete("/api/v1/cursos/1")
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
    }

    // ── Tests AsignaturaController MockMvc ────────────────────────────────────

    @Test
    void controllerListarAsignaturas_debeRetornar200() throws Exception {
        when(asignaturaRepository.findAll()).thenReturn(List.of(asignaturaBase));
        mockMvcAsignatura.perform(get("/api/v1/asignaturas")
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
    }

    @Test
    void controllerBuscarAsignaturaPorId_debeRetornar200() throws Exception {
        when(asignaturaRepository.findById(1L)).thenReturn(Optional.of(asignaturaBase));
        mockMvcAsignatura.perform(get("/api/v1/asignaturas/1")
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
    }

    @Test
    void controllerGuardarAsignatura_debeRetornar200() throws Exception {
        when(cursoRepository.findById(1L)).thenReturn(Optional.of(cursoBase));
        when(asignaturaRepository.save(any(Asignatura.class))).thenReturn(asignaturaBase);
        mockMvcAsignatura.perform(post("/api/v1/asignaturas")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestAsignatura)))
            .andExpect(status().isOk());
    }

    @Test
    void controllerListarAsignaturasPorCurso_debeRetornar200() throws Exception {
        when(asignaturaRepository.findByCursoId(1L)).thenReturn(List.of(asignaturaBase));
        mockMvcAsignatura.perform(get("/api/v1/asignaturas/por-curso")
                .param("cursoId", "1")
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
    }

    @Test
    void controllerEliminarAsignatura_debeRetornar200() throws Exception {
        when(asignaturaRepository.findById(1L)).thenReturn(Optional.of(asignaturaBase));
        when(asignacionDocenteRepository.findByAsignaturaId(1L)).thenReturn(List.of());
        mockMvcAsignatura.perform(delete("/api/v1/asignaturas/1")
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
    }

    // ── Tests PeriodoAcademicoController MockMvc ──────────────────────────────

    @Test
    void controllerListarPeriodos_debeRetornar200() throws Exception {
        when(periodoAcademicoRepository.findAll()).thenReturn(List.of(periodoBase));
        mockMvcPeriodo.perform(get("/api/v1/periodos-academicos")
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
    }

    @Test
    void controllerListarPeriodosActivos_debeRetornar200() throws Exception {
        when(periodoAcademicoRepository.findByActivoTrue()).thenReturn(List.of(periodoBase));
        mockMvcPeriodo.perform(get("/api/v1/periodos-academicos/activos")
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
    }

    @Test
    void controllerBuscarPeriodoPorId_debeRetornar200() throws Exception {
        when(periodoAcademicoRepository.findById(1L)).thenReturn(Optional.of(periodoBase));
        mockMvcPeriodo.perform(get("/api/v1/periodos-academicos/1")
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
    }

    @Test
    void controllerGuardarPeriodo_debeRetornar200() throws Exception {
        when(periodoAcademicoRepository.save(any(PeriodoAcademico.class)))
            .thenReturn(periodoBase);
        mockMvcPeriodo.perform(post("/api/v1/periodos-academicos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestPeriodo)))
            .andExpect(status().isOk());
    }

    @Test
    void controllerEliminarPeriodo_debeRetornar200() throws Exception {
        when(periodoAcademicoRepository.findById(1L)).thenReturn(Optional.of(periodoBase));
        mockMvcPeriodo.perform(delete("/api/v1/periodos-academicos/1")
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
    }

    // ── Tests AsignacionDocenteController MockMvc ─────────────────────────────

    @Test
    void controllerListarAsignaciones_debeRetornar200() throws Exception {
        when(asignacionDocenteRepository.findAll()).thenReturn(List.of(asignacionBase));
        mockMvcAsignacion.perform(get("/api/v1/asignaciones-docentes")
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
    }

    @Test
    void controllerBuscarAsignacionPorId_debeRetornar200() throws Exception {
        when(asignacionDocenteRepository.findById(1L))
            .thenReturn(Optional.of(asignacionBase));
        mockMvcAsignacion.perform(get("/api/v1/asignaciones-docentes/1")
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
    }

    @Test
    void controllerListarAsignacionesPorDocente_debeRetornar200() throws Exception {
        when(asignacionDocenteRepository.findByDocenteId(10L))
            .thenReturn(List.of(asignacionBase));
        mockMvcAsignacion.perform(get("/api/v1/asignaciones-docentes/por-docente")
                .param("docenteId", "10")
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
    }

    @Test
    void controllerListarAsignacionesPorCurso_debeRetornar200() throws Exception {
        when(asignacionDocenteRepository.findByCursoId(1L))
            .thenReturn(List.of(asignacionBase));
        mockMvcAsignacion.perform(get("/api/v1/asignaciones-docentes/por-curso")
                .param("cursoId", "1")
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
    }

    @Test
    void controllerGuardarAsignacion_debeRetornar200() throws Exception {
        when(cursoRepository.findById(1L)).thenReturn(Optional.of(cursoBase));
        when(asignaturaRepository.findById(1L)).thenReturn(Optional.of(asignaturaBase));
        when(periodoAcademicoRepository.findById(1L)).thenReturn(Optional.of(periodoBase));
        when(asignacionDocenteRepository.save(any(AsignacionDocente.class)))
            .thenReturn(asignacionBase);
        mockMvcAsignacion.perform(post("/api/v1/asignaciones-docentes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestAsignacion)))
            .andExpect(status().isOk());
    }

    @Test
    void controllerEliminarAsignacion_debeRetornar200() throws Exception {
        when(asignacionDocenteRepository.findById(1L))
            .thenReturn(Optional.of(asignacionBase));
        mockMvcAsignacion.perform(delete("/api/v1/asignaciones-docentes/1")
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
    }
// ── Tests actualizar ──────────────────────────────────────────────────────

    @Test
    void actualizarCurso_debeRetornarDTO() {
        when(cursoRepository.findById(1L)).thenReturn(Optional.of(cursoBase));
        when(cursoRepository.save(any(Curso.class))).thenReturn(cursoBase);
        CursoResponseDTO resultado = cursoService.actualizar(1L, requestCurso);
        assertNotNull(resultado);
        assertEquals("1° Básico A", resultado.getNombre());
        verify(cursoRepository, times(1)).save(any(Curso.class));
    }

    @Test
    void actualizarCurso_noExiste_debeLanzarExcepcion() {
        when(cursoRepository.findById(99L)).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () ->
            cursoService.actualizar(99L, requestCurso));
    }

    @Test
    void actualizarAsignatura_debeRetornarDTO() {
        when(asignaturaRepository.findById(1L)).thenReturn(Optional.of(asignaturaBase));
        when(cursoRepository.findById(1L)).thenReturn(Optional.of(cursoBase));
        when(asignaturaRepository.save(any(Asignatura.class))).thenReturn(asignaturaBase);
        AsignaturaResponseDTO resultado = asignaturaService.actualizar(1L, requestAsignatura);
        assertNotNull(resultado);
        assertEquals("Matemáticas", resultado.getNombre());
        verify(asignaturaRepository, times(1)).save(any(Asignatura.class));
    }

    @Test
    void actualizarPeriodo_debeRetornarDTO() {
        when(periodoAcademicoRepository.findById(1L)).thenReturn(Optional.of(periodoBase));
        when(periodoAcademicoRepository.save(any(PeriodoAcademico.class)))
            .thenReturn(periodoBase);
        PeriodoAcademicoResponseDTO resultado =
            periodoAcademicoService.actualizar(1L, requestPeriodo);
        assertNotNull(resultado);
        assertEquals("2025-1", resultado.getNombre());
        verify(periodoAcademicoRepository, times(1)).save(any(PeriodoAcademico.class));
    }

    @Test
    void actualizarAsignacion_debeRetornarDTO() {
        when(asignacionDocenteRepository.findById(1L))
            .thenReturn(Optional.of(asignacionBase));
        when(cursoRepository.findById(1L)).thenReturn(Optional.of(cursoBase));
        when(asignaturaRepository.findById(1L)).thenReturn(Optional.of(asignaturaBase));
        when(periodoAcademicoRepository.findById(1L)).thenReturn(Optional.of(periodoBase));
        when(asignacionDocenteRepository.save(any(AsignacionDocente.class)))
            .thenReturn(asignacionBase);
        AsignacionDocenteResponseDTO resultado =
            asignacionDocenteService.actualizar(1L, requestAsignacion);
        assertNotNull(resultado);
        assertEquals(10L, resultado.getDocenteId());
        verify(asignacionDocenteRepository, times(1)).save(any(AsignacionDocente.class));
    }

    @Test
    void controllerActualizarCurso_debeRetornar200() throws Exception {
        when(cursoRepository.findById(1L)).thenReturn(Optional.of(cursoBase));
        when(cursoRepository.save(any(Curso.class))).thenReturn(cursoBase);
        mockMvcCurso.perform(put("/api/v1/cursos/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestCurso)))
            .andExpect(status().isOk());
    }

    @Test
    void controllerActualizarPeriodo_debeRetornar200() throws Exception {
        when(periodoAcademicoRepository.findById(1L)).thenReturn(Optional.of(periodoBase));
        when(periodoAcademicoRepository.save(any(PeriodoAcademico.class)))
            .thenReturn(periodoBase);
        mockMvcPeriodo.perform(put("/api/v1/periodos-academicos/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestPeriodo)))
            .andExpect(status().isOk());
    }

    @Test
    void controllerActualizarAsignacion_debeRetornar200() throws Exception {
        when(asignacionDocenteRepository.findById(1L))
            .thenReturn(Optional.of(asignacionBase));
        when(cursoRepository.findById(1L)).thenReturn(Optional.of(cursoBase));
        when(asignaturaRepository.findById(1L)).thenReturn(Optional.of(asignaturaBase));
        when(periodoAcademicoRepository.findById(1L)).thenReturn(Optional.of(periodoBase));
        when(asignacionDocenteRepository.save(any(AsignacionDocente.class)))
            .thenReturn(asignacionBase);
        mockMvcAsignacion.perform(put("/api/v1/asignaciones-docentes/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestAsignacion)))
            .andExpect(status().isOk());
    }
}