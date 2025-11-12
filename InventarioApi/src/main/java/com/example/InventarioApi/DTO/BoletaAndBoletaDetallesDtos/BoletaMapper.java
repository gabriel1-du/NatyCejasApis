package com.example.InventarioApi.DTO.BoletaAndBoletaDetallesDtos;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

import com.example.InventarioApi.Model.BoletaModelsFolder.Boleta;
import com.example.InventarioApi.Model.BoletaModelsFolder.DetalleBoleta;

public class BoletaMapper {

       public static Boleta toEntity(BoletaDTO dto) {
        Boleta boleta = new Boleta();
        boleta.setIdPedido(dto.getIdPedido());
        boleta.setIdUsuario(dto.getIdUsuario());
        boleta.setTotal(dto.getTotal());
        boleta.setFechaEmision(LocalDateTime.now());
        boleta.setEstado("EMITIDA");

        if (dto.getDetalles() != null) {
            boleta.setDetalles(dto.getDetalles().stream().map(detalleDto -> {
                DetalleBoleta detalle = new DetalleBoleta();
                detalle.setBoleta(boleta);
                detalle.setIdProducto(detalleDto.getIdProducto());
                detalle.setCantidad(detalleDto.getCantidad());
                detalle.setPrecioUnitario(detalleDto.getPrecioUnitario());
                return detalle;
            }).collect(Collectors.toList()));
        }

        return boleta;
    }

}
