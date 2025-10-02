package com.example.InventarioApi.Controller.ProductoControllerFolder;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.InventarioApi.Model.ProductoModelsFolder.CategoriaProducto;
import com.example.InventarioApi.Service.ProductoServicesFolder.CategoriaProductoService;

@RestController
@RequestMapping("/inventario/categorias")
public class CategoriaProductoController {

    @Autowired
    private CategoriaProductoService categoriaServicio;

    // Listar todas las categorías
    @GetMapping
    public List<CategoriaProducto> listarCategorias() {
        return categoriaServicio.listarCategorias();
    }

    // Obtener una categoría por ID
    @GetMapping("/{id}")
    public ResponseEntity<CategoriaProducto> obtenerCategoria(@PathVariable Integer id) {
        return categoriaServicio.obtenerCategoriaPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Crear una nueva categoría
    @PostMapping
    public CategoriaProducto crearCategoria(@RequestBody CategoriaProducto categoria) {
        return categoriaServicio.guardarCategoria(categoria);
    }

    // Eliminar una categoría por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCategoria(@PathVariable Integer id) {
        categoriaServicio.eliminarCategoria(id);
        return ResponseEntity.noContent().build();
    }
    
}
