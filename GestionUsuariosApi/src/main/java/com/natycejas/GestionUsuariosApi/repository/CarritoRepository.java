package com.natycejas.GestionUsuariosApi.repository;

import com.natycejas.GestionUsuariosApi.model.Carrito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarritoRepository extends JpaRepository<Carrito, Integer> {
}