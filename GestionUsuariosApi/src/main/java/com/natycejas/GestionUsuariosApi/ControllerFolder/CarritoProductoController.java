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
import com.natycejas.GestionUsuariosApi.DTOFolder.CarritoProductoDtosFolder.CarritoProductoLiteDTO;
import com.natycejas.GestionUsuariosApi.Service.CarritoProductoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/carrito-producto")
@Tag(name = "Carrito Producto", description = "API para la gestión de productos dentro de los carritos de compras. Permite agregar, consultar, actualizar y eliminar productos de los carritos.")
public class CarritoProductoController {

     @Autowired
    private CarritoProductoService carritoProductoService;

    @Operation(
        summary = "Listar todos los productos del carrito",
        description = "Obtiene una lista completa de todos los productos agregados a los carritos en el sistema."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de productos del carrito obtenida exitosamente",
                    content = @Content(mediaType = "application/json", 
                                     schema = @Schema(implementation = CarritoProductoDTO.class)))
    })
    @GetMapping
    public ResponseEntity<List<CarritoProductoDTO>> listarTodosController() {
        return ResponseEntity.ok(carritoProductoService.listarTodos());
    }

    @Operation(
        summary = "Listar productos por ID de carrito",
        description = "Obtiene todos los productos asociados a un carrito específico por su ID."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Productos del carrito obtenidos exitosamente",
                    content = @Content(mediaType = "application/json",
                                     schema = @Schema(implementation = CarritoProductoDTO.class)))
    })
    @GetMapping("/carrito/{idCarrito}")
    public ResponseEntity<List<CarritoProductoLiteDTO>> listarPorCarritoIdController(
            @Parameter(description = "ID único del carrito", required = true, example = "1")
            @PathVariable Integer idCarrito) {
        return ResponseEntity.ok(carritoProductoService.listarPorCarritoId(idCarrito));
    }

    @Operation(
        summary = "Buscar producto del carrito por ID",
        description = "Recupera la información completa de un producto específico dentro de un carrito mediante su ID único."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Producto del carrito encontrado exitosamente",
                    content = @Content(mediaType = "application/json", 
                                     schema = @Schema(implementation = CarritoProductoDTO.class))),
        @ApiResponse(responseCode = "404", description = "Producto del carrito no encontrado",
                    content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<CarritoProductoDTO> buscarPorIdController(
            @Parameter(description = "ID único del producto en el carrito", required = true, example = "1")
            @PathVariable Integer id) {
        CarritoProductoDTO dto = carritoProductoService.buscarPorId(id);
        return dto != null ? ResponseEntity.ok(dto) : ResponseEntity.notFound().build();
    }

    @Operation(
        summary = "Agregar producto al carrito",
        description = "Agrega un nuevo producto a un carrito específico con la cantidad indicada."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Producto agregado al carrito exitosamente",
                    content = @Content(mediaType = "application/json", 
                                     schema = @Schema(implementation = CarritoProductoDTO.class))),
        @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos",
                    content = @Content),
        @ApiResponse(responseCode = "404", description = "Carrito o producto no encontrado",
                    content = @Content)
    })
    @PostMapping
    public ResponseEntity<CarritoProductoDTO> crearController(
            @Parameter(description = "Datos del producto a agregar al carrito", required = true)
            @RequestBody CarritoProductoCreateDTO carritoProductoCreateDTO) {
        CarritoProductoDTO dto = carritoProductoService.crear(carritoProductoCreateDTO);
        return ResponseEntity.ok(dto);
    }

    @Operation(
        summary = "Actualizar producto del carrito",
        description = "Actualiza la información de un producto existente en el carrito, como la cantidad."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Producto del carrito actualizado exitosamente",
                    content = @Content(mediaType = "application/json", 
                                     schema = @Schema(implementation = CarritoProductoDTO.class))),
        @ApiResponse(responseCode = "404", description = "Producto del carrito no encontrado",
                    content = @Content),
        @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos",
                    content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<CarritoProductoDTO> actualizarController(
            @Parameter(description = "ID único del producto en el carrito a actualizar", required = true, example = "1")
            @PathVariable Integer id, 
            @Parameter(description = "Datos actualizados del producto en el carrito", required = true)
            @RequestBody CarritoProductoUpdateDTO carritoProductoUpdateDTO) {
        CarritoProductoDTO dto = carritoProductoService.actualizar(id, carritoProductoUpdateDTO);
        return dto != null ? ResponseEntity.ok(dto) : ResponseEntity.notFound().build();
    }

    @Operation(
        summary = "Eliminar producto del carrito",
        description = "Elimina permanentemente un producto específico de un carrito mediante su ID único."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Producto eliminado del carrito exitosamente"),
        @ApiResponse(responseCode = "404", description = "Producto del carrito no encontrado",
                    content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarController(
            @Parameter(description = "ID único del producto en el carrito a eliminar", required = true, example = "1")
            @PathVariable Integer id) {
        carritoProductoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

}
