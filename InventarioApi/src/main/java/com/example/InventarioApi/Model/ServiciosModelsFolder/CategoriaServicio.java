package com.example.InventarioApi.Model.ServiciosModelsFolder;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "categoria_servicio")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CategoriaServicio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_categoria_servicio;

    @Column(name = "nombre_categoria_servicio", nullable = false, length = 20)
    private String nombre_categoria_servicio;

}
