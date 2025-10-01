package com.example.InventarioApi.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateProductoDTO {
    private Integer id_producto;   // id a actualizar
    private String nombre_producto;
    private String descripcion;
    private Integer precio;
    private Integer stock;

}
