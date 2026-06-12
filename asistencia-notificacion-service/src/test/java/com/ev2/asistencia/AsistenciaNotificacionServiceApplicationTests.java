package com.ev2.asistencia;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.ev2.asistencia.dto.AnotacionRequestDTO;
import com.ev2.asistencia.dto.AnotacionResponseDTO;
import com.ev2.asistencia.dto.AsistenciaRequestDTO;
import com.ev2.asistencia.dto.AsistenciaResponseDTO;
import com.ev2.asistencia.dto.MensajeRequestDTO;
import com.ev2.asistencia.dto.MensajeResponseDTO;
import com.ev2.asistencia.dto.NotaRequestDTO;
import com.ev2.asistencia.dto.NotaResponseDTO;
import com.ev2.asistencia.kafka.KafkaProducerService;
import com.ev2.asistencia.model.Anotacion;
import com.ev2.asistencia.model.Asistencia;
import com.ev2.asistencia.model.EstadoAsistencia;
import com.ev2.asistencia.model.Mensaje;
import com.ev2.asistencia.model.Nota;
import com.ev2.asistencia.model.TipoAnotacion;
import com.ev2.asistencia.repository.AnotacionRepository;
import com.ev2.asistencia.repository.AsistenciaRepository;
import com.ev2.asistencia.repository.MensajeRepository;
import com.ev2.asistencia.repository.NotaRepository;
import com.ev2.asistencia.service.AnotacionServiceImpl;
import com.ev2.asistencia.service.AsistenciaServiceImpl;
import com.ev2.asistencia.service.MensajeServiceImpl;
import com.ev2.asistencia.service.NotaServiceImpl;

@ExtendWith(MockitoExtension.class)
class AsistenciaNotificacionServiceApplicationTests {

    // ── Mocks AsistenciaService ───────────────────────────────────────────────

    @Mock
    private AsistenciaRepository asistenciaRepository;

    @Mock
    private KafkaProducerService kafkaProducerService;

    @InjectMocks
    private AsistenciaServiceImpl asistenciaService;

    // ── Mocks NotaService ─────────────────────────────────────────────────────

    @Mock
    private NotaRepository notaRepository;

    @InjectMocks
    private NotaServiceImpl notaService;

    // ── Mocks AnotacionService ────────────────────────────────────────────────

    @Mock
    private AnotacionRepository anotacionRepository;

    @InjectMocks
    private AnotacionServiceImpl anotacionService;

    // ── Mocks MensajeService ──────────────────────────────────────────────────

    @Mock
    private MensajeRepository mensajeRepository;

    @InjectMocks
    private MensajeServiceImpl mensajeService;

    // ── Datos de prueba ───────────────────────────────────────────────────────

    private Asistencia asistenciaBase;
    private AsistenciaRequestDTO requestAsistencia;

    private Nota notaBase;
    private NotaRequestDTO requestNota;

    private Anotacion anotacionBase;
    private AnotacionRequestDTO requestAnotacion;

    private Mensaje mensajeBase;
    private MensajeRequestDTO requestMensaje;

    @BeforeEach
    void setUp() {

        // Asistencia
        asistenciaBase = new Asistencia();
        asistenciaBase.setId(1L);
        asistenciaBase.setEstudianteId(10L);
        asistenciaBase.setEstado(EstadoAsistencia.PRESENTE);
        asistenciaBase.setFechaHora(LocalDateTime.now());

        requestAsistencia = new AsistenciaRequestDTO();
        requestAsistencia.setEstudianteId(10L);
        requestAsistencia.setEstado(EstadoAsistencia.PRESENTE);

        // Nota
        notaBase = new Nota();
        notaBase.setId(1L);
        notaBase.setEstudianteId(10L);
        notaBase.setAsignaturaId(5L);
        notaBase.setNota(6.5);
        notaBase.setDescripcion("Prueba 1");
        notaBase.setFechaRegistro(LocalDateTime.now());

        requestNota = new NotaRequestDTO();
        requestNota.setEstudianteId(10L);
        requestNota.setAsignaturaId(5L);
        requestNota.setNota(6.5);
        requestNota.setDescripcion("Prueba 1");

        // Anotacion
        anotacionBase = new Anotacion();
        anotacionBase.setId(1L);
        anotacionBase.setEstudianteId(10L);
        anotacionBase.setDescripcion("Buen comportamiento");
        anotacionBase.setTipo(TipoAnotacion.POSITIVA);
        anotacionBase.setFechaCreacion(LocalDateTime.now());

        requestAnotacion = new AnotacionRequestDTO();
        requestAnotacion.setEstudianteId(10L);
        requestAnotacion.setDescripcion("Buen comportamiento");
        requestAnotacion.setTipo(TipoAnotacion.POSITIVA);

        // Mensaje
        mensajeBase = new Mensaje();
        mensajeBase.setId(1L);
        mensajeBase.setRemitenteId(1L);
        mensajeBase.setDestinatarioId(2L);
        mensajeBase.setContenido("Hola docente");
        mensajeBase.setFechaEnvio(LocalDateTime.now());
        mensajeBase.setLeido(false);

        requestMensaje = new MensajeRequestDTO();
        requestMensaje.setRemitenteId(1L);
        requestMensaje.setDestinatarioId(2L);
        requestMensaje.setContenido("Hola docente");
    }

