package com.natycejas.GestionUsuariosApi.RepositoryFolder;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Modifying;

import com.natycejas.GestionUsuariosApi.ModelFolder.CarritoProducto;
import java.util.List;

@Repository
public interface CarritoProductoRepository extends JpaRepository<CarritoProducto, Integer> {
    List<CarritoProducto> findByCarrito_IdCarrito(Integer idCarrito);

    @Modifying
    void deleteByCarrito_IdCarrito(Integer idCarrito);
}
