package com.example.InventarioApi.MarcaTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.InventarioApi.Model.Marca;
import com.example.InventarioApi.Repository.MarcaRepository;
import com.example.InventarioApi.ServiceImpl.MarcaServiceImpl;

public class MarcaServiceImplTest {

    @Mock
    private MarcaRepository marcaRepository;

    @InjectMocks
    private MarcaServiceImpl marcaServiceImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testListarMarcas() {
        Marca m1 = new Marca();
        m1.setId_marca(1);
        m1.setNombre_marca("Acme");
        Marca m2 = new Marca();
        m2.setId_marca(2);
        m2.setNombre_marca("Globex");

        when(marcaRepository.findAll()).thenReturn(Arrays.asList(m1, m2));

        List<Marca> list = marcaServiceImpl.listarMarcas();
        assertEquals(2, list.size());
        assertEquals("Acme", list.get(0).getNombre_marca());
        assertEquals("Globex", list.get(1).getNombre_marca());
    }

    @Test
    void testGuardarMarca() {
        Marca entrada = new Marca();
        entrada.setNombre_marca("Umbrella");

        Marca guardada = new Marca();
        guardada.setId_marca(10);
        guardada.setNombre_marca("Umbrella");

        when(marcaRepository.save(any(Marca.class))).thenReturn(guardada);

        Marca result = marcaServiceImpl.guardarMarca(entrada);
        assertNotNull(result);
        assertEquals(10, result.getId_marca());
        assertEquals("Umbrella", result.getNombre_marca());
    }

    @Test
    void testObtenerMarcaPorId() {
        Marca m = new Marca();
        m.setId_marca(7);
        m.setNombre_marca("Initech");
        when(marcaRepository.findById(7)).thenReturn(Optional.of(m));

        Optional<Marca> result = marcaServiceImpl.obtenerMarcaPorId(7);
        assertTrue(result.isPresent());
        assertEquals("Initech", result.get().getNombre_marca());
    }

    @Test
    void testEliminarMarca() {
        // No retorna nada, solo verificamos que se invoca
        marcaServiceImpl.eliminarMarca(5);
        verify(marcaRepository).deleteById(5);
    }
}