package com.example.InventarioApi.Service;

import java.util.List;

import com.example.InventarioApi.DTO.CreateProductoDTO;
import com.example.InventarioApi.DTO.ProductoDTO;
import com.example.InventarioApi.Model.Producto;

public interface ProductoService {

    List<ProductoDTO> obtenerTodosLosProductos();

    ProductoDTO obtenerProductoPorId(Integer id);

    ProductoDTO crearProducto(CreateProductoDTO createProductoDTO);

    void eliminarProducto(Integer id);

    Producto sumarStock(Integer id, Integer cantidad);

    Producto restarStock(Integer id, Integer cantidad);

}
