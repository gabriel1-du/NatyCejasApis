package com.example.InventarioApi.ServiceImpl.CitaServiceImplFolder;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.InventarioApi.Model.Cita;
import com.example.InventarioApi.Repository.CitaRepositoriesFolder.CitaRepository;
import com.example.InventarioApi.Service.CitaServiceFolder.CitaService;

@Service
public class CitaServiceImpl implements CitaService {

    @Autowired
    private CitaRepository citaRepository;

    @Override
    public List<Cita> obtenerTodos() {
        return citaRepository.findAll();
    }

    @Override
    public Cita obtenerPorId(Integer id) {
        return citaRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Cita no encontrada con id " + id));
    }

    @Override
    public Cita crear(Cita cita) {
        if (cita.getEstado() == null || cita.getEstado().isBlank()) {
            cita.setEstado("PENDIENTE");
        }
        return citaRepository.save(cita);
    }

    @Override
    public Cita actualizar(Integer id, Cita cita) {
        Cita existente = obtenerPorId(id);
        existente.setId_usuario(cita.getId_usuario());
        existente.setId_servicio(cita.getId_servicio());
        existente.setFecha(cita.getFecha());
        existente.setHora(cita.getHora());
        existente.setEstado(cita.getEstado() == null || cita.getEstado().isBlank() ? "PENDIENTE" : cita.getEstado());
        return citaRepository.save(existente);
    }

    @Override
    public void eliminar(Integer id) {
        Cita existente = obtenerPorId(id);
        citaRepository.delete(existente);
    }
}