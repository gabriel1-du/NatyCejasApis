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

import com.natycejas.GestionUsuariosApi.ModelFolder.Region;
import com.natycejas.GestionUsuariosApi.RepositoryFolder.RegionRepository;

@ExtendWith(MockitoExtension.class)
class RegionServiceImplTest {

    @InjectMocks
    private RegionServiceImpl regionService;

    @Mock
    private RegionRepository regionRepository;

    private Region region;
    private Region regionActualizada;

    @BeforeEach
    void setUp() {
        region = new Region(1, "Metropolitana");
        regionActualizada = new Region(1, "Valparaiso");
    }

    @Test
    void crearRegion_deberiaGuardar() {
        when(regionRepository.save(region)).thenReturn(region);
        Region res = regionService.crearRegion(region);
        assertNotNull(res);
        assertEquals("Metropolitana", res.getNombreRegion());
        verify(regionRepository).save(region);
    }

    @Test
    void obtenerRegionPorId_deberiaRetornar() {
        when(regionRepository.findById(1)).thenReturn(Optional.of(region));
        Region res = regionService.obtenerRegionPorId(1);
        assertEquals(1, res.getIdRegion());
        verify(regionRepository).findById(1);
    }

    @Test
    void actualizarRegion_deberiaActualizarNombre() {
        when(regionRepository.findById(1)).thenReturn(Optional.of(region));
        when(regionRepository.save(any(Region.class))).thenReturn(regionActualizada);
        Region res = regionService.actualizarRegion(1, regionActualizada);
        assertEquals("Valparaiso", res.getNombreRegion());
        verify(regionRepository).findById(1);
        verify(regionRepository).save(any(Region.class));
    }

    @Test
    void listarRegiones_deberiaRetornarLista() {
        when(regionRepository.findAll()).thenReturn(List.of(region));
        List<Region> res = regionService.listarRegiones();
        assertEquals(1, res.size());
        verify(regionRepository).findAll();
    }

    @Test
    void eliminarRegion_deberiaEliminarSiExiste() {
        when(regionRepository.existsById(1)).thenReturn(true);
        regionService.eliminarRegion(1);
        verify(regionRepository).existsById(1);
        verify(regionRepository).deleteById(1);
    }
}