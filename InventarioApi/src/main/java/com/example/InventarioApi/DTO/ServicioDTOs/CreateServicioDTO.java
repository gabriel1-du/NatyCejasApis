package com.example.InventarioApi.DTO.ServicioDTOs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateServicioDTO {
    private String nombre_servicio;
    private String descripcion;
    private String precio;
    private Integer id_categoria_service;
    
}
