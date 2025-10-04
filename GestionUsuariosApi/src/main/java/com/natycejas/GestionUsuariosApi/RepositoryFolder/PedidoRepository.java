package com.natycejas.GestionUsuariosApi.RepositoryFolder;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.natycejas.GestionUsuariosApi.ModelFolder.Pedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Integer> {
}