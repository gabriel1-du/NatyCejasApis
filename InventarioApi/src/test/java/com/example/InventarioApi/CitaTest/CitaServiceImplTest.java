package com.example.InventarioApi.CitaTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.InventarioApi.Model.Cita;
import com.example.InventarioApi.Repository.CitaRepositoriesFolder.CitaRepository;
import com.example.InventarioApi.ServiceImpl.CitaServiceImplFolder.CitaServiceImpl;

public class CitaServiceImplTest {

    @Mock
    private CitaRepository citaRepository;

    @InjectMocks
    private CitaServiceImpl citaServiceImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCrearDefaultEstadoPendiente() {
        Cita nueva = Cita.builder()
                .id_usuario(1)
                .id_servicio(2)
                .fecha(LocalDate.of(2025, 1, 1))
                .hora(LocalTime.of(10, 0))
                .estado(null)
                .build();

        when(citaRepository.save(any(Cita.class))).thenAnswer(invocation -> {
            Cita c = invocation.getArgument(0);
            c.setId_cita(100);
            return c;
        });

        Cita result = citaServiceImpl.crear(nueva);
        assertNotNull(result.getId_cita());
        assertEquals("PENDIENTE", result.getEstado());
    }

    @Test
    void testActualizarEstadoBlankSeMantienePendiente() {
        Cita existente = Cita.builder()
                .id_cita(1)
                .id_usuario(10)
                .id_servicio(20)
                .fecha(LocalDate.of(2025, 1, 1))
                .hora(LocalTime.of(9, 0))
                .estado("CONFIRMADA")
                .build();

        when(citaRepository.findById(1)).thenReturn(Optional.of(existente));
        when(citaRepository.save(any(Cita.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Cita cambios = Cita.builder()
                .id_usuario(11)
                .id_servicio(22)
                .fecha(LocalDate.of(2025, 2, 1))
                .hora(LocalTime.of(11, 30))
                .estado(" ") // blank
                .build();

        Cita actualizado = citaServiceImpl.actualizar(1, cambios);

        assertEquals(11, actualizado.getId_usuario());
        assertEquals(22, actualizado.getId_servicio());
        assertEquals(LocalDate.of(2025, 2, 1), actualizado.getFecha());
        assertEquals(LocalTime.of(11, 30), actualizado.getHora());
        assertEquals("PENDIENTE", actualizado.getEstado());
    }

    @Test
    void testObtenerTodos() {
        Cita c1 = Cita.builder().id_cita(1).id_usuario(1).id_servicio(2).fecha(LocalDate.now()).hora(LocalTime.NOON).estado("PENDIENTE").build();
        Cita c2 = Cita.builder().id_cita(2).id_usuario(3).id_servicio(4).fecha(LocalDate.now()).hora(LocalTime.MIDNIGHT).estado("CONFIRMADA").build();
        when(citaRepository.findAll()).thenReturn(Arrays.asList(c1, c2));

        List<Cita> list = citaServiceImpl.obtenerTodos();
        assertEquals(2, list.size());
        assertEquals(1, list.get(0).getId_cita());
        assertEquals(2, list.get(1).getId_cita());
    }

    @Test
    void testObtenerPorId() {
        Cita c1 = Cita.builder().id_cita(99).id_usuario(1).id_servicio(2).fecha(LocalDate.now()).hora(LocalTime.NOON).estado("PENDIENTE").build();
        when(citaRepository.findById(99)).thenReturn(Optional.of(c1));

        Cita dto = citaServiceImpl.obtenerPorId(99);
        assertEquals(99, dto.getId_cita());
    }

    @Test
    void testEliminar() {
        Cita c1 = Cita.builder().id_cita(77).id_usuario(1).id_servicio(2).fecha(LocalDate.now()).hora(LocalTime.NOON).estado("PENDIENTE").build();
        when(citaRepository.findById(77)).thenReturn(Optional.of(c1));

        citaServiceImpl.eliminar(77);
        verify(citaRepository).delete(c1);
    }
}