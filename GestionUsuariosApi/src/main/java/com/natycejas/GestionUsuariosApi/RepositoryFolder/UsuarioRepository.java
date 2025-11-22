package com.natycejas.GestionUsuariosApi.RepositoryFolder;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.natycejas.GestionUsuariosApi.ModelFolder.Usuario;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    Optional<Usuario> findByEmail(String email);
    Optional<Usuario> findByRut(String rut);
}