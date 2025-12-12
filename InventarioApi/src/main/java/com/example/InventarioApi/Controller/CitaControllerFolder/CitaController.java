package com.example.InventarioApi.Controller.CitaControllerFolder;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.InventarioApi.Model.Cita;
import com.example.InventarioApi.ServiceImpl.CitaServiceImplFolder.CitaServiceImpl;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Controller
@RestController
@RequestMapping("inventario/citas") // URL acceso
@Tag(name = "Citas", description = "Operaciones CRUD de citas") // Swagger
public class CitaController {

    @Autowired
    private CitaServiceImpl citaServiceImpl; // Inyección de servicio impl

    @GetMapping
    @Operation(summary = "Listar citas", description = "Obtiene todas las citas") // Swagger
    @ApiResponse(responseCode = "200", description = "Lista obtenida correctamente") // Swagger
    public List<Cita> obtenerTodosController() { // Metodos
        return citaServiceImpl.obtenerTodos();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener cita por ID", description = "Devuelve una cita existente o 404 si no existe") // Swagger
    @ApiResponses({ // Swagger
        @ApiResponse(responseCode = "200", description = "Cita encontrada"),
        @ApiResponse(responseCode = "404", description = "Cita no encontrada")
    })
    public ResponseEntity<Cita> obtenerPorIdController(@Parameter(description = "ID de la cita", example = "1") @PathVariable Integer id) { // Metodos
        try {
            return ResponseEntity.ok(citaServiceImpl.obtenerPorId(id));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    @Operation(summary = "Crear cita", description = "Crea una nueva cita") // Swagger
    @ApiResponse(responseCode = "200", description = "Cita creada correctamente") // Swagger
    public Cita crearController(@RequestBody Cita cita) { // Metodos
        return citaServiceImpl.crear(cita);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar cita", description = "Actualiza una cita existente") // Swagger
    @ApiResponses({ // Swagger
        @ApiResponse(responseCode = "200", description = "Cita actualizada correctamente"),
        @ApiResponse(responseCode = "404", description = "Cita no encontrada")
    })
    public ResponseEntity<Cita> actualizarController(@PathVariable Integer id, @RequestBody Cita cita) { // Metodos
        try {
            return ResponseEntity.ok(citaServiceImpl.actualizar(id, cita));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar cita", description = "Elimina una cita por su ID") // Swagger
    @ApiResponses({ // Swagger
        @ApiResponse(responseCode = "200", description = "Cita eliminada correctamente"),
        @ApiResponse(responseCode = "404", description = "Cita no encontrada")
    })
    public ResponseEntity<Void> eliminarController(@PathVariable Integer id) { // Metodos
        try {
            citaServiceImpl.eliminar(id);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}