package com.natycejas.GestionUsuariosApi.ServiceImpl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.natycejas.GestionUsuariosApi.DTOFolder.PedidoDtosFolder.PedidoCreateDTO;
import com.natycejas.GestionUsuariosApi.DTOFolder.PedidoDtosFolder.PedidoDTO;
import com.natycejas.GestionUsuariosApi.DTOFolder.PedidoDtosFolder.PedidoUpdateDTO;
import com.natycejas.GestionUsuariosApi.MapperFolder.PedidoMapper;
import com.natycejas.GestionUsuariosApi.ModelFolder.Carrito;
import com.natycejas.GestionUsuariosApi.ModelFolder.Pedido;
import com.natycejas.GestionUsuariosApi.RepositoryFolder.CarritoRepository;
import com.natycejas.GestionUsuariosApi.RepositoryFolder.PedidoRepository;

@ExtendWith(MockitoExtension.class)
class PedidoServiceImplTest {

    @InjectMocks
    private PedidoServiceImpl pedidoService;

    @Mock
    private PedidoRepository pedidoRepository;

    @Mock
    private CarritoRepository carritoRepository;

    @Mock
    private PedidoMapper pedidoMapper;

    private PedidoCreateDTO createDTO;
    private Carrito carrito;
    private Pedido pedidoEntity;
    private Pedido pedidoGuardado;
    private PedidoDTO pedidoDTO;

    @BeforeEach
    void setUp() {
        createDTO = new PedidoCreateDTO(5, 3, null);
        carrito = new Carrito();
        carrito.setIdCarrito(5);
        pedidoEntity = new Pedido();
        pedidoGuardado = new Pedido();
        pedidoGuardado.setIdPedido(99);
        pedidoGuardado.setCarrito(carrito);
        pedidoGuardado.setTotal(3);
        pedidoDTO = new PedidoDTO(99, null, pedidoGuardado.getFecha(), 3, "PAGADO", null);
    }

    @Test
    void crearPedido_deberiaPersistirYRetornarDTO() {
        when(carritoRepository.findById(5)).thenReturn(Optional.of(carrito));
        when(pedidoMapper.toEntity(createDTO, carrito)).thenReturn(pedidoEntity);
        when(pedidoRepository.save(pedidoEntity)).thenReturn(pedidoGuardado);
        when(pedidoMapper.toDTO(pedidoGuardado)).thenReturn(pedidoDTO);

        PedidoDTO result = pedidoService.crearPedido(createDTO);
        assertNotNull(result);
        assertEquals(99, result.getIdPedido());

        verify(carritoRepository).findById(5);
        verify(pedidoMapper).toEntity(createDTO, carrito);
        verify(pedidoRepository).save(pedidoEntity);
        verify(pedidoMapper).toDTO(pedidoGuardado);
    }

    @Test
    void actualizarPedido_deberiaActualizarYRetornarDTO() {
        Integer id = 99;
        PedidoUpdateDTO updateDTO = new PedidoUpdateDTO("ENVIADO", 123);
        
        when(pedidoRepository.findById(id)).thenReturn(Optional.of(pedidoGuardado));
        when(pedidoRepository.save(pedidoGuardado)).thenReturn(pedidoGuardado);
        when(pedidoMapper.toDTO(pedidoGuardado)).thenReturn(pedidoDTO);
        
        PedidoDTO result = pedidoService.actualizarPedido(id, updateDTO);
        
        assertNotNull(result);
        verify(pedidoRepository).findById(id);
        verify(pedidoMapper).updateEntityFromDTO(updateDTO, pedidoGuardado);
        verify(pedidoRepository).save(pedidoGuardado);
    }
}
