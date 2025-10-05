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

import com.natycejas.GestionUsuariosApi.DTOFolder.CarritoDtosFolder.CarritoCreateDTO;
import com.natycejas.GestionUsuariosApi.DTOFolder.CarritoDtosFolder.CarritoDTO;
import com.natycejas.GestionUsuariosApi.DTOFolder.CarritoDtosFolder.CarritoUpdateDTO;
import com.natycejas.GestionUsuariosApi.Service.CarritoService;

@RestController
@RequestMapping("/api/carritos")
public class CarritoController {

     @Autowired
    private CarritoService carritoService;

    @GetMapping
    public ResponseEntity<List<CarritoDTO>> listarTodos() {
        return ResponseEntity.ok(carritoService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarritoDTO> obtenerPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(carritoService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<CarritoDTO> crear(@RequestBody CarritoCreateDTO carritoCreateDTO) {
        CarritoDTO nuevoCarrito = carritoService.crear(carritoCreateDTO);
        return ResponseEntity.ok(nuevoCarrito);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CarritoDTO> actualizar(@PathVariable Integer id, @RequestBody CarritoUpdateDTO carritoUpdateDTO) {
        CarritoDTO carritoActualizado = carritoService.actualizar(id, carritoUpdateDTO);
        return ResponseEntity.ok(carritoActualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        carritoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

}
