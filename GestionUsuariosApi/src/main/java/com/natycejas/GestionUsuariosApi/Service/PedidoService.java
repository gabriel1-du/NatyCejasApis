package com.natycejas.GestionUsuariosApi.Service;

import java.util.List;

import com.natycejas.GestionUsuariosApi.DTOFolder.PedidoDtosFolder.PedidoCreateDTO;
import com.natycejas.GestionUsuariosApi.DTOFolder.PedidoDtosFolder.PedidoDTO;
import com.natycejas.GestionUsuariosApi.DTOFolder.PedidoDtosFolder.PedidoUpdateDTO;

public interface PedidoService {

    PedidoDTO crearPedido(PedidoCreateDTO pedidoCreateDTO);
    PedidoDTO obtenerPedidoPorId(Integer id);
    List<PedidoDTO> listarPedidos();
    PedidoDTO actualizarPedido(Integer id, PedidoUpdateDTO pedidoUpdateDTO);
    void eliminarPedido(Integer id);

}
