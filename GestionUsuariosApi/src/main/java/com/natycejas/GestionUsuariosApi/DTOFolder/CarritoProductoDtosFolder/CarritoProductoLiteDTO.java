package com.natycejas.GestionUsuariosApi.DTOFolder.CarritoProductoDtosFolder;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarritoProductoLiteDTO {
    private Integer idProducto;
    private Integer idUsuario;
    private Integer idCarrito;
    private Integer cantidad;
}