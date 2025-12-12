package com.example.InventarioApi.Repository.ServiciosRepositoriesFolder;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.InventarioApi.Model.ServiciosModelsFolder.Servicio;

public interface ServicioRepository extends JpaRepository<Servicio, Integer> {
}
