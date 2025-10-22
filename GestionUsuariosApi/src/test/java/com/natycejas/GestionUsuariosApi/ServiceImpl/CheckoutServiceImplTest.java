package com.natycejas.GestionUsuariosApi.ServiceImpl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import com.natycejas.GestionUsuariosApi.DTOFolder.CheckoutDtosFolder.CheckoutItemDTO;
import com.natycejas.GestionUsuariosApi.DTOFolder.CheckoutDtosFolder.CheckoutRequestDTO;
import com.natycejas.GestionUsuariosApi.DTOFolder.CheckoutDtosFolder.CheckoutResponseDTO;
import com.natycejas.GestionUsuariosApi.ExternalClientFolder.InventarioClient;
import com.natycejas.GestionUsuariosApi.ModelFolder.Carrito;
import com.natycejas.GestionUsuariosApi.ModelFolder.CarritoProducto;
import com.natycejas.GestionUsuariosApi.ModelFolder.Pedido;
import com.natycejas.GestionUsuariosApi.ModelFolder.Usuario;
import com.natycejas.GestionUsuariosApi.RepositoryFolder.CarritoProductoRepository;
import com.natycejas.GestionUsuariosApi.RepositoryFolder.CarritoRepository;
import com.natycejas.GestionUsuariosApi.RepositoryFolder.PedidoRepository;
import com.natycejas.GestionUsuariosApi.RepositoryFolder.UsuarioRepository;

@ExtendWith(MockitoExtension.class)
class CheckoutServiceImplTest {

    @InjectMocks
    private CheckoutServiceImpl checkoutService;

    @Mock private UsuarioRepository usuarioRepository;
    @Mock private CarritoRepository carritoRepository;
    @Mock private CarritoProductoRepository carritoProductoRepository;
    @Mock private PedidoRepository pedidoRepository;
    @Mock private InventarioClient inventarioClient;

    private Usuario usuario;
    private CheckoutRequestDTO request;
    private Carrito carritoPersistido;
    private Pedido pedidoPersistido;

    @BeforeEach
    void setUp() {
        usuario = new Usuario();
        usuario.setIdUsuario(1);
        usuario.setRut("11.111.111-1");

        List<CheckoutItemDTO> items = List.of(
            new CheckoutItemDTO(100, 2),
            new CheckoutItemDTO(101, 1)
        );
        request = new CheckoutRequestDTO("11.111.111-1", items);

        carritoPersistido = new Carrito();
        carritoPersistido.setIdCarrito(5);
        carritoPersistido.setId_usuario(usuario);

        pedidoPersistido = new Pedido();
        pedidoPersistido.setIdPedido(99);
        pedidoPersistido.setCarrito(carritoPersistido);
        pedidoPersistido.setTotal(3);
        pedidoPersistido.setEstado("PAGADO");
    }

    @Test
    void checkout_ok_deberiaRetornarResponseDTOYPersistir() {
        when(usuarioRepository.findByRut("11.111.111-1")).thenReturn(Optional.of(usuario));
        when(carritoRepository.save(any(Carrito.class))).thenReturn(carritoPersistido);
        doNothing().when(inventarioClient).restarStock(anyInt(), anyInt());
        when(pedidoRepository.save(any(Pedido.class))).thenReturn(pedidoPersistido);

        CheckoutResponseDTO res = checkoutService.checkout(request);

        assertNotNull(res);
        assertEquals(99, res.getIdPedido());
        assertEquals(3, res.getTotalItems());
        assertEquals("OK", res.getEstado());

        verify(usuarioRepository).findByRut("11.111.111-1");
        verify(carritoRepository).save(any(Carrito.class));
        // Dos productos distintos en items
        verify(inventarioClient, times(2)).restarStock(anyInt(), anyInt());
        verify(carritoProductoRepository, atLeast(2)).save(any(CarritoProducto.class));
        verify(pedidoRepository).save(any(Pedido.class));
    }

    @Test
    void checkout_runUsuarioObligatorio_deberiaLanzarBadRequest() {
        CheckoutRequestDTO invalid = new CheckoutRequestDTO(null, List.of(new CheckoutItemDTO(100, 1)));
        ResponseStatusException ex = assertThrows(ResponseStatusException.class, () -> checkoutService.checkout(invalid));
        assertEquals(HttpStatus.BAD_REQUEST, ex.getStatusCode());
    }

    @Test
    void checkout_itemsVacios_deberiaLanzarBadRequest() {
        CheckoutRequestDTO invalid = new CheckoutRequestDTO("11.111.111-1", List.of());
        ResponseStatusException ex = assertThrows(ResponseStatusException.class, () -> checkoutService.checkout(invalid));
        assertEquals(HttpStatus.BAD_REQUEST, ex.getStatusCode());
    }

    @Test
    void checkout_falloInventario_deberiaLanzarBadGateway() {
        when(usuarioRepository.findByRut("11.111.111-1")).thenReturn(Optional.of(usuario));
        when(carritoRepository.save(any(Carrito.class))).thenReturn(carritoPersistido);
        doThrow(new RuntimeException("API error")).when(inventarioClient).restarStock(anyInt(), anyInt());

        ResponseStatusException ex = assertThrows(ResponseStatusException.class, () -> checkoutService.checkout(request));
        assertEquals(HttpStatus.BAD_GATEWAY, ex.getStatusCode());
        verify(inventarioClient, atLeastOnce()).restarStock(anyInt(), anyInt());
    }
}