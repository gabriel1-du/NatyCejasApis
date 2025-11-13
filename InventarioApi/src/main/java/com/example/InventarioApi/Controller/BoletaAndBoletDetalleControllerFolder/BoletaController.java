package com.example.InventarioApi.Controller.BoletaAndBoletDetalleControllerFolder;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.InventarioApi.DTO.BoletaAndBoletaDetallesDtos.BoletaDTO;
import com.example.InventarioApi.Model.BoletaModelsFolder.Boleta;
import com.example.InventarioApi.Service.BoletaAndBoletaDetalleServicesFolder.BoletaService;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.RequestBody;
import jakarta.validation.Valid;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;


@RestController
@RequestMapping("/api/boletas")
public class BoletaController {


     @Autowired
    private BoletaService boletaService;

    // ==================== GET ALL ====================
    @GetMapping
    @Operation(summary = "Listar todas las boletas", description = "Devuelve la lista completa de boletas registradas")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Lista obtenida correctamente")
    })
    public ResponseEntity<List<Boleta>> listarTodas() {
        List<Boleta> boletas = boletaService.listarTodas();
        return ResponseEntity.ok(boletas);
    }

    // ==================== GET BY ID ====================
    @GetMapping("/{id}")
    @Operation(summary = "Obtener boleta por ID", description = "Devuelve una boleta existente o 404 si no existe")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Boleta encontrada"),
        @ApiResponse(responseCode = "404", description = "Boleta no encontrada")
    })
    public ResponseEntity<Boleta> obtenerPorId(@PathVariable Integer id) {
        Optional<Boleta> boleta = boletaService.obtenerPorId(id);
        return boleta.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // ==================== POST ====================
    @PostMapping
    @Operation(summary = "Crear nueva boleta", description = "Crea una boleta nueva a partir del DTO enviado")
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Boleta creada correctamente"),
        @ApiResponse(responseCode = "400", description = "Datos inválidos")
    })
    public ResponseEntity<Boleta> crearBoleta(@Valid @RequestBody BoletaDTO boletaDTO) {
        Boleta nuevaBoleta = boletaService.crearBoleta(boletaDTO);
        return ResponseEntity.status(201).body(nuevaBoleta);
    }

    // ==================== PUT ====================
    @PutMapping("/{id}")
    @Operation(summary = "Actualizar boleta existente", description = "Actualiza los datos de una boleta según su ID")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Boleta actualizada correctamente"),
        @ApiResponse(responseCode = "404", description = "Boleta no encontrada")
    })
    public ResponseEntity<Boleta> actualizarBoleta(@PathVariable Integer id, @Valid @RequestBody BoletaDTO boletaDTO) {
        try {
            Boleta boletaActualizada = boletaService.actualizarBoleta(id, boletaDTO);
            return ResponseEntity.ok(boletaActualizada);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // ==================== DELETE ====================
    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar boleta", description = "Elimina una boleta existente por su ID")
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "Boleta eliminada correctamente"),
        @ApiResponse(responseCode = "404", description = "Boleta no encontrada")
    })
    public ResponseEntity<Void> eliminarBoleta(@PathVariable Integer id) {
        boletaService.eliminarBoleta(id);
        return ResponseEntity.noContent().build();
    }
}
