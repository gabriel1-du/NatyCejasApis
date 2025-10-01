package com.example.InventarioApi.ServiceImpl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.InventarioApi.DTO.CreateProductoDTO;
import com.example.InventarioApi.DTO.MapperProducto;
import com.example.InventarioApi.DTO.ProductoDTO;
import com.example.InventarioApi.Model.CategoriaProducto;
import com.example.InventarioApi.Model.Marca;
import com.example.InventarioApi.Model.Producto;
import com.example.InventarioApi.Repositoriy.CategoriaProductoRepository;
import com.example.InventarioApi.Repositoriy.MarcaRepository;
import com.example.InventarioApi.Repositoriy.ProductoRepository;
import com.example.InventarioApi.Service.ProductoService;

@Service
public class ProductoServiceImpl implements ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private MarcaRepository marcaRepositorio;

    @Autowired
    private CategoriaProductoRepository categoriaProductoRepositorio;


    
    public List<ProductoDTO>  obtenerTodosLosProductos() {
        List<Producto> productos = productoRepository.findAll();
        return productos.stream()
                .map(MapperProducto::toDTO) // usamos el mapper que me pasaste
                .collect(Collectors.toList());
    }

    public Producto guardarProducto(Producto producto) {
        return productoRepository.save(producto);
    }

    public ProductoDTO obtenerProductoPorId(Integer id) {
        Optional<Producto> producto = productoRepository.findById(id);
        return producto.map(MapperProducto::toDTO).orElse(null);
    }

    public void eliminarProducto(Integer id) {
        productoRepository.deleteById(id);
    }

    public ProductoDTO crearProducto(CreateProductoDTO createDTO) {
        // Buscar Marca
        Marca marca = marcaRepositorio.findById(createDTO.getId_marca())
                .orElseThrow(() -> new RuntimeException("Marca no encontrada con id: " + createDTO.getId_marca()));

        // Buscar Categoría
        CategoriaProducto categoria = categoriaProductoRepositorio.findById(createDTO.getId_categoria())
                .orElseThrow(() -> new RuntimeException("Categoria no encontrada con id: " + createDTO.getId_categoria()));

        // Crear el Producto desde el DTO
        Producto producto = new Producto();
        producto.setNombre_producto(createDTO.getNombre_producto());
        producto.setDescripcion(createDTO.getDescripcion());
        producto.setPrecio(createDTO.getPrecio());
        producto.setStock(createDTO.getStock());
        producto.setMarca(marca);
        producto.setCategoria(categoria);

        // Guardar y mapear a DTO
        Producto productoGuardado = productoRepository.save(producto);
        return MapperProducto.toDTO(productoGuardado);
    }


    public Producto sumarStock(Integer id, Integer cantidad) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
        producto.setStock(producto.getStock() + cantidad);
        return productoRepository.save(producto);
    }

    public Producto restarStock(Integer id, Integer cantidad) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
        if (producto.getStock() < cantidad) {
            throw new RuntimeException("Stock insuficiente");
        }
        producto.setStock(producto.getStock() - cantidad);
        return productoRepository.save(producto);
    }
    
}
