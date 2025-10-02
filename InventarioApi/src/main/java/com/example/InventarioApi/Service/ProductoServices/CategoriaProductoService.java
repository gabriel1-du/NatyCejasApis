package com.example.InventarioApi.Service.ProductoServices;

import java.util.List;
import java.util.Optional;

import com.example.InventarioApi.Model.ProductoModels.CategoriaProducto;

public interface CategoriaProductoService {
    
    List<CategoriaProducto> listarCategorias();

    Optional<CategoriaProducto> obtenerCategoriaPorId(Integer id);

    CategoriaProducto guardarCategoria(CategoriaProducto categoria);

    void eliminarCategoria(Integer id);

}
