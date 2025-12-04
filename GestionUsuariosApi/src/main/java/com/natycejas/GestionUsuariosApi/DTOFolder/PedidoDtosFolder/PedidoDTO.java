package com.natycejas.GestionUsuariosApi.DTOFolder.PedidoDtosFolder;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

import com.natycejas.GestionUsuariosApi.DTOFolder.CarritoDtosFolder.CarritoDTO;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PedidoDTO {
    private Integer idPedido;
    private CarritoDTO carrito; //Colaboracion con carrito DTO
    private LocalDateTime fecha;
    private Integer total;
    private String estado;
    private Integer idBoleta;
}
