package com.example.InventarioApi.Repository.ProductoRepositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.InventarioApi.Model.ProductoModels.CategoriaProducto;

public interface CategoriaProductoRepository extends JpaRepository<CategoriaProducto, Integer> {
    
}
