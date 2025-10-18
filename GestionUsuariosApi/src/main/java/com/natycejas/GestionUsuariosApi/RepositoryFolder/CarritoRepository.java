package com.natycejas.GestionUsuariosApi.RepositoryFolder;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.natycejas.GestionUsuariosApi.ModelFolder.Carrito;

@Repository
public interface CarritoRepository extends JpaRepository<Carrito, Integer> {
}