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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/pedidos")
@Tag(name = "Pedido", description = "API para la gestión de pedidos. Permite crear, consultar, actualizar y eliminar pedidos de los usuarios.")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @Operation(
        summary = "Crear un nuevo pedido",
        description = "Crea un nuevo pedido en el sistema con la información proporcionada. " +
                     "El pedido se asocia a un usuario específico y puede incluir múltiples productos."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Pedido creado exitosamente",
                    content = @Content(mediaType = "application/json", 
                                     schema = @Schema(implementation = PedidoDTO.class))),
        @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos",
                    content = @Content),
        @ApiResponse(responseCode = "404", description = "Usuario no encontrado",
                    content = @Content)
    })
    @PostMapping
    public ResponseEntity<PedidoDTO> crearPedidoController(
            @Parameter(description = "Datos del pedido a crear", required = true)
            @RequestBody PedidoCreateDTO pedidoCreateDTO) {
        return ResponseEntity.ok(pedidoService.crearPedido(pedidoCreateDTO));
    }

    @Operation(
        summary = "Obtener pedido por ID",
        description = "Recupera la información completa de un pedido específico mediante su ID único."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Pedido encontrado exitosamente",
                    content = @Content(mediaType = "application/json", 
                                     schema = @Schema(implementation = PedidoDTO.class))),
        @ApiResponse(responseCode = "404", description = "Pedido no encontrado",
                    content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<PedidoDTO> obtenerPedidoController(
            @Parameter(description = "ID único del pedido", required = true, example = "1")
            @PathVariable Integer id) {
        return ResponseEntity.ok(pedidoService.obtenerPedidoPorId(id));
    }

    @Operation(
        summary = "Listar todos los pedidos",
        description = "Obtiene una lista completa de todos los pedidos registrados en el sistema."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de pedidos obtenida exitosamente",
                    content = @Content(mediaType = "application/json", 
                                     schema = @Schema(implementation = PedidoDTO.class)))
    })
    @GetMapping
    public ResponseEntity<List<PedidoDTO>> listarPedidosController() {
        return ResponseEntity.ok(pedidoService.listarPedidos());
    }

    @Operation(
        summary = "Actualizar pedido existente",
        description = "Actualiza la información de un pedido existente. Solo se actualizarán " +
                     "los campos proporcionados en el DTO de actualización."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Pedido actualizado exitosamente",
                    content = @Content(mediaType = "application/json", 
                                     schema = @Schema(implementation = PedidoDTO.class))),
        @ApiResponse(responseCode = "404", description = "Pedido no encontrado",
                    content = @Content),
        @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos",
                    content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<PedidoDTO> actualizarPedidoController(
            @Parameter(description = "ID único del pedido a actualizar", required = true, example = "1")
            @PathVariable Integer id,
            @Parameter(description = "Datos actualizados del pedido", required = true)
            @RequestBody PedidoUpdateDTO pedidoUpdateDTO) {
        return ResponseEntity.ok(pedidoService.actualizarPedido(id, pedidoUpdateDTO));
    }

    @Operation(
        summary = "Eliminar pedido",
        description = "Elimina permanentemente un pedido del sistema mediante su ID único."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Pedido eliminado exitosamente"),
        @ApiResponse(responseCode = "404", description = "Pedido no encontrado",
                    content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPedidoController(
            @Parameter(description = "ID único del pedido a eliminar", required = true, example = "1")
            @PathVariable Integer id) {
        pedidoService.eliminarPedido(id);
        return ResponseEntity.noContent().build();
    }

}
