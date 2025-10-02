package com.example.InventarioApi.Model.ServiciosModels;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "categoria_servicio")
@AllArgsConstructor
public class CategoriaServicio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_categoria_servicio;

    @Column(name = "nombre_categoria_servicio", nullable = false, length = 20)
    private String nombre_categoria_servicio;

}
