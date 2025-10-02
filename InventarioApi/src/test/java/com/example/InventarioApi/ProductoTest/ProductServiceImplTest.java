package com.example.InventarioApi.ProductoTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.InventarioApi.DTO.ProductoDTOs.CreateProductoDTO;
import com.example.InventarioApi.DTO.ProductoDTOs.ProductoDTO;
import com.example.InventarioApi.Model.ProductoModels.Producto;
import com.example.InventarioApi.Repository.ProductoRepositories.ProductoRepository;
import com.example.InventarioApi.ServiceImpl.ProductoServiceImplFolder.ProductoServiceImpl;

public class ProductServiceImplTest {
    
    @Mock
    private ProductoRepository productoRepositorio;

    @InjectMocks
    private ProductoServiceImpl productoServicio;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCrearProducto() {
        // Arrange: DTO de entrada
        CreateProductoDTO createDTO = CreateProductoDTO.builder()
                .nombre_producto("Shampoo")
                .descripcion("Shampoo revitalizante")
                .precio(5000)
                .stock(10)
                .id_marca(1)
                .id_categoria(2)
                .build();

        // Simular la entidad que guardará el repositorio
        Producto productoGuardado = new Producto();
        productoGuardado.setId_producto(1);
        productoGuardado.setNombre_producto("Shampoo");
        productoGuardado.setDescripcion("Shampoo revitalizante");
        productoGuardado.setPrecio(5000);
        productoGuardado.setStock(10);

        // Mock del repositorio
        when(productoRepositorio.save(any(Producto.class))).thenReturn(productoGuardado);

        // Act: ejecutar el servicio
        ProductoDTO resultado = productoServicio.crearProducto(createDTO);

        // Assert: verificar resultados
        assertNotNull(resultado);
        assertEquals(1, resultado.getId_producto());
        assertEquals("Shampoo", resultado.getNombre_producto());
        assertEquals("Shampoo revitalizante", resultado.getDescripcion());
        assertEquals(5000, resultado.getPrecio());
        assertEquals(10, resultado.getStock());
    }

}
