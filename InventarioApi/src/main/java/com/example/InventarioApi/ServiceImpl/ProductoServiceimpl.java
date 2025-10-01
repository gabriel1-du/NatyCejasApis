package com.example.InventarioApi.ServiceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.InventarioApi.DTO.CreateProductoDTO;
import com.example.InventarioApi.DTO.MapperProducto;
import com.example.InventarioApi.DTO.ProductoDTO;
import com.example.InventarioApi.Model.Producto;
import com.example.InventarioApi.Repositoriy.ProductoRepository;
import com.example.InventarioApi.Service.ProductoService;

@Service
public class ProductoServiceImpl implements ProductoService {
    @Autowired
    private ProductoRepository productoRepository;

    public List<Producto> listarProductos() {
        return productoRepository.findAll();
    }

    public Producto guardarProducto(Producto producto) {
        return productoRepository.save(producto);
    }

    public Optional<Producto> obtenerProductoPorId(Integer id) {
        return productoRepository.findById(id);
    }

    public void eliminarProducto(Integer id) {
        productoRepository.deleteById(id);
    }

    public ProductoDTO crearProducto(CreateProductoDTO createProductoDTO) {
        Producto producto = MapperProducto.toEntity(createProductoDTO);
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
