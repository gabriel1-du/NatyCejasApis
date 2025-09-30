package com.example.InventarioApi.Controller;

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

import com.example.InventarioApi.Model.Marca;
import com.example.InventarioApi.ServiceImpl.MarcaServiceImpl;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/inventario/marcas")
@RequiredArgsConstructor
public class MarcaContoller {

    @Autowired
    MarcaServiceImpl marcaService;

    @GetMapping //Buscar todo
    public List<Marca> listarMarcas() {
        return marcaService.listarMarcas();
    }

    @GetMapping("/{id}") //Buscar por ID
    public ResponseEntity<Marca> obtenerMarca(@PathVariable Integer id) {
        return marcaService.obtenerMarcaPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping //Crear
    public Marca crearMarca(@RequestBody Marca marca) {
        return marcaService.guardarMarca(marca);
    }

    @DeleteMapping("/{id}") //Buscar por ID
    public ResponseEntity<Void> eliminarMarca(@PathVariable Integer id) {
        marcaService.eliminarMarca(id);
        return ResponseEntity.noContent().build();
    }
}
