package com.example.InventarioApi.Service.ServicioService;

import java.util.List;

import com.example.InventarioApi.DTO.ServicioDTOs.CreateServicioDTO;
import com.example.InventarioApi.DTO.ServicioDTOs.ServicioDTO;
import com.example.InventarioApi.DTO.ServicioDTOs.UpdateServicioDTO;

public interface ServicioService {

    ServicioDTO crearServicio(CreateServicioDTO dto);
    ServicioDTO actualizarServicio(Integer id, UpdateServicioDTO dto);
    ServicioDTO obtenerServicioPorId(Integer id);
    List<ServicioDTO> listarServicios();
    void eliminarServicio(Integer id);

}
