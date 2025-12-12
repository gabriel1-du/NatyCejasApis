package com.example.InventarioApi.DTO.ServicioDTOs;

import com.example.InventarioApi.Model.ServiciosModelsFolder.Servicio;

public class ServicioMapper {

    public static ServicioDTO toDTO(Servicio servicio) {
        ServicioDTO dto = new ServicioDTO();
        dto.setId_servicio(servicio.getId_servicio());
        dto.setNombre_servicio(servicio.getNombre_servicio());
        dto.setDescripcion(servicio.getDescripcion());
        dto.setPrecio(servicio.getPrecio());

        if (servicio.getCategoriaServicio() != null) {
            dto.setId_categoria_servicio(servicio.getCategoriaServicio().getId_categoria_servicio());
            dto.setNombre_categoria_servicio(servicio.getCategoriaServicio().getNombre_categoria_servicio());
        }

        return dto;
    }
}
