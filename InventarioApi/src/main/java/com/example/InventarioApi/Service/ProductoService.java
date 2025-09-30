package com.example.InventarioApi.Service;

import java.util.List;
import java.util.Optional;

import com.example.InventarioApi.Model.Producto;

public interface ProductoService {

    List<Producto> listarProductos();

    Optional<Producto> obtenerProductoPorId(Integer id);

    Producto guardarProducto(Producto producto);

    void eliminarProducto(Integer id);

    Producto sumarStock(Integer id, Integer cantidad);

    Producto restarStock(Integer id, Integer cantidad);

}
