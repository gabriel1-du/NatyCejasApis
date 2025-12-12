package com.example.InventarioApi.ServiceImpl.BoletaAndBoletaDetallServiceImplFolder;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.InventarioApi.DTO.BoletaAndBoletaDetallesDtos.DetalleBoletaDTO;
import com.example.InventarioApi.DTO.BoletaAndBoletaDetallesDtos.DetalleBoletaMapper;
import com.example.InventarioApi.Model.BoletaModelsFolder.DetalleBoleta;
import com.example.InventarioApi.Repository.BoletaAndBoletaDetalleRepositoriesFolder.BoletaDetalleRepository;
import com.example.InventarioApi.Service.BoletaAndBoletaDetalleServicesFolder.BoletaDetalleService;


@Service
public class BoletaDetalleImpl implements BoletaDetalleService {

    @Autowired
    private BoletaDetalleRepository detalleBoletaRepository;

    @Autowired
    private DetalleBoletaMapper detalleBoletaMapper;

    @Override
    public List<DetalleBoleta> listarTodas() {
        return detalleBoletaRepository.findAll();
    }

    @Override
    public Optional<DetalleBoleta> obtenerPorId(Integer id) {
        return detalleBoletaRepository.findById(id);
    }

    @Override
    public DetalleBoleta crearDetalle(DetalleBoletaDTO detalleDTO) {
        DetalleBoleta detalle = detalleBoletaMapper.toEntity(detalleDTO);
        return detalleBoletaRepository.save(detalle);
    }

    @Override
    public DetalleBoleta actualizarDetalle(Integer id, DetalleBoletaDTO detalleDTO) {
        Optional<DetalleBoleta> detalleExistente = detalleBoletaRepository.findById(id);
        if (detalleExistente.isPresent()) {
            DetalleBoleta detalleActualizado = detalleBoletaMapper.toEntity(detalleDTO);
            detalleActualizado.setIdDetalleBoleta(id);
            return detalleBoletaRepository.save(detalleActualizado);
        }
        throw new RuntimeException("Detalle de boleta con ID " + id + " no encontrado");
    }

    @Override
    public void eliminarDetalle(Integer id) {
        detalleBoletaRepository.deleteById(id);
    }

}
