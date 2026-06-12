package com.ev2.academico;

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

import com.ev2.academico.dto.AsignaturaRequestDTO;
import com.ev2.academico.dto.AsignaturaResponseDTO;
import com.ev2.academico.dto.CursoRequestDTO;
import com.ev2.academico.dto.CursoResponseDTO;
import com.ev2.academico.model.Asignatura;
import com.ev2.academico.model.Curso;
import com.ev2.academico.repository.AsignacionDocenteRepository;
import com.ev2.academico.repository.AsignaturaRepository;
import com.ev2.academico.repository.CursoRepository;
import com.ev2.academico.service.AsignaturaServiceImpl;
import com.ev2.academico.service.CursoServiceImpl;

@ExtendWith(MockitoExtension.class)
class AcademicoServiceApplicationTests {

    // ── Mocks CursoService ────────────────────────────────────────────────────

    @Mock
    private CursoRepository cursoRepository;

    @Mock
    private AsignaturaRepository asignaturaRepository;

    @Mock
    private AsignacionDocenteRepository asignacionDocenteRepository;

    @InjectMocks
    private CursoServiceImpl cursoService;

    // ── Mocks AsignaturaService ───────────────────────────────────────────────

    @InjectMocks
    private AsignaturaServiceImpl asignaturaService;

    // ── Datos de prueba ───────────────────────────────────────────────────────

    private Curso cursoBase;
    private CursoRequestDTO requestCurso;

    private Asignatura asignaturaBase;
    private AsignaturaRequestDTO requestAsignatura;

    @BeforeEach
    void setUp() {

        cursoBase = new Curso();
        cursoBase.setId(1L);
        cursoBase.setNombre("1° Básico");
        cursoBase.setNivel("Básico");
        cursoBase.setSeccion("A");

        requestCurso = new CursoRequestDTO();
        requestCurso.setNombre("1° Básico");
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
    }

    // ── Tests CursoService ────────────────────────────────────────────────────

    @Test
    void guardarCurso_debeRetornarDTO() {

        when(cursoRepository.save(any(Curso.class)))
            .thenReturn(cursoBase);

        CursoResponseDTO resultado = cursoService.guardar(requestCurso);

        assertNotNull(resultado);
        assertEquals("1° Básico", resultado.getNombre());
        assertEquals("Básico", resultado.getNivel());
        assertEquals("A", resultado.getSeccion());
        verify(cursoRepository, times(1)).save(any(Curso.class));
    }

    @Test
    void listarCursos_debeRetornarLista() {

        when(cursoRepository.findAll())
            .thenReturn(List.of(cursoBase));

        List<CursoResponseDTO> resultado = cursoService.listarTodos();

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals("1° Básico", resultado.get(0).getNombre());
    }

    @Test
    void listarCursos_listaVacia_debeRetornarListaVacia() {

        when(cursoRepository.findAll())
            .thenReturn(List.of());

        List<CursoResponseDTO> resultado = cursoService.listarTodos();

        assertNotNull(resultado);
        assertTrue(resultado.isEmpty());
    }

    @Test
    void buscarCursoPorId_debeRetornarDTO() {

        when(cursoRepository.findById(1L))
            .thenReturn(Optional.of(cursoBase));

        CursoResponseDTO resultado = cursoService.buscarPorId(1L);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
        assertEquals("1° Básico", resultado.getNombre());
    }

    @Test
    void buscarCursoPorId_noExiste_debeLanzarExcepcion() {

        when(cursoRepository.findById(99L))
            .thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> {
            cursoService.buscarPorId(99L);
        });
    }

    @Test
    void eliminarCurso_sinDependencias_debeEliminar() {

        when(cursoRepository.findById(1L))
            .thenReturn(Optional.of(cursoBase));

        when(asignaturaRepository.findByCursoId(1L))
            .thenReturn(List.of());

        when(asignacionDocenteRepository.findByCursoId(1L))
            .thenReturn(List.of());

        cursoService.eliminar(1L);

        verify(cursoRepository, times(1)).delete(cursoBase);
    }

    @Test
    void eliminarCurso_conAsignaturas_debeLanzarExcepcion() {

        when(cursoRepository.findById(1L))
            .thenReturn(Optional.of(cursoBase));

        when(asignaturaRepository.findByCursoId(1L))
            .thenReturn(List.of(asignaturaBase));

        assertThrows(RuntimeException.class, () -> {
            cursoService.eliminar(1L);
        });

        verify(cursoRepository, never()).delete(any());
    }

    // ── Tests AsignaturaService ───────────────────────────────────────────────

    @Test
    void guardarAsignatura_debeRetornarDTO() {

        when(cursoRepository.findById(1L))
            .thenReturn(Optional.of(cursoBase));

        when(asignaturaRepository.save(any(Asignatura.class)))
            .thenReturn(asignaturaBase);

        AsignaturaResponseDTO resultado = asignaturaService.guardar(requestAsignatura);

        assertNotNull(resultado);
        assertEquals("Matemáticas", resultado.getNombre());
        assertEquals("MAT001", resultado.getCodigo());
        assertEquals(1L, resultado.getCursoId());
        verify(asignaturaRepository, times(1)).save(any(Asignatura.class));
    }

    @Test
    void guardarAsignatura_cursoNoExiste_debeLanzarExcepcion() {

        when(cursoRepository.findById(99L))
            .thenReturn(Optional.empty());

        requestAsignatura.setCursoId(99L);

        assertThrows(RuntimeException.class, () -> {
            asignaturaService.guardar(requestAsignatura);
        });
    }

    @Test
    void listarAsignaturas_debeRetornarLista() {

        when(asignaturaRepository.findAll())
            .thenReturn(List.of(asignaturaBase));

        List<AsignaturaResponseDTO> resultado = asignaturaService.listarTodas();

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals("Matemáticas", resultado.get(0).getNombre());
    }

    @Test
    void listarAsignaturasPorCurso_debeRetornarLista() {

        when(asignaturaRepository.findByCursoId(1L))
            .thenReturn(List.of(asignaturaBase));

        List<AsignaturaResponseDTO> resultado =
            asignaturaService.listarPorCurso(1L);

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals("MAT001", resultado.get(0).getCodigo());
    }

    @Test
    void eliminarAsignatura_conAsignaciones_debeLanzarExcepcion() {

        when(asignaturaRepository.findById(1L))
            .thenReturn(Optional.of(asignaturaBase));

        when(asignacionDocenteRepository.findByAsignaturaId(1L))
            .thenReturn(List.of(new com.ev2.academico.model.AsignacionDocente()));

        assertThrows(RuntimeException.class, () -> {
            asignaturaService.eliminar(1L);
        });

        verify(asignaturaRepository, never()).delete(any());
    }
}