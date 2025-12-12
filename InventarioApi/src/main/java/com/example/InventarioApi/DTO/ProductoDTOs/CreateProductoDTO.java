package com.example.InventarioApi.DTO.ProductoDTOs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateProductoDTO {

    private Integer id_producto;
    private String nombre_producto;
    private String descripcion;
    private Integer precio;
    private Integer stock;
    private Integer id_marca; //Marca
    private Integer id_categoria; //CategoriaProducto
    private String foto_url; // URL de imagen opcional

}
