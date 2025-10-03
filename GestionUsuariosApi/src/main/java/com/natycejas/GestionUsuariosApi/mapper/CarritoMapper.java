package com.natycejas.GestionUsuariosApi.mapper;

import com.natycejas.GestionUsuariosApi.dto.CarritoDtosFolder.CarritoCreateDTO;
import com.natycejas.GestionUsuariosApi.dto.CarritoDtosFolder.CarritoDTO;
import com.natycejas.GestionUsuariosApi.dto.CarritoDtosFolder.CarritoUpdateDTO;
import com.natycejas.GestionUsuariosApi.model.Carrito;
import com.natycejas.GestionUsuariosApi.model.Usuario;
import com.natycejas.GestionUsuariosApi.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class CarritoMapper {

    private final UsuarioRepository usuarioRepository;

    @Autowired
    public CarritoMapper(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public CarritoDTO toDTO(Carrito carrito) {
        if (carrito == null) {
            return null;
        }
        
        CarritoDTO carritoDTO = new CarritoDTO();
        carritoDTO.setIdCarrito(carrito.getIdCarrito());
        carritoDTO.setUsuario(carrito.getUsuario().getIdUsuario());
        carritoDTO.setFechaCreacion(carrito.getFechaCreacion());
        carritoDTO.setEstado(carrito.getEstado());
        
        return carritoDTO;
    }
    
    public Carrito toEntity(CarritoCreateDTO carritoCreateDTO) {
        if (carritoCreateDTO == null) {
            return null;
        }
        
        Carrito carrito = new Carrito();
        
        // Buscar el usuario por ID
        if (carritoCreateDTO.getIdUsuario() != null) {
            Usuario usuario = usuarioRepository.findById(carritoCreateDTO.getIdUsuario())
                    .orElse(null);
            carrito.setUsuario(usuario);
        }
        
        // Establecer fecha de creación y estado por defecto
        carrito.setFechaCreacion(LocalDateTime.now());
        carrito.setEstado("Activo");
        
        return carrito;
    }
    
    public void updateEntityFromDTO(CarritoUpdateDTO carritoUpdateDTO, Carrito carrito) {
        if (carritoUpdateDTO == null || carrito == null) {
            return;
        }
        
        if (carritoUpdateDTO.getEstado() != null) {
            carrito.setEstado(carritoUpdateDTO.getEstado());
        }
    }
}