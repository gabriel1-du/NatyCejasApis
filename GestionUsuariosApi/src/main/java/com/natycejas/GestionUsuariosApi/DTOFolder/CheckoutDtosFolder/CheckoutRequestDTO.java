package com.natycejas.GestionUsuariosApi.DTOFolder.CheckoutDtosFolder;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CheckoutRequestDTO {
    private String runUsuario;
    private List<CheckoutItemDTO> items;
}