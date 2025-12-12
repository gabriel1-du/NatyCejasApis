package com.natycejas.GestionUsuariosApi.MapperFolder;

import com.natycejas.GestionUsuariosApi.DTOFolder.CarritoDtosFolder.CarritoCreateDTO;
import com.natycejas.GestionUsuariosApi.DTOFolder.CarritoDtosFolder.CarritoDTO;
import com.natycejas.GestionUsuariosApi.DTOFolder.CarritoDtosFolder.CarritoUpdateDTO;
import com.natycejas.GestionUsuariosApi.ModelFolder.Carrito;
import com.natycejas.GestionUsuariosApi.ModelFolder.Usuario;
import com.natycejas.GestionUsuariosApi.RepositoryFolder.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class CarritoMapper {

    @Autowired
    private UsuarioMapper usuarioMapper;
    
    @Autowired
    private UsuarioRepository usuarioRepository;

    public CarritoDTO toDTO(Carrito carrito) {
        if (carrito == null) {
            return null;
        }
        
        CarritoDTO carritoDTO = new CarritoDTO();
        carritoDTO.setIdCarrito(carrito.getIdCarrito());
        carritoDTO.setUsuario(usuarioMapper.toDTO(carrito.getId_usuario()));
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
            carrito.setId_usuario(usuario);
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