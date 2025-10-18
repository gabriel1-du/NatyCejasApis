package com.example.InventarioApi.ServiceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.InventarioApi.Model.Marca;
import com.example.InventarioApi.Repository.MarcaRepository;
import com.example.InventarioApi.Service.MarcaService;

@Service
public class MarcaServiceImpl implements MarcaService{

    @Autowired
    private MarcaRepository marcaRepository;

    public List<Marca> listarMarcas() {
        return marcaRepository.findAll();
    }

    public Marca guardarMarca(Marca marca) {
        return marcaRepository.save(marca);
    }

    public Optional<Marca> obtenerMarcaPorId(Integer id) {
        return marcaRepository.findById(id);
    }

    public void eliminarMarca(Integer id) {
        marcaRepository.deleteById(id);
    }

    
}
