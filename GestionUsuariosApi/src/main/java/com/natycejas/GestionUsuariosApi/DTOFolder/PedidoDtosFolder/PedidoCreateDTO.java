package com.natycejas.GestionUsuariosApi.DTOFolder.PedidoDtosFolder;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PedidoCreateDTO {
    private Integer idCarrito;
    private Integer total;
}