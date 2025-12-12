package com.example.InventarioApi.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.InventarioApi.Model.Marca;

@Repository
public interface MarcaRepository extends JpaRepository<Marca, Integer> {
    
}
