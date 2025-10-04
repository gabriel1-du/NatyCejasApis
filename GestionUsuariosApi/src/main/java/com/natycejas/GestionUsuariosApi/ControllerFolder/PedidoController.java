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

import com.natycejas.GestionUsuariosApi.DTOFolder.PedidoDtosFolder.PedidoCreateDTO;
import com.natycejas.GestionUsuariosApi.DTOFolder.PedidoDtosFolder.PedidoDTO;
import com.natycejas.GestionUsuariosApi.DTOFolder.PedidoDtosFolder.PedidoUpdateDTO;
import com.natycejas.GestionUsuariosApi.Service.PedidoService;


@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @PostMapping
    public ResponseEntity<PedidoDTO> crearPedidoController(@RequestBody PedidoCreateDTO pedidoCreateDTO) {
        return ResponseEntity.ok(pedidoService.crearPedido(pedidoCreateDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PedidoDTO> obtenerPedidoController(@PathVariable Integer id) {
        return ResponseEntity.ok(pedidoService.obtenerPedidoPorId(id));
    }

    @GetMapping
    public ResponseEntity<List<PedidoDTO>> listarPedidosController() {
        return ResponseEntity.ok(pedidoService.listarPedidos());
    }

    @PutMapping("/{id}")
    public ResponseEntity<PedidoDTO> actualizarPedidoController(
            @PathVariable Integer id,
            @RequestBody PedidoUpdateDTO pedidoUpdateDTO) {
        return ResponseEntity.ok(pedidoService.actualizarPedido(id, pedidoUpdateDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPedidoController(@PathVariable Integer id) {
        pedidoService.eliminarPedido(id);
        return ResponseEntity.noContent().build();
    }

}
