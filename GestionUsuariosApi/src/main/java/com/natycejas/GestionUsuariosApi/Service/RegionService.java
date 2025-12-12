package com.natycejas.GestionUsuariosApi.Service;

import java.util.List;

import com.natycejas.GestionUsuariosApi.ModelFolder.Region;

public interface RegionService {
    Region crearRegion(Region region);
    Region obtenerRegionPorId(Integer id);
    List<Region> listarRegiones();
    Region actualizarRegion(Integer id, Region region);
    void eliminarRegion(Integer id);
}