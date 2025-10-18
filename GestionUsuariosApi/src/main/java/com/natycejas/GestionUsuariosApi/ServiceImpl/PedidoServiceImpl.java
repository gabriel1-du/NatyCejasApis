package com.natycejas.GestionUsuariosApi.ServiceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.natycejas.GestionUsuariosApi.DTOFolder.PedidoDtosFolder.PedidoCreateDTO;
import com.natycejas.GestionUsuariosApi.DTOFolder.PedidoDtosFolder.PedidoDTO;
import com.natycejas.GestionUsuariosApi.DTOFolder.PedidoDtosFolder.PedidoUpdateDTO;
import com.natycejas.GestionUsuariosApi.MapperFolder.PedidoMapper;
import com.natycejas.GestionUsuariosApi.ModelFolder.Carrito;
import com.natycejas.GestionUsuariosApi.ModelFolder.Pedido;
import com.natycejas.GestionUsuariosApi.RepositoryFolder.CarritoRepository;
import com.natycejas.GestionUsuariosApi.RepositoryFolder.PedidoRepository;
import com.natycejas.GestionUsuariosApi.Service.PedidoService;

@Service
public class PedidoServiceImpl implements PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private CarritoRepository carritoRepository;

    @Autowired
    private PedidoMapper pedidoMapper;


    
    public PedidoDTO crearPedido(PedidoCreateDTO pedidoCreateDTO) {
        Carrito carrito = carritoRepository.findById(pedidoCreateDTO.getIdCarrito())
                .orElseThrow(() -> new RuntimeException("Carrito no encontrado con id " + pedidoCreateDTO.getIdCarrito()));

        Pedido pedido = pedidoMapper.toEntity(pedidoCreateDTO, carrito);
        Pedido guardado = pedidoRepository.save(pedido);
        return pedidoMapper.toDTO(guardado);
    }

    
    public PedidoDTO obtenerPedidoPorId(Integer id) {
        return pedidoRepository.findById(id)
                .map(pedidoMapper::toDTO)
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado con id " + id));
    }

    
    public List<PedidoDTO> listarPedidos() {
        return pedidoRepository.findAll()
                .stream()
                .map(pedidoMapper::toDTO)
                .collect(Collectors.toList());
    }

    
    public PedidoDTO actualizarPedido(Integer id, PedidoUpdateDTO pedidoUpdateDTO) {
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado con id " + id));

        pedidoMapper.updateEntityFromDTO(pedidoUpdateDTO, pedido);
        Pedido actualizado = pedidoRepository.save(pedido);

        return pedidoMapper.toDTO(actualizado);
    }

    
    public void eliminarPedido(Integer id) {
        if (!pedidoRepository.existsById(id)) {
            throw new RuntimeException("Pedido no encontrado con id " + id);
        }
        pedidoRepository.deleteById(id);
    }


}
