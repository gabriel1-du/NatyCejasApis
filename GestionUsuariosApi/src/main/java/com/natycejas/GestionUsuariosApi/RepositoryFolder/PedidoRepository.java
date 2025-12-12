package com.natycejas.GestionUsuariosApi.RepositoryFolder;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.natycejas.GestionUsuariosApi.ModelFolder.Pedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Integer> {
    @Modifying
    void deleteByCarrito_IdCarrito(Integer idCarrito);

    @Modifying
    @Query(value = "DELETE FROM boleta WHERE id_pedido IN (SELECT id_pedido FROM pedido WHERE id_carrito = :idCarrito)", nativeQuery = true)
    void deleteBoletasByCarritoId(@Param("idCarrito") Integer idCarrito);
}
