package com.natycejas.GestionUsuariosApi.ControllerFolder;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.natycejas.GestionUsuariosApi.ModelFolder.Region;
import com.natycejas.GestionUsuariosApi.Service.RegionService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/regiones")
@Tag(name = "Regiones", description = "API para la gestión de regiones")
public class RegionController {

    @Autowired
    private RegionService regionService;

    @Operation(summary = "Crear una nueva región",
               description = "Crea una región con el nombre proporcionado.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Región creada exitosamente",
                     content = @Content(mediaType = "application/json",
                                      schema = @Schema(implementation = Region.class)))
    })
    @PostMapping
    public ResponseEntity<Region> crearRegion(
            @Parameter(description = "Datos de la región a crear", required = true)
            @RequestBody Region region) {
        return ResponseEntity.ok(regionService.crearRegion(region));
    }

    @Operation(summary = "Obtener región por ID",
               description = "Recupera la información completa de una región mediante su ID.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Región encontrada",
                     content = @Content(mediaType = "application/json",
                                      schema = @Schema(implementation = Region.class))),
        @ApiResponse(responseCode = "404", description = "Región no encontrada", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<Region> obtenerRegion(
            @Parameter(description = "ID de la región", required = true)
            @PathVariable Integer id) {
        return ResponseEntity.ok(regionService.obtenerRegionPorId(id));
    }

    @Operation(summary = "Listar regiones",
               description = "Obtiene el listado completo de regiones.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Listado obtenido",
                     content = @Content(mediaType = "application/json",
                                      schema = @Schema(implementation = Region.class)))
    })
    @GetMapping
    public ResponseEntity<List<Region>> listarRegiones() {
        return ResponseEntity.ok(regionService.listarRegiones());
    }

    @Operation(summary = "Actualizar región",
               description = "Actualiza el nombre de una región existente.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Región actualizada",
                     content = @Content(mediaType = "application/json",
                                      schema = @Schema(implementation = Region.class))),
        @ApiResponse(responseCode = "404", description = "Región no encontrada", content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<Region> actualizarRegion(
            @Parameter(description = "ID de la región", required = true)
            @PathVariable Integer id,
            @Parameter(description = "Datos de la región", required = true)
            @RequestBody Region region) {
        return ResponseEntity.ok(regionService.actualizarRegion(id, region));
    }

    @Operation(summary = "Eliminar región",
               description = "Elimina una región por su ID.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Región eliminada"),
        @ApiResponse(responseCode = "404", description = "Región no encontrada", content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarRegion(
            @Parameter(description = "ID de la región", required = true)
            @PathVariable Integer id) {
        regionService.eliminarRegion(id);
        return ResponseEntity.noContent().build();
    }
}