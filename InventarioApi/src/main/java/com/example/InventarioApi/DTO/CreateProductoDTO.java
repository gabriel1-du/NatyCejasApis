package com.example.InventarioApi.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreateProductoDTO {

    private Integer id_producto;
    private String nombre_producto;
    private String descripcion;
    private Integer precio;
    private Integer stock;
    private Integer id_marca; //Marca
    private Integer id_categoria; //CategoriaProducto

}
