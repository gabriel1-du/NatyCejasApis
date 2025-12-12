package com.natycejas.GestionUsuariosApi.ServiceImpl;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
import com.natycejas.GestionUsuariosApi.Service.CheckoutService;

@Service
public class CheckoutServiceImpl implements CheckoutService {

    private static final Logger log = LoggerFactory.getLogger(CheckoutServiceImpl.class);

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CarritoRepository carritoRepository;

    @Autowired
    private CarritoProductoRepository carritoProductoRepository;

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private InventarioClient inventarioClient;

    @Override
    @Transactional
    public CheckoutResponseDTO checkout(CheckoutRequestDTO request) {
        if (request == null || request.getRunUsuario() == null || request.getRunUsuario().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "runUsuario es obligatorio");
        }
        List<CheckoutItemDTO> items = request.getItems();
        if (items == null || items.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "items no puede estar vacío");
        }

        // Validación de cada item y consolidación opcional por idProducto
        Map<Integer, Integer> cantidadPorProducto = new HashMap<>();
        for (CheckoutItemDTO it : items) {
            if (it == null || it.getIdProducto() == null) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "idProducto no puede ser nulo");
            }
            if (it.getCantidad() == null || it.getCantidad() <= 0) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "cantidad debe ser > 0");
            }
            cantidadPorProducto.merge(it.getIdProducto(), it.getCantidad(), Integer::sum);
        }

        // Buscar usuario por RUT
        Usuario usuario = usuarioRepository.findByRut(request.getRunUsuario())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuario inexistente"));

        // Crear carrito asociado al usuario
        Carrito carrito = new Carrito();
        carrito.setId_usuario(usuario);
        carrito.setFechaCreacion(LocalDateTime.now());
        carrito.setEstado("ACTIVO");
        carrito = carritoRepository.save(carrito);

        // Restar stock en inventario por cada producto (operación crítica)
        for (Map.Entry<Integer, Integer> entry : cantidadPorProducto.entrySet()) {
            Integer idProducto = entry.getKey();
            Integer cantidad = entry.getValue();
            try {
                log.debug("[Inventario][PATCH] Restar stock producto={} cantidad={}", idProducto, cantidad);
                inventarioClient.restarStock(idProducto, cantidad);
                log.debug("[Inventario] OK producto={} cantidad={}", idProducto, cantidad);
            } catch (RuntimeException ex) {
                log.debug("[Inventario] ERROR producto={} cantidad={} msg={}", idProducto, cantidad, ex.getMessage());
                throw new ResponseStatusException(HttpStatus.BAD_GATEWAY, "Fallo inventario: " + ex.getMessage());
            }
        }

        // Persistir los items comprados ligados al carrito
        for (Map.Entry<Integer, Integer> entry : cantidadPorProducto.entrySet()) {
            CarritoProducto cp = new CarritoProducto();
            cp.setCarrito(carrito);
            cp.setIdProducto(entry.getKey());
            cp.setCantidad(entry.getValue());
            carritoProductoRepository.save(cp);
        }

        // Registrar pedido (venta) usando el carrito y totalItems como total
        int totalItems = cantidadPorProducto.values().stream().mapToInt(Integer::intValue).sum();
        Pedido pedido = new Pedido();
        pedido.setCarrito(carrito);
        pedido.setFecha(LocalDateTime.now());
        pedido.setTotal(totalItems);
        pedido.setEstado("PAGADO");
        pedido = pedidoRepository.save(pedido);

        return new CheckoutResponseDTO(pedido.getIdPedido(), totalItems, "OK", "Compra registrada");
    }
}