package com.example.InventarioApi.Repositoriy;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.InventarioApi.Model.Producto;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer> {

}
