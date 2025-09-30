package com.example.InventarioApi.Repositoriy;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.InventarioApi.Model.CategoriaProducto;

public interface CategoriaProductoRepository extends JpaRepository<CategoriaProducto, Integer> {
    
}
