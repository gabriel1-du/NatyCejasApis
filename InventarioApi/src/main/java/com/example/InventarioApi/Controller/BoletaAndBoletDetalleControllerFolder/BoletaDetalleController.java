package com.example.InventarioApi.Controller.BoletaAndBoletDetalleControllerFolder;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.InventarioApi.DTO.BoletaAndBoletaDetallesDtos.DetalleBoletaDTO;
import com.example.InventarioApi.Model.BoletaModelsFolder.DetalleBoleta;
import com.example.InventarioApi.Service.BoletaAndBoletaDetalleServicesFolder.BoletaDetalleService;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.RequestBody;
import jakarta.validation.Valid;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/api/detalleboleta")
public class BoletaDetalleController {

     @Autowired
    private BoletaDetalleService boletaDetalleService;

    // 🟢 Listar todos los detalles
    @GetMapping
    @Operation(summary = "Listar todos los detalles de boleta",
            description = "Devuelve una lista con todos los registros de detalle de boleta almacenados")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista obtenida correctamente")
    })
    public ResponseEntity<List<DetalleBoleta>> listarTodas() {
        return ResponseEntity.ok(boletaDetalleService.listarTodas());
    }

    // 🟢 Obtener un detalle por ID
    @GetMapping("/{id}")
    @Operation(summary = "Obtener detalle de boleta por ID",
            description = "Devuelve un detalle de boleta existente o un código 404 si no existe")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Detalle de boleta encontrado"),
            @ApiResponse(responseCode = "404", description = "Detalle de boleta no encontrado")
    })
    public ResponseEntity<DetalleBoleta> obtenerPorId(@PathVariable Integer id) {
        return boletaDetalleService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // 🟢 Crear un nuevo detalle
    @PostMapping
    @Operation(summary = "Crear nuevo detalle de boleta",
            description = "Crea un nuevo registro de detalle de boleta en base al DTO recibido")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Detalle creado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos")
    })
    public ResponseEntity<DetalleBoleta> crearDetalle(@Valid @RequestBody DetalleBoletaDTO detalleDTO) {
        DetalleBoleta nuevoDetalle = boletaDetalleService.crearDetalle(detalleDTO);
        return ResponseEntity.status(201).body(nuevoDetalle);
    }

    // 🟢 Actualizar un detalle existente
    @PutMapping("/{id}")
    @Operation(summary = "Actualizar detalle de boleta existente",
            description = "Actualiza los datos de un detalle de boleta identificado por su ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Detalle actualizado correctamente"),
            @ApiResponse(responseCode = "404", description = "Detalle no encontrado")
    })
    public ResponseEntity<DetalleBoleta> actualizarDetalle(
            @PathVariable Integer id,
            @Valid @RequestBody DetalleBoletaDTO detalleDTO) {

        try {
            DetalleBoleta actualizado = boletaDetalleService.actualizarDetalle(id, detalleDTO);
            return ResponseEntity.ok(actualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // 🟢 Eliminar un detalle por ID
    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar detalle de boleta por ID",
            description = "Elimina un registro de detalle de boleta por su identificador")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Detalle eliminado correctamente"),
            @ApiResponse(responseCode = "404", description = "Detalle no encontrado")
    })
    public ResponseEntity<Void> eliminarDetalle(@PathVariable Integer id) {
        boletaDetalleService.eliminarDetalle(id);
        return ResponseEntity.noContent().build();
    }

}
