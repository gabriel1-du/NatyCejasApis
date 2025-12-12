package com.natycejas.GestionUsuariosApi.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.natycejas.GestionUsuariosApi.ModelFolder.Region;
import com.natycejas.GestionUsuariosApi.RepositoryFolder.RegionRepository;
import com.natycejas.GestionUsuariosApi.Service.RegionService;

@Service
public class RegionServiceImpl implements RegionService {

    @Autowired
    private RegionRepository regionRepository;

    public RegionServiceImpl(RegionRepository regionRepository) {
        this.regionRepository = regionRepository;
    }

    @Override
    public Region crearRegion(Region region) {
        return regionRepository.save(region);
    }

    @Override
    public Region obtenerRegionPorId(Integer id) {
        return regionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Región no encontrada con id " + id));
    }

    @Override
    public List<Region> listarRegiones() {
        return regionRepository.findAll();
    }

    @Override
    public Region actualizarRegion(Integer id, Region region) {
        Region existente = regionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Región no encontrada con id " + id));
        existente.setNombreRegion(region.getNombreRegion());
        return regionRepository.save(existente);
    }

    @Override
    public void eliminarRegion(Integer id) {
        if (!regionRepository.existsById(id)) {
            throw new RuntimeException("Región no encontrada con id " + id);
        }
        regionRepository.deleteById(id);
    }
}