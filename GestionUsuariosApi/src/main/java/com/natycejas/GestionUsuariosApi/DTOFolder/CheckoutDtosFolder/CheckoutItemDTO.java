package com.natycejas.GestionUsuariosApi.DTOFolder.CheckoutDtosFolder;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CheckoutItemDTO {
    private Integer idProducto;
    private Integer cantidad;
}