package com.example.InventarioApi.Repository.ServiciosRepositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.InventarioApi.Model.ServiciosModels.Servicio;

public interface ServicioRepository extends JpaRepository<Servicio, Integer> {
}
