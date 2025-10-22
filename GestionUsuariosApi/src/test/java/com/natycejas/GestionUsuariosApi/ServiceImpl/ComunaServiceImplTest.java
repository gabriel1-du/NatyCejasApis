package com.natycejas.GestionUsuariosApi.ServiceImpl;

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

import com.natycejas.GestionUsuariosApi.ModelFolder.Comuna;
import com.natycejas.GestionUsuariosApi.ModelFolder.Region;
import com.natycejas.GestionUsuariosApi.RepositoryFolder.ComunaRepository;

@ExtendWith(MockitoExtension.class)
class ComunaServiceImplTest {

    @InjectMocks
    private ComunaServiceImpl comunaService;

    @Mock
    private ComunaRepository comunaRepository;

    private Region region;
    private Comuna comuna;
    private Comuna comunaActualizada;

    @BeforeEach
    void setUp() {
        region = new Region(1, "Metropolitana");
        comuna = new Comuna(2, "Santiago", region);
        comunaActualizada = new Comuna(2, "Providencia", region);
    }

    @Test
    void crearComuna_deberiaGuardar() {
        when(comunaRepository.save(comuna)).thenReturn(comuna);
        Comuna res = comunaService.crearComuna(comuna);
        assertNotNull(res);
        assertEquals("Santiago", res.getNombreComuna());
        verify(comunaRepository).save(comuna);
    }

    @Test
    void obtenerComunaPorId_deberiaRetornar() {
        when(comunaRepository.findById(2)).thenReturn(Optional.of(comuna));
        Comuna res = comunaService.obtenerComunaPorId(2);
        assertEquals(2, res.getIdComuna());
        verify(comunaRepository).findById(2);
    }

    @Test
    void listarComunas_deberiaRetornarLista() {
        when(comunaRepository.findAll()).thenReturn(List.of(comuna));
        List<Comuna> res = comunaService.listarComunas();
        assertEquals(1, res.size());
        verify(comunaRepository).findAll();
    }

    @Test
    void actualizarComuna_deberiaActualizarNombreYRegion() {
        when(comunaRepository.findById(2)).thenReturn(Optional.of(comuna));
        when(comunaRepository.save(any(Comuna.class))).thenReturn(comunaActualizada);
        Comuna res = comunaService.actualizarComuna(2, comunaActualizada);
        assertEquals("Providencia", res.getNombreComuna());
        verify(comunaRepository).findById(2);
        verify(comunaRepository).save(any(Comuna.class));
    }

    @Test
    void eliminarComuna_deberiaEliminarSiExiste() {
        when(comunaRepository.existsById(2)).thenReturn(true);
        comunaService.eliminarComuna(2);
        verify(comunaRepository).existsById(2);
        verify(comunaRepository).deleteById(2);
    }
}