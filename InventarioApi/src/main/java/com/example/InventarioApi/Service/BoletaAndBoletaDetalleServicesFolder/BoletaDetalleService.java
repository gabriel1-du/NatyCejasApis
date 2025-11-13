package com.example.InventarioApi.Service.BoletaAndBoletaDetalleServicesFolder;

import java.util.List;
import java.util.Optional;

import com.example.InventarioApi.DTO.BoletaAndBoletaDetallesDtos.DetalleBoletaDTO;
import com.example.InventarioApi.Model.BoletaModelsFolder.DetalleBoleta;

public interface BoletaDetalleService {
    
    List<DetalleBoleta> listarTodas();
    Optional<DetalleBoleta> obtenerPorId(Integer id);
    DetalleBoleta crearDetalle(DetalleBoletaDTO detalleDTO);
    DetalleBoleta actualizarDetalle(Integer id, DetalleBoletaDTO detalleDTO);
    void eliminarDetalle(Integer id);

}
