package com.natycejas.GestionUsuariosApi.repository;

import com.natycejas.GestionUsuariosApi.model.CarritoProducto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarritoProductoRepository extends JpaRepository<CarritoProducto, Integer> {
}