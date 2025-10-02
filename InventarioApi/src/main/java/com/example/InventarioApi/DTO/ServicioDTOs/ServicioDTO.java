package com.example.InventarioApi.DTO.ServicioDTOs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServicioDTO {
    
    //Atributos propios de la clase modelo 
    private Integer id_servicio;
    private String nombre_servicio;
    private String descripcion;
    private Integer precio;

    // Relación con CategoriaServicio
    private Integer id_categoria_servicio;
    private String nombre_categoria_servicio;

    
}
