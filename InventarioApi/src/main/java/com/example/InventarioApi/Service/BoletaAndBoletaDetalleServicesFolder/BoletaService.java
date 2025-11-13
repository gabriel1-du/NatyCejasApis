package com.example.InventarioApi.Service.BoletaAndBoletaDetalleServicesFolder;

import java.util.List;
import java.util.Optional;

import com.example.InventarioApi.DTO.BoletaAndBoletaDetallesDtos.BoletaDTO;
import com.example.InventarioApi.Model.BoletaModelsFolder.Boleta;

public interface BoletaService {
    
    List<Boleta> listarTodas();
    Optional<Boleta> obtenerPorId(Integer id);
    Boleta crearBoleta(BoletaDTO boletaDTO);
    Boleta actualizarBoleta(Integer id, BoletaDTO boletaDTO);
    void eliminarBoleta(Integer id);
}
