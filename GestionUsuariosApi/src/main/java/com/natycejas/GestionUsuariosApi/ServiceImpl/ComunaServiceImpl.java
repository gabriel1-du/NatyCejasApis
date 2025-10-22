package com.natycejas.GestionUsuariosApi.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.natycejas.GestionUsuariosApi.ModelFolder.Comuna;
import com.natycejas.GestionUsuariosApi.RepositoryFolder.ComunaRepository;
import com.natycejas.GestionUsuariosApi.Service.ComunaService;

@Service
public class ComunaServiceImpl implements ComunaService {

    @Autowired
    private ComunaRepository comunaRepository;

    public ComunaServiceImpl(ComunaRepository comunaRepository) {
        this.comunaRepository = comunaRepository;
    }

    @Override
    public Comuna crearComuna(Comuna comuna) {
        return comunaRepository.save(comuna);
    }

    @Override
    public Comuna obtenerComunaPorId(Integer id) {
        return comunaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Comuna no encontrada con id " + id));
    }

    @Override
    public List<Comuna> listarComunas() {
        return comunaRepository.findAll();
    }

    @Override
    public Comuna actualizarComuna(Integer id, Comuna comuna) {
        Comuna existente = comunaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Comuna no encontrada con id " + id));
        existente.setNombreComuna(comuna.getNombreComuna());
        existente.setRegion(comuna.getRegion());
        return comunaRepository.save(existente);
    }

    @Override
    public void eliminarComuna(Integer id) {
        if (!comunaRepository.existsById(id)) {
            throw new RuntimeException("Comuna no encontrada con id " + id);
        }
        comunaRepository.deleteById(id);
    }
}