    // ── Tests AsistenciaService ───────────────────────────────────────────────

    @Test
    void guardarAsistencia_debeRetornarDTO() {

        when(asistenciaRepository.save(any(Asistencia.class)))
            .thenReturn(asistenciaBase);

        doNothing().when(kafkaProducerService)
            .enviarAsistenciaRegistrada(any());

        AsistenciaResponseDTO resultado = asistenciaService.guardar(requestAsistencia);

        assertNotNull(resultado);
        assertEquals(10L, resultado.getEstudianteId());
        assertEquals(EstadoAsistencia.PRESENTE, resultado.getEstado());
        verify(asistenciaRepository, times(1)).save(any(Asistencia.class));
        verify(kafkaProducerService, times(1)).enviarAsistenciaRegistrada(any());
    }

    @Test
    void listarAsistenciasPorEstudiante_debeRetornarLista() {

        when(asistenciaRepository.findByEstudianteId(10L))
            .thenReturn(List.of(asistenciaBase));

        List<AsistenciaResponseDTO> resultado =
            asistenciaService.listarPorEstudiante(10L);

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals(EstadoAsistencia.PRESENTE, resultado.get(0).getEstado());
    }

    @Test
    void listarAsistenciasPorEstudiante_sinRegistros_debeRetornarListaVacia() {

        when(asistenciaRepository.findByEstudianteId(99L))
            .thenReturn(List.of());

        List<AsistenciaResponseDTO> resultado =
            asistenciaService.listarPorEstudiante(99L);

        assertNotNull(resultado);
        assertTrue(resultado.isEmpty());
    }

    // ── Tests NotaService ─────────────────────────────────────────────────────

    @Test
    void guardarNota_debeRetornarDTO() {

        when(notaRepository.save(any(Nota.class)))
            .thenReturn(notaBase);

        NotaResponseDTO resultado = notaService.guardar(requestNota);

        assertNotNull(resultado);
        assertEquals(10L, resultado.getEstudianteId());
        assertEquals(6.5, resultado.getNota());
        assertEquals("Prueba 1", resultado.getDescripcion());
        verify(notaRepository, times(1)).save(any(Nota.class));
    }

    @Test
    void listarNotasPorEstudiante_debeRetornarLista() {

        when(notaRepository.findByEstudianteId(10L))
            .thenReturn(List.of(notaBase));

        List<NotaResponseDTO> resultado =
            notaService.listarPorEstudiante(10L);

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals(6.5, resultado.get(0).getNota());
    }

    @Test
    void listarNotasPorEstudiante_sinRegistros_debeRetornarListaVacia() {

        when(notaRepository.findByEstudianteId(99L))
            .thenReturn(List.of());

        List<NotaResponseDTO> resultado =
            notaService.listarPorEstudiante(99L);

        assertTrue(resultado.isEmpty());
    }

    // ── Tests AnotacionService ────────────────────────────────────────────────

    @Test
    void guardarAnotacion_debeRetornarDTO() {

        when(anotacionRepository.save(any(Anotacion.class)))
            .thenReturn(anotacionBase);

        AnotacionResponseDTO resultado =
            anotacionService.guardar(requestAnotacion);

        assertNotNull(resultado);
        assertEquals(10L, resultado.getEstudianteId());
        assertEquals(TipoAnotacion.POSITIVA, resultado.getTipo());
        verify(anotacionRepository, times(1)).save(any(Anotacion.class));
    }

    @Test
    void listarAnotacionesPorEstudiante_debeRetornarLista() {

        when(anotacionRepository.findByEstudianteId(10L))
            .thenReturn(List.of(anotacionBase));

        List<AnotacionResponseDTO> resultado =
            anotacionService.listarPorEstudiante(10L);

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals("Buen comportamiento", resultado.get(0).getDescripcion());
    }

    // ── Tests MensajeService ──────────────────────────────────────────────────

    @Test
    void enviarMensaje_debeRetornarDTO() {

        when(mensajeRepository.save(any(Mensaje.class)))
            .thenReturn(mensajeBase);

        MensajeResponseDTO resultado = mensajeService.enviar(requestMensaje);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getRemitenteId());
        assertEquals(2L, resultado.getDestinatarioId());
        assertEquals("Hola docente", resultado.getContenido());
        assertFalse(resultado.getLeido());
        verify(mensajeRepository, times(1)).save(any(Mensaje.class));
    }

    @Test
    void obtenerMensajesRecibidos_debeRetornarLista() {

        when(mensajeRepository.findByDestinatarioId(2L))
            .thenReturn(List.of(mensajeBase));

        List<MensajeResponseDTO> resultado =
            mensajeService.obtenerRecibidos(2L);

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals("Hola docente", resultado.get(0).getContenido());
    }

    @Test
    void obtenerConversacion_debeRetornarLista() {

        when(mensajeRepository
            .findByRemitenteIdAndDestinatarioIdOrRemitenteIdAndDestinatarioId(
                1L, 2L, 2L, 1L))
            .thenReturn(List.of(mensajeBase));

        List<MensajeResponseDTO> resultado =
            mensajeService.obtenerConversacion(1L, 2L);

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
    }
}