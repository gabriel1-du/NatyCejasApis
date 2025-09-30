package com.example.InventarioApi.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductoDTO {

    private Integer id_producto;
    private String nombre_producto;
    private String descripcion;
    private Integer precio;
    private Integer stock;

    // Marca
    private Integer id_marca;
    private String nombre_marca;

    // CategoriaProducto
    private Integer id_categoria;
    private String nombre_categoria;
    
}
