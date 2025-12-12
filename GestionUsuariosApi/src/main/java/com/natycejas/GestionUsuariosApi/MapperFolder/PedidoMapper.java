package com.natycejas.GestionUsuariosApi.MapperFolder;

import org.springframework.stereotype.Component;

import com.natycejas.GestionUsuariosApi.DTOFolder.PedidoDtosFolder.PedidoCreateDTO;
import com.natycejas.GestionUsuariosApi.DTOFolder.PedidoDtosFolder.PedidoDTO;
import com.natycejas.GestionUsuariosApi.DTOFolder.PedidoDtosFolder.PedidoUpdateDTO;
import com.natycejas.GestionUsuariosApi.ModelFolder.Carrito;
import com.natycejas.GestionUsuariosApi.ModelFolder.Pedido;

@Component
public class PedidoMapper {

    private final CarritoMapper carritoMapper;

    public PedidoMapper(CarritoMapper carritoMapper) {
        this.carritoMapper = carritoMapper;
    }

    public PedidoDTO toDTO(Pedido pedido) {
        if (pedido == null) {
            return null;
        }
        return new PedidoDTO(
                pedido.getIdPedido(),
                carritoMapper.toDTO(pedido.getCarrito()), // Convierte carrito a DTO
                pedido.getFecha(),
                pedido.getTotal(),
                pedido.getEstado()
        );
    }

    public Pedido toEntity(PedidoCreateDTO pedidoCreateDTO, Carrito carrito) {
        if (pedidoCreateDTO == null) {
            return null;
        }
        Pedido pedido = new Pedido();
        pedido.setCarrito(carrito);
        pedido.setTotal(pedidoCreateDTO.getTotal());
        pedido.setEstado("PAGADO");
        pedido.setFecha(java.time.LocalDateTime.now());
        return pedido;
    }

    public void updateEntityFromDTO(PedidoUpdateDTO pedidoUpdateDTO, Pedido pedido) {
        if (pedidoUpdateDTO == null || pedido == null) {
            return;
        }
        if (pedidoUpdateDTO.getEstado() != null) {
            pedido.setEstado(pedidoUpdateDTO.getEstado());
        }
        if (pedidoUpdateDTO.getIdBoleta() != null) {
            pedido.setIdBoleta(pedidoUpdateDTO.getIdBoleta());
        }
    }
}