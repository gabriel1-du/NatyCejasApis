package com.example.InventarioApi.ServiceImpl.ProductoServiceImplFolder;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.InventarioApi.Model.ProductoModels.CategoriaProducto;
import com.example.InventarioApi.Repository.ProductoRepositories.CategoriaProductoRepository;
import com.example.InventarioApi.Service.ProductoServices.CategoriaProductoService;


@Service
public class CategoriaProductoServiceImpl implements CategoriaProductoService {

    @Autowired
    private CategoriaProductoRepository categoriaProductoRepositorio;

    @Override
    public List<CategoriaProducto> listarCategorias() {
        return categoriaProductoRepositorio.findAll();
    }

    @Override
    public Optional<CategoriaProducto> obtenerCategoriaPorId(Integer id) {
        return categoriaProductoRepositorio.findById(id);
    }

    @Override
    public CategoriaProducto guardarCategoria(CategoriaProducto categoria) {
        return categoriaProductoRepositorio.save(categoria);
    }

    @Override
    public void eliminarCategoria(Integer id) {
        categoriaProductoRepositorio.deleteById(id);
    }

}
