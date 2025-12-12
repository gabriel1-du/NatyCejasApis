package com.example.InventarioApi.Repository.BoletaAndBoletaDetalleRepositoriesFolder;

import org.springframework.data.jpa.repository.JpaRepository;


import com.example.InventarioApi.Model.BoletaModelsFolder.DetalleBoleta;

public interface BoletaDetalleRepository extends JpaRepository<DetalleBoleta, Integer> {

}
