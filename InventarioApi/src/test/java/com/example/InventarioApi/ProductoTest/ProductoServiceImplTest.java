package com.example.InventarioApi.ProductoTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.InventarioApi.DTO.ProductoDTOs.CreateProductoDTO;
import com.example.InventarioApi.DTO.ProductoDTOs.ProductoDTO;
import com.example.InventarioApi.Model.Marca;
import com.example.InventarioApi.Model.ProductoModelsFolder.CategoriaProducto;
import com.example.InventarioApi.Model.ProductoModelsFolder.Producto;
import com.example.InventarioApi.Repository.MarcaRepository;
import com.example.InventarioApi.Repository.ProductoRepositoriesFolder.CategoriaProductoRepository;
import com.example.InventarioApi.Repository.ProductoRepositoriesFolder.ProductoRepository;
import com.example.InventarioApi.ServiceImpl.ProductoServiceImplFolder.ProductoServiceImpl;

import java.util.Optional;

public class ProductoServiceImplTest {

    @Mock
    private ProductoRepository productoRepository;

    @Mock
    private MarcaRepository marcaRepositorio;

    @Mock
    private CategoriaProductoRepository categoriaProductoRepositorio;

    @InjectMocks
    private ProductoServiceImpl productoServiceImpl;

    private Marca marca;
    private CategoriaProducto categoria;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        marca = new Marca();
        marca.setId_marca(1);
        marca.setNombre_marca("Acme");
        categoria = new CategoriaProducto();
        categoria.setId_categoria_producto(2);
        categoria.setNombre_categoria_producto("Capilares");

        when(marcaRepositorio.findById(1)).thenReturn(Optional.of(marca));
        when(categoriaProductoRepositorio.findById(2)).thenReturn(Optional.of(categoria));

        // Simular save devolviendo el mismo producto con ID asignado
        when(productoRepository.save(any(Producto.class))).thenAnswer(invocation -> {
            Producto p = invocation.getArgument(0);
            p.setId_producto(100);
            return p;
        });
    }

    @Test
    void crearProducto_seteaFotoUrlDesdeDTO_trim_y_limita255() {
        // Arrange
        String baseUrl = "  https://example.com/img/producto.jpg  ";
        String longUrl = baseUrl.repeat(20); // asegura > 255
        CreateProductoDTO dto = CreateProductoDTO.builder()
                .nombre_producto("Shampoo")
                .descripcion("Suave")
                .precio(1500)
                .stock(10)
                .id_marca(1)
                .id_categoria(2)
                .foto_url(longUrl) // muy largo y con espacios
                .build();

        // Act
        ProductoDTO result = productoServiceImpl.crearProducto(dto);

        // Assert
        assertNotNull(result);
        assertEquals(100, result.getId_producto());
        assertNotNull(result.getFoto_url());
        String trimmed = longUrl.trim();
        String expected = trimmed.length() > 255 ? trimmed.substring(0, 255) : trimmed;
        assertEquals(expected, result.getFoto_url());
    }

    @Test
    void crearProducto_noSeteaFotoUrl_siBlankONull() {
        // Caso blank
        CreateProductoDTO dtoBlank = CreateProductoDTO.builder()
                .nombre_producto("Acondicionador")
                .descripcion("Hidratante")
                .precio(1200)
                .stock(5)
                .id_marca(1)
                .id_categoria(2)
                .foto_url("   ")
                .build();
        ProductoDTO resBlank = productoServiceImpl.crearProducto(dtoBlank);
        assertNull(resBlank.getFoto_url());

        // Caso null
        CreateProductoDTO dtoNull = CreateProductoDTO.builder()
                .nombre_producto("Gel")
                .descripcion("Fijación")
                .precio(900)
                .stock(7)
                .id_marca(1)
                .id_categoria(2)
                .foto_url(null)
                .build();
        ProductoDTO resNull = productoServiceImpl.crearProducto(dtoNull);
        assertNull(resNull.getFoto_url());
    }
}