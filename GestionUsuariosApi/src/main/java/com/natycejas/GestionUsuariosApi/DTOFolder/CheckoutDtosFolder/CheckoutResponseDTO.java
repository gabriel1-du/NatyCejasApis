package com.natycejas.GestionUsuariosApi.DTOFolder.CheckoutDtosFolder;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CheckoutResponseDTO {
    private Integer idPedido;
    private Integer totalItems;
    private String estado;
    private String mensaje;
}