package com.natycejas.GestionUsuariosApi.MapperFolder;

import com.natycejas.GestionUsuariosApi.DTOFolder.CarritoProductoDtosFolder.CarritoProductoCreateDTO;
import com.natycejas.GestionUsuariosApi.DTOFolder.CarritoProductoDtosFolder.CarritoProductoDTO;
import com.natycejas.GestionUsuariosApi.DTOFolder.CarritoProductoDtosFolder.CarritoProductoUpdateDTO;
import com.natycejas.GestionUsuariosApi.ModelFolder.Carrito;
import com.natycejas.GestionUsuariosApi.ModelFolder.CarritoProducto;
import com.natycejas.GestionUsuariosApi.RepositoryFolder.CarritoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CarritoProductoMapper {

    private final CarritoRepository carritoRepository;
    
    @Autowired
    private CarritoMapper carritoMapper;

    @Autowired
    public CarritoProductoMapper(CarritoRepository carritoRepository) {
        this.carritoRepository = carritoRepository;
    }

    public CarritoProductoDTO toDTO(CarritoProducto carritoProducto) {
        if (carritoProducto == null) {
            return null;
        }
        
        CarritoProductoDTO carritoProductoDTO = new CarritoProductoDTO();
        carritoProductoDTO.setIdCarritoProducto(carritoProducto.getIdCarritoProducto());
        carritoProductoDTO.setCarrito(carritoMapper.toDTO(carritoProducto.getCarrito()));
        carritoProductoDTO.setIdProducto(carritoProducto.getIdProducto());
        carritoProductoDTO.setCantidad(carritoProducto.getCantidad());
        
        return carritoProductoDTO;
    }

    
    public CarritoProducto toEntity(CarritoProductoCreateDTO carritoProductoCreateDTO) {
        if (carritoProductoCreateDTO == null) {
            return null;
        }
        
        CarritoProducto carritoProducto = new CarritoProducto();
        
        // Buscar el carrito por ID
        if (carritoProductoCreateDTO.getIdCarrito() != null) {
            Carrito carrito = carritoRepository.findById(carritoProductoCreateDTO.getIdCarrito())
                    .orElse(null);
            carritoProducto.setCarrito(carrito);
        }
        
        carritoProducto.setIdProducto(carritoProductoCreateDTO.getIdProducto());
        carritoProducto.setCantidad(carritoProductoCreateDTO.getCantidad());
        
        return carritoProducto;
    }
    
    public void updateEntityFromDTO(CarritoProductoUpdateDTO carritoProductoUpdateDTO, CarritoProducto carritoProducto) {
        if (carritoProductoUpdateDTO == null || carritoProducto == null) {
            return;
        }
        
        if (carritoProductoUpdateDTO.getCantidad() != null) {
            carritoProducto.setCantidad(carritoProductoUpdateDTO.getCantidad());
        }
    }
}