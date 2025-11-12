package com.example.InventarioApi.DTO.BoletaAndBoletaDetallesDtos;

import java.util.List;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BoletaDTO {
    
    private Integer idPedido;
    private Integer idUsuario;
    private Integer total;
    private List<DetalleBoletaDTO> detalles;
   
}
