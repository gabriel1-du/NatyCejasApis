package com.natycejas.GestionUsuariosApi.Service;

import java.util.List;

import com.natycejas.GestionUsuariosApi.ModelFolder.Comuna;

public interface ComunaService {
    Comuna crearComuna(Comuna comuna);
    Comuna obtenerComunaPorId(Integer id);
    List<Comuna> listarComunas();
    Comuna actualizarComuna(Integer id, Comuna comuna);
    void eliminarComuna(Integer id);
}