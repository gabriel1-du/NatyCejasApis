package com.natycejas.GestionUsuariosApi.RepositoryFolder;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.natycejas.GestionUsuariosApi.ModelFolder.Comuna;

@Repository
public interface ComunaRepository extends JpaRepository<Comuna, Integer> {
}