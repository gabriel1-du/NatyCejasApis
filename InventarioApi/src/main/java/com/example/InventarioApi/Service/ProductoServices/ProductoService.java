package com.example.InventarioApi.Service.ProductoServices;

import java.util.List;

import com.example.InventarioApi.DTO.ProductoDTOs.CreateProductoDTO;
import com.example.InventarioApi.DTO.ProductoDTOs.ProductoDTO;
import com.example.InventarioApi.DTO.ProductoDTOs.UpdateProductoDTO;
import com.example.InventarioApi.Model.ProductoModels.Producto;

public interface ProductoService {

    List<ProductoDTO> obtenerTodosLosProductos();

    ProductoDTO obtenerProductoPorId(Integer id);

    ProductoDTO crearProducto(CreateProductoDTO createProductoDTO);

    ProductoDTO actualizarProducto(Integer id, UpdateProductoDTO updateDTO);

    void eliminarProducto(Integer id);

    Producto sumarStock(Integer id, Integer cantidad);

    Producto restarStock(Integer id, Integer cantidad);

}
