package com.example.InventarioApi.ServiceImpl.BoletaAndBoletaDetallServiceImplFolder;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.InventarioApi.DTO.BoletaAndBoletaDetallesDtos.BoletaDTO;
import com.example.InventarioApi.DTO.BoletaAndBoletaDetallesDtos.BoletaMapper;
import com.example.InventarioApi.Model.BoletaModelsFolder.Boleta;
import com.example.InventarioApi.Repository.BoletaAndBoletaDetalleRepositoriesFolder.BoletaRepository;
import com.example.InventarioApi.Service.BoletaAndBoletaDetalleServicesFolder.BoletaService;

@Service
public class BoletaServiceImpl implements BoletaService {

    @Autowired
    private BoletaRepository boletaRepository;

    @Autowired
    private BoletaMapper boletaMapper;

    @Override
    public List<Boleta> listarTodas() {
        return boletaRepository.findAll();
    }

    @Override
    public Optional<Boleta> obtenerPorId(Integer id) {
        return boletaRepository.findById(id);
    }

    @Override
    public Boleta crearBoleta(BoletaDTO boletaDTO) {
        if (boletaDTO.getIdPedido() == null || boletaDTO.getIdUsuario() == null || boletaDTO.getTotal() == null) {
            throw new IllegalArgumentException("idPedido, idUsuario y total son obligatorios");
        }
        Boleta boleta = boletaMapper.toEntity(boletaDTO);
        return boletaRepository.save(boleta);
    }

    @Override
    public Boleta actualizarBoleta(Integer id, BoletaDTO boletaDTO) {
        Optional<Boleta> boletaExistente = boletaRepository.findById(id);
        if (boletaExistente.isPresent()) {
            if (boletaDTO.getIdPedido() == null || boletaDTO.getIdUsuario() == null || boletaDTO.getTotal() == null) {
                throw new IllegalArgumentException("idPedido, idUsuario y total son obligatorios");
            }
            Boleta boletaActualizada = boletaMapper.toEntity(boletaDTO);
            boletaActualizada.setIdBoleta(id);
            return boletaRepository.save(boletaActualizada);
        }
        throw new RuntimeException("Boleta con ID " + id + " no encontrada");
    }

    @Override
    public void eliminarBoleta(Integer id) {
        boletaRepository.deleteById(id);
    }



}
