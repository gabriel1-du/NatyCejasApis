package com.example.InventarioApi.DTO.BoletaAndBoletaDetallesDtos;

import org.springframework.stereotype.Component;

import com.example.InventarioApi.Model.BoletaModelsFolder.Boleta;
import com.example.InventarioApi.Model.BoletaModelsFolder.DetalleBoleta;
import com.example.InventarioApi.Model.ProductoModelsFolder.Producto;

@Component
public class DetalleBoletaMapper {

    
    public DetalleBoleta toEntity(DetalleBoletaDTO dto) {
        DetalleBoleta detalle = new DetalleBoleta();

        Boleta boleta = new Boleta();
        boleta.setIdBoleta(dto.getIdBoleta());
        detalle.setBoleta(boleta);

        Producto producto = new Producto();
        producto.setId_producto(dto.getIdProducto());

        detalle.setIdProducto(dto.getIdProducto());
        detalle.setCantidad(dto.getCantidad());
        detalle.setPrecioUnitario(dto.getPrecioUnitario());

        return detalle;
    }

}
