package com.example.InventarioApi.Repository.ProductoRepositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.InventarioApi.Model.ProductoModels.Producto;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer> {

}
