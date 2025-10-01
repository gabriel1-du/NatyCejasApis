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

import com.example.InventarioApi.DTO.CreateProductoDTO;
import com.example.InventarioApi.DTO.ProductoDTO;
import com.example.InventarioApi.DTO.UpdateProductoDTO;
import com.example.InventarioApi.Model.Producto;
import com.example.InventarioApi.ServiceImpl.ProductoServiceImpl;


@RestController
@RequestMapping("/inventario/productos")
public class ProductoController {


    @Autowired
    private ProductoServiceImpl productoService;

    //---Metodos crud---//

    @GetMapping //Obtener Todos
    public List<ProductoDTO> obtenerTodosLosProductosController() {
        return productoService.obtenerTodosLosProductos();
    }

    @GetMapping("/{id}")
    public ProductoDTO obtenerProductoPorIdController(@PathVariable Integer id) {
        return productoService.obtenerProductoPorId(id);
    }

    @PostMapping
    public ProductoDTO crearProducto(@RequestBody CreateProductoDTO createProductoDTO) {
        return productoService.crearProducto(createProductoDTO);
    }

    @PutMapping("/{id}") //Actulizar
    public ProductoDTO updateProducto(@PathVariable Integer id, @RequestBody UpdateProductoDTO updateDTO) {
        return productoService.actualizarProducto(id, updateDTO);
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


