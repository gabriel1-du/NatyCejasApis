package com.natycejas.GestionUsuariosApi.dto.CarritoProductoDtosFolder;

import com.natycejas.GestionUsuariosApi.dto.CarritoDtosFolder.CarritoDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarritoProductoDTO {
    private Integer idCarritoProducto;
    private CarritoDTO carrito;
    private Integer idProducto;
    private Integer cantidad;
}