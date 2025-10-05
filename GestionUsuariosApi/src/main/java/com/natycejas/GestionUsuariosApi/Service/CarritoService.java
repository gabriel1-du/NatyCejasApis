package com.natycejas.GestionUsuariosApi.Service;

import java.util.List;

import com.natycejas.GestionUsuariosApi.DTOFolder.CarritoDtosFolder.CarritoCreateDTO;
import com.natycejas.GestionUsuariosApi.DTOFolder.CarritoDtosFolder.CarritoDTO;
import com.natycejas.GestionUsuariosApi.DTOFolder.CarritoDtosFolder.CarritoUpdateDTO;


public interface CarritoService {

    List<CarritoDTO> listarTodos();
    CarritoDTO buscarPorId(Integer id);
    CarritoDTO crear(CarritoCreateDTO carritoCreateDTO);
    CarritoDTO actualizar(Integer id, CarritoUpdateDTO carritoUpdateDTO);
    void eliminar(Integer id);
}
