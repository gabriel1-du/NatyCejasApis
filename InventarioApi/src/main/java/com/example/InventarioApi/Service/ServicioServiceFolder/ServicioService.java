package com.example.InventarioApi.Service.ServicioServiceFolder;

import java.util.List;

import com.example.InventarioApi.DTO.ServicioDTOs.CreateServicioDTO;
import com.example.InventarioApi.DTO.ServicioDTOs.ServicioDTO;
import com.example.InventarioApi.DTO.ServicioDTOs.UpdateServicioDTO;

public interface ServicioService {

    List<ServicioDTO> obtenerTodos();
    ServicioDTO obtenerPorId(Integer id);
    ServicioDTO guardar(CreateServicioDTO dto);
    ServicioDTO actualizar(Integer id, UpdateServicioDTO dto);
    void eliminar(Integer id);


}
