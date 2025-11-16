package com.natycejas.GestionUsuariosApi.ControllerFolder;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/carritos")
@Tag(name = "Carrito", description = "API para la gestión de carritos de compras. Permite crear, consultar, actualizar y eliminar carritos de usuarios.")
public class CarritoController {

     @Autowired
    private CarritoService carritoService;

    @Operation(
        summary = "Listar todos los carritos",
        description = "Obtiene una lista completa de todos los carritos registrados en el sistema."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de carritos obtenida exitosamente",
                    content = @Content(mediaType = "application/json", 
                                     schema = @Schema(implementation = CarritoDTO.class)))
    })
    @GetMapping
    public ResponseEntity<List<CarritoDTO>> listarTodos() {
        return ResponseEntity.ok(carritoService.listarTodos());
    }

    @Operation(
        summary = "Obtener carrito por ID",
        description = "Recupera la información completa de un carrito específico mediante su ID único."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Carrito encontrado exitosamente",
                    content = @Content(mediaType = "application/json", 
                                     schema = @Schema(implementation = CarritoDTO.class))),
        @ApiResponse(responseCode = "404", description = "Carrito no encontrado",
                    content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<CarritoDTO> obtenerPorId(
            @Parameter(description = "ID único del carrito", required = true, example = "1")
            @PathVariable Integer id) {
        return ResponseEntity.ok(carritoService.buscarPorId(id));
    }

    @Operation(
        summary = "Obtener carrito por ID de usuario",
        description = "Recupera el carrito asociado al usuario mediante su ID."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Carrito encontrado exitosamente",
                    content = @Content(mediaType = "application/json", 
                                     schema = @Schema(implementation = CarritoDTO.class))),
        @ApiResponse(responseCode = "404", description = "Carrito o usuario no encontrado",
                    content = @Content)
    })
    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<CarritoDTO> obtenerPorUsuarioId(
            @Parameter(description = "ID único del usuario", required = true, example = "1")
            @PathVariable Integer idUsuario) {
        return ResponseEntity.ok(carritoService.buscarPorUsuarioId(idUsuario));
    }

    @Operation(
        summary = "Crear un nuevo carrito",
        description = "Crea un nuevo carrito de compras en el sistema asociado a un usuario específico."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Carrito creado exitosamente",
                    content = @Content(mediaType = "application/json", 
                                     schema = @Schema(implementation = CarritoDTO.class))),
        @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos",
                    content = @Content),
        @ApiResponse(responseCode = "404", description = "Usuario no encontrado",
                    content = @Content)
    })
    @PostMapping
    public ResponseEntity<CarritoDTO> crear(
            @Parameter(description = "Datos del carrito a crear", required = true)
            @RequestBody CarritoCreateDTO carritoCreateDTO) {
        CarritoDTO nuevoCarrito = carritoService.crear(carritoCreateDTO);
        return ResponseEntity.ok(nuevoCarrito);
    }

    @Operation(
        summary = "Actualizar carrito existente",
        description = "Actualiza la información de un carrito existente. Solo se actualizarán " +
                     "los campos proporcionados en el DTO de actualización."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Carrito actualizado exitosamente",
                    content = @Content(mediaType = "application/json", 
                                     schema = @Schema(implementation = CarritoDTO.class))),
        @ApiResponse(responseCode = "404", description = "Carrito no encontrado",
                    content = @Content),
        @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos",
                    content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<CarritoDTO> actualizar(
            @Parameter(description = "ID único del carrito a actualizar", required = true, example = "1")
            @PathVariable Integer id, 
            @Parameter(description = "Datos actualizados del carrito", required = true)
            @RequestBody CarritoUpdateDTO carritoUpdateDTO) {
        CarritoDTO carritoActualizado = carritoService.actualizar(id, carritoUpdateDTO);
        return ResponseEntity.ok(carritoActualizado);
    }

    @Operation(
        summary = "Eliminar carrito",
        description = "Elimina permanentemente un carrito del sistema mediante su ID único."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Carrito eliminado exitosamente"),
        @ApiResponse(responseCode = "404", description = "Carrito no encontrado",
                    content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(
            @Parameter(description = "ID único del carrito a eliminar", required = true, example = "1")
            @PathVariable Integer id) {
        carritoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

}
