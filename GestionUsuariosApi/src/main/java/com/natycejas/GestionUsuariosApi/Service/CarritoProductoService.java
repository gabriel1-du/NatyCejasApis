package com.natycejas.GestionUsuariosApi.Service;

import java.util.List;


import com.natycejas.GestionUsuariosApi.DTOFolder.CarritoProductoDtosFolder.CarritoProductoCreateDTO;
import com.natycejas.GestionUsuariosApi.DTOFolder.CarritoProductoDtosFolder.CarritoProductoDTO;
import com.natycejas.GestionUsuariosApi.DTOFolder.CarritoProductoDtosFolder.CarritoProductoLiteDTO;
import com.natycejas.GestionUsuariosApi.DTOFolder.CarritoProductoDtosFolder.CarritoProductoUpdateDTO;


public interface CarritoProductoService {

    
    // Listar todos los productos del carrito
    List<CarritoProductoDTO> listarTodos();

    // Listar productos por ID de carrito
    List<CarritoProductoLiteDTO> listarPorCarritoId(Integer idCarrito);

    // Buscar un producto del carrito por su ID
    CarritoProductoDTO buscarPorId(Integer id);

    // Crear un nuevo producto en el carrito
    CarritoProductoDTO crear(CarritoProductoCreateDTO carritoProductoCreateDTO);

    // Actualizar un producto del carrito
    CarritoProductoDTO actualizar(Integer id, CarritoProductoUpdateDTO carritoProductoUpdateDTO);

    // Eliminar un producto del carrito
    void eliminar(Integer id);

}
