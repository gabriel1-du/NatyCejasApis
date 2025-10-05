package com.natycejas.GestionUsuariosApi.ControllerFolder;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.natycejas.GestionUsuariosApi.DTOFolder.CarritoProductoDtosFolder.CarritoProductoCreateDTO;
import com.natycejas.GestionUsuariosApi.DTOFolder.CarritoProductoDtosFolder.CarritoProductoDTO;
import com.natycejas.GestionUsuariosApi.DTOFolder.CarritoProductoDtosFolder.CarritoProductoUpdateDTO;
import com.natycejas.GestionUsuariosApi.Service.CarritoProductoService;

@RestController
@RequestMapping("/api/carrito-producto")
public class CarritoProductoController {

     @Autowired
    private CarritoProductoService carritoProductoService;

    // Listar todos los productos del carrito
    @GetMapping
    public ResponseEntity<List<CarritoProductoDTO>> listarTodosController() {
        return ResponseEntity.ok(carritoProductoService.listarTodos());
    }

    // Buscar producto del carrito por ID
    @GetMapping("/{id}")
    public ResponseEntity<CarritoProductoDTO> buscarPorIdController(@PathVariable Integer id) {
        CarritoProductoDTO dto = carritoProductoService.buscarPorId(id);
        return dto != null ? ResponseEntity.ok(dto) : ResponseEntity.notFound().build();
    }

    // Crear producto dentro del carrito
    @PostMapping
    public ResponseEntity<CarritoProductoDTO> crearController(@RequestBody CarritoProductoCreateDTO carritoProductoCreateDTO) {
        CarritoProductoDTO dto = carritoProductoService.crear(carritoProductoCreateDTO);
        return ResponseEntity.ok(dto);
    }

    // Actualizar producto del carrito
    @PutMapping("/{id}")
    public ResponseEntity<CarritoProductoDTO> actualizarController(@PathVariable Integer id, @RequestBody CarritoProductoUpdateDTO carritoProductoUpdateDTO) {
        CarritoProductoDTO dto = carritoProductoService.actualizar(id, carritoProductoUpdateDTO);
        return dto != null ? ResponseEntity.ok(dto) : ResponseEntity.notFound().build();
    }

    // Eliminar producto del carrito
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarController(@PathVariable Integer id) {
        carritoProductoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

}
