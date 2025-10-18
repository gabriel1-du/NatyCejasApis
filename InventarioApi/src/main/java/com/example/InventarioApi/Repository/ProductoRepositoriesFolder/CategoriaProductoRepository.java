package com.example.InventarioApi.Repository.ProductoRepositoriesFolder;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.InventarioApi.Model.ProductoModelsFolder.CategoriaProducto;

public interface CategoriaProductoRepository extends JpaRepository<CategoriaProducto, Integer> {
    
}
