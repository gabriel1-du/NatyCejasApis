package com.example.InventarioApi.CategoriaProductoTest;

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

import com.example.InventarioApi.Model.ProductoModelsFolder.CategoriaProducto;
import com.example.InventarioApi.Repository.ProductoRepositoriesFolder.CategoriaProductoRepository;
import com.example.InventarioApi.ServiceImpl.ProductoServiceImplFolder.CategoriaProductoServiceImpl;

public class CategoriaProductoServiceImplTest {

    @Mock
    private CategoriaProductoRepository categoriaProductoRepositorio;

    @InjectMocks
    private CategoriaProductoServiceImpl categoriaProductoServiceImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testListarCategorias() {
        CategoriaProducto c1 = new CategoriaProducto();
        c1.setId_categoria_producto(1);
        c1.setNombre_categoria_producto("Capilares");
        CategoriaProducto c2 = new CategoriaProducto();
        c2.setId_categoria_producto(2);
        c2.setNombre_categoria_producto("Faciales");

        when(categoriaProductoRepositorio.findAll()).thenReturn(Arrays.asList(c1, c2));

        List<CategoriaProducto> list = categoriaProductoServiceImpl.listarCategorias();
        assertEquals(2, list.size());
        assertEquals("Capilares", list.get(0).getNombre_categoria_producto());
        assertEquals("Faciales", list.get(1).getNombre_categoria_producto());
    }

    @Test
    void testGuardarCategoria() {
        CategoriaProducto entrada = new CategoriaProducto();
        entrada.setNombre_categoria_producto("Cejas");

        CategoriaProducto guardada = new CategoriaProducto();
        guardada.setId_categoria_producto(10);
        guardada.setNombre_categoria_producto("Cejas");

        when(categoriaProductoRepositorio.save(any(CategoriaProducto.class))).thenReturn(guardada);

        CategoriaProducto result = categoriaProductoServiceImpl.guardarCategoria(entrada);
        assertNotNull(result);
        assertEquals(10, result.getId_categoria_producto());
        assertEquals("Cejas", result.getNombre_categoria_producto());
    }

    @Test
    void testObtenerCategoriaPorId() {
        CategoriaProducto c = new CategoriaProducto();
        c.setId_categoria_producto(7);
        c.setNombre_categoria_producto("Uñas");
        when(categoriaProductoRepositorio.findById(7)).thenReturn(Optional.of(c));

        Optional<CategoriaProducto> result = categoriaProductoServiceImpl.obtenerCategoriaPorId(7);
        assertTrue(result.isPresent());
        assertEquals("Uñas", result.get().getNombre_categoria_producto());
    }

    @Test
    void testEliminarCategoria() {
        categoriaProductoServiceImpl.eliminarCategoria(5);
        verify(categoriaProductoRepositorio).deleteById(5);
    }
}