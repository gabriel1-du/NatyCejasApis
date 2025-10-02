package com.example.InventarioApi.DTO.ProductoDTOs;

import com.example.InventarioApi.Model.Marca;
import com.example.InventarioApi.Model.ProductoModelsFolder.CategoriaProducto;
import com.example.InventarioApi.Model.ProductoModelsFolder.Producto;

public class MapperProducto {

    // Convertir CreateProductoDTO -> Producto (para guardar)
    public static Producto toEntity(CreateProductoDTO dto) {
        Producto producto = new Producto();
        producto.setNombre_producto(dto.getNombre_producto());
        producto.setDescripcion(dto.getDescripcion());
        producto.setPrecio(dto.getPrecio());
        producto.setStock(dto.getStock());

        if (dto.getId_marca() != null) {
            Marca marca = new Marca();
            marca.setId_marca(dto.getId_marca());
            producto.setMarca(marca);
        }

        if (dto.getId_categoria() != null) {
            CategoriaProducto categoria = new CategoriaProducto();
            categoria.setId_categoria_producto(categoria.getId_categoria_producto());
            producto.setCategoria(categoria);
        }

        return producto;
    }

    // Convertir Producto -> ProductoDTO (para mostrar)
    public static ProductoDTO toDTO(Producto producto) {
        ProductoDTO dto = new ProductoDTO();
        dto.setId_producto(producto.getId_producto());
        dto.setNombre_producto(producto.getNombre_producto());
        dto.setDescripcion(producto.getDescripcion());
        dto.setPrecio(producto.getPrecio());
        dto.setStock(producto.getStock());

        if (producto.getMarca() != null) {
            dto.setId_marca(producto.getMarca().getId_marca());
            dto.setNombre_marca(producto.getMarca().getNombre_marca());
        }

        if (producto.getCategoria() != null) {
            dto.setId_categoria(producto.getCategoria().getId_categoria_producto());
            dto.setNombre_categoria(producto.getCategoria().getNombre_categoria_producto());
        }

        return dto;
    }
}
