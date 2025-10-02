package com.example.InventarioApi.Repository.ServiciosRepositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.InventarioApi.Model.ServiciosModels.CategoriaServicio;

public interface CategoriaServicioRepository extends JpaRepository<CategoriaServicio, Integer> {
}
