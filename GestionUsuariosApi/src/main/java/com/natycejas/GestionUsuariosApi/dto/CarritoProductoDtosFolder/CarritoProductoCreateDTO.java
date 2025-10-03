package com.natycejas.GestionUsuariosApi.dto.CarritoProductoDtosFolder;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarritoProductoCreateDTO {
    private Integer idCarrito;
    private Integer idProducto;
    private Integer cantidad;
}