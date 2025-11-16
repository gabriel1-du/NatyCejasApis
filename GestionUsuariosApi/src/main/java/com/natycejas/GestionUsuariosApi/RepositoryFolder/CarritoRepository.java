package com.natycejas.GestionUsuariosApi.RepositoryFolder;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.natycejas.GestionUsuariosApi.ModelFolder.Carrito;
import java.util.Optional;

@Repository
public interface CarritoRepository extends JpaRepository<Carrito, Integer> {
    @Query("SELECT c FROM Carrito c WHERE c.id_usuario.idUsuario = :idUsuario")
    Optional<Carrito> findByUsuarioId(@Param("idUsuario") Integer idUsuario);
}