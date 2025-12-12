package com.natycejas.GestionUsuariosApi.RepositoryFolder;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.natycejas.GestionUsuariosApi.ModelFolder.Usuario;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    Optional<Usuario> findByEmail(String email);
    Optional<Usuario> findByRut(String rut);

    @Modifying
    @Query(value = "DELETE FROM boleta WHERE id_usuario = :idUsuario", nativeQuery = true)
    void deleteBoletaByUsuarioId(@Param("idUsuario") Integer idUsuario);
}
