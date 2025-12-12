package com.natycejas.GestionUsuariosApi.ControllerFolder;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.natycejas.GestionUsuariosApi.ModelFolder.Comuna;
import com.natycejas.GestionUsuariosApi.Service.ComunaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/comunas")
@Tag(name = "Comunas", description = "API para la gestión de comunas")
public class ComunaController {

    @Autowired
    private ComunaService comunaService;

    @Operation(summary = "Crear una nueva comuna",
               description = "Crea una comuna con nombre y su región asociada. Para asociar la región, se puede enviar el objeto Región con su id.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Comuna creada exitosamente",
                     content = @Content(mediaType = "application/json",
                                      schema = @Schema(implementation = Comuna.class)))
    })
    @PostMapping
    public ResponseEntity<Comuna> crearComuna(
            @Parameter(description = "Datos de la comuna a crear", required = true)
            @RequestBody Comuna comuna) {
        return ResponseEntity.ok(comunaService.crearComuna(comuna));
    }

    @Operation(summary = "Obtener comuna por ID",
               description = "Recupera la información completa de una comuna mediante su ID.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Comuna encontrada",
                     content = @Content(mediaType = "application/json",
                                      schema = @Schema(implementation = Comuna.class))),
        @ApiResponse(responseCode = "404", description = "Comuna no encontrada", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<Comuna> obtenerComuna(
            @Parameter(description = "ID de la comuna", required = true)
            @PathVariable Integer id) {
        return ResponseEntity.ok(comunaService.obtenerComunaPorId(id));
    }

    @Operation(summary = "Listar comunas",
               description = "Obtiene el listado completo de comunas.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Listado obtenido",
                     content = @Content(mediaType = "application/json",
                                      schema = @Schema(implementation = Comuna.class)))
    })
    @GetMapping
    public ResponseEntity<List<Comuna>> listarComunas() {
        return ResponseEntity.ok(comunaService.listarComunas());
    }

    @Operation(summary = "Actualizar comuna",
               description = "Actualiza el nombre y/o la región asociada de una comuna existente.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Comuna actualizada",
                     content = @Content(mediaType = "application/json",
                                      schema = @Schema(implementation = Comuna.class))),
        @ApiResponse(responseCode = "404", description = "Comuna no encontrada", content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<Comuna> actualizarComuna(
            @Parameter(description = "ID de la comuna", required = true)
            @PathVariable Integer id,
            @Parameter(description = "Datos de la comuna", required = true)
            @RequestBody Comuna comuna) {
        return ResponseEntity.ok(comunaService.actualizarComuna(id, comuna));
    }

    @Operation(summary = "Eliminar comuna",
               description = "Elimina una comuna por su ID.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Comuna eliminada"),
        @ApiResponse(responseCode = "404", description = "Comuna no encontrada", content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarComuna(
            @Parameter(description = "ID de la comuna", required = true)
            @PathVariable Integer id) {
        comunaService.eliminarComuna(id);
        return ResponseEntity.noContent().build();
    }
}