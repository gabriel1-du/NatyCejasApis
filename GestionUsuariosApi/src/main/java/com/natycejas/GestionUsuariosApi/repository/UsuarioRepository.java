package com.natycejas.GestionUsuariosApi.repository;

import com.natycejas.GestionUsuariosApi.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
}