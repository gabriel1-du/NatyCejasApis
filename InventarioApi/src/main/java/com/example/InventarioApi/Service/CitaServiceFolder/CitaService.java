package com.example.InventarioApi.Service.CitaServiceFolder;

import java.util.List;

import com.example.InventarioApi.Model.Cita;

public interface CitaService {
    List<Cita> obtenerTodos();
    Cita obtenerPorId(Integer id);
    Cita crear(Cita cita);
    Cita actualizar(Integer id, Cita cita);
    void eliminar(Integer id);
}