package com.example.InventarioApi.ServicioTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.InventarioApi.DTO.ServicioDTOs.CreateServicioDTO;
import com.example.InventarioApi.DTO.ServicioDTOs.ServicioDTO;
import com.example.InventarioApi.DTO.ServicioDTOs.UpdateServicioDTO;
import com.example.InventarioApi.Model.ServiciosModelsFolder.CategoriaServicio;
import com.example.InventarioApi.Model.ServiciosModelsFolder.Servicio;
import com.example.InventarioApi.Repository.ServiciosRepositoriesFolder.CategoriaServicioRepository;
import com.example.InventarioApi.Repository.ServiciosRepositoriesFolder.ServicioRepository;
import com.example.InventarioApi.ServiceImpl.ServicioServiceImplsFolder.ServicioServiceImpl;

public class ServicioServiceImplTest {

    @Mock
    private ServicioRepository servicioRepository;

    @Mock
    private CategoriaServicioRepository categoriaServicioRepository;

    @InjectMocks
    private ServicioServiceImpl servicioServiceImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGuardarServicio() {
        CreateServicioDTO dto = CreateServicioDTO.builder()
                .nombre_servicio("Corte")
                .descripcion("Rápido y eficiente")
                .precio("1000")
                .id_categoria_servicio(1)
                .build();

        CategoriaServicio categoria = new CategoriaServicio();
        categoria.setId_categoria_servicio(1);
        categoria.setNombre_categoria_servicio("Belleza");

        when(categoriaServicioRepository.findById(1)).thenReturn(Optional.of(categoria));
        when(servicioRepository.save(any(Servicio.class))).thenAnswer(invocation -> {
            Servicio s = invocation.getArgument(0);
            s.setId_servicio(10);
            return s;
        });

        ServicioDTO result = servicioServiceImpl.guardar(dto);

        assertNotNull(result);
        assertEquals(10, result.getId_servicio());
        assertEquals("Corte", result.getNombre_servicio());
        assertEquals("Rápido y eficiente", result.getDescripcion());
        assertEquals(1000, result.getPrecio());
        assertEquals(1, result.getId_categoria_servicio());
        assertEquals("Belleza", result.getNombre_categoria_servicio());
    }

    @Test
    void testActualizarServicio() {
        Servicio existente = new Servicio();
        existente.setId_servicio(5);
        CategoriaServicio cat1 = new CategoriaServicio();
        cat1.setId_categoria_servicio(1);
        cat1.setNombre_categoria_servicio("Inicial");
        existente.setCategoriaServicio(cat1);
        existente.setNombre_servicio("Old");
        existente.setDescripcion("Desc old");
        existente.setPrecio(500);

        when(servicioRepository.findById(5)).thenReturn(Optional.of(existente));

        CategoriaServicio cat2 = new CategoriaServicio();
        cat2.setId_categoria_servicio(2);
        cat2.setNombre_categoria_servicio("Nueva");
        when(categoriaServicioRepository.findById(2)).thenReturn(Optional.of(cat2));
        when(servicioRepository.save(any(Servicio.class))).thenAnswer(invocation -> invocation.getArgument(0));

        UpdateServicioDTO dto = UpdateServicioDTO.builder()
                .nombre_servicio("Peinado")
                .descripcion("Actual")
                .precio(1500)
                .id_categoria_servicio(2)
                .build();

        ServicioDTO result = servicioServiceImpl.actualizar(5, dto);

        assertEquals("Peinado", result.getNombre_servicio());
        assertEquals("Actual", result.getDescripcion());
        assertEquals(1500, result.getPrecio());
        assertEquals(2, result.getId_categoria_servicio());
        assertEquals("Nueva", result.getNombre_categoria_servicio());
    }

    @Test
    void testObtenerTodos() {
        CategoriaServicio cat = new CategoriaServicio();
        cat.setId_categoria_servicio(1);
        cat.setNombre_categoria_servicio("Belleza");
        Servicio s1 = new Servicio();
        s1.setId_servicio(1);
        s1.setNombre_servicio("Corte");
        s1.setDescripcion("Desc");
        s1.setPrecio(1000);
        s1.setCategoriaServicio(cat);
        Servicio s2 = new Servicio();
        s2.setId_servicio(2);
        s2.setNombre_servicio("Peinado");
        s2.setDescripcion("Desc2");
        s2.setPrecio(2000);
        s2.setCategoriaServicio(cat);

        when(servicioRepository.findAll()).thenReturn(Arrays.asList(s1, s2));

        List<ServicioDTO> list = servicioServiceImpl.obtenerTodos();
        assertEquals(2, list.size());
        assertEquals("Corte", list.get(0).getNombre_servicio());
        assertEquals("Peinado", list.get(1).getNombre_servicio());
    }

    @Test
    void testObtenerPorId() {
        CategoriaServicio cat = new CategoriaServicio();
        cat.setId_categoria_servicio(1);
        cat.setNombre_categoria_servicio("Belleza");
        Servicio s1 = new Servicio();
        s1.setId_servicio(1);
        s1.setNombre_servicio("Corte");
        s1.setDescripcion("Desc");
        s1.setPrecio(1000);
        s1.setCategoriaServicio(cat);
        when(servicioRepository.findById(1)).thenReturn(Optional.of(s1));

        ServicioDTO dto = servicioServiceImpl.obtenerPorId(1);
        assertEquals(1, dto.getId_servicio());
        assertEquals(1000, dto.getPrecio());
        assertEquals(1, dto.getId_categoria_servicio());
    }

    @Test
    void testEliminarOk() {
        when(servicioRepository.existsById(7)).thenReturn(true);
        servicioServiceImpl.eliminar(7);
        verify(servicioRepository).deleteById(7);
    }

    @Test
    void testEliminarNotFound() {
        when(servicioRepository.existsById(99)).thenReturn(false);
        assertThrows(RuntimeException.class, () -> servicioServiceImpl.eliminar(99));
        verify(servicioRepository, never()).deleteById(anyInt());
    }
}