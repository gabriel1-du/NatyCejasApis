package com.example.InventarioApi.Service;

import java.util.List;
import java.util.Optional;

import com.example.InventarioApi.Model.Marca;

public interface MarcaService  {

    List<Marca> listarMarcas();

    Optional<Marca> obtenerMarcaPorId(Integer id);

    Marca guardarMarca(Marca marca);

    void eliminarMarca(Integer id);


}
