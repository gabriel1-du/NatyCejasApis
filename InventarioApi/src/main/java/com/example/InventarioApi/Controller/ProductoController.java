package com.example.InventarioApi.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.InventarioApi.Model.Producto;
import com.example.InventarioApi.ServiceImpl.ProductoServiceImpl;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/inventario/productos")
public class ProductoController {


    @Autowired
    private ProductoServiceImpl productoService;

    //---Metodos crud---//

    @GetMapping //Obtener Todos
    public List<Producto> listarProductos() {
        return productoService.listarProductos();
    }

    @GetMapping("/{id}") //Ovtener Por ID
    public ResponseEntity<Producto> obtenerProducto(@PathVariable Integer id) {
        return productoService.obtenerProductoPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping //Crear
    public Producto crearProducto(@RequestBody Producto producto) {
        return productoService.guardarProducto(producto);
    }

    @PutMapping("/{id}") //Actulizar
    public ResponseEntity<Producto> actualizarProducto(@PathVariable Integer id, @RequestBody Producto producto) {
        return productoService.obtenerProductoPorId(id)
                .map(p -> {
                    producto.setId_producto(id);
                    return ResponseEntity.ok(productoService.guardarProducto(producto));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @PatchMapping("/{id}/sumar")
    public Producto sumarStock(@PathVariable Integer id, @RequestParam Integer cantidad) {
        return productoService.sumarStock(id, cantidad);
    }

    @PatchMapping("/{id}/restar")
    public Producto restarStock(@PathVariable Integer id, @RequestParam Integer cantidad) {
        return productoService.restarStock(id, cantidad);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable Integer id) {
        productoService.eliminarProducto(id);
        return ResponseEntity.noContent().build();
    }
}


