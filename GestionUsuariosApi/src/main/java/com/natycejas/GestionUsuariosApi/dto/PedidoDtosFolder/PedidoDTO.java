package com.natycejas.GestionUsuariosApi.dto.PedidoDtosFolder;

import com.natycejas.GestionUsuariosApi.dto.CarritoDtosFolder.CarritoDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PedidoDTO {
    private Integer idPedido;
    private CarritoDTO carrito;
    private LocalDateTime fecha;
    private Integer total;
    private String estado;
}