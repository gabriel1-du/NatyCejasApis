package com.example.InventarioApi.Repository.ProductoRepositoriesFolder;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.InventarioApi.Model.ProductoModelsFolder.Producto;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer> {

}
