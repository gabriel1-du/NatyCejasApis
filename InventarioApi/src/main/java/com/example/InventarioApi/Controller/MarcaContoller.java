package com.example.InventarioApi.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.InventarioApi.Model.Marca;
import com.example.InventarioApi.ServiceImpl.MarcaServiceImpl;

import lombok.RequiredArgsConstructor;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/inventario/marcas")
@RequiredArgsConstructor
@Tag(name = "Marcas", description = "Operaciones CRUD de marcas") // Swagger
public class MarcaContoller {

    @Autowired
    MarcaServiceImpl marcaService;

    @GetMapping //Buscar todo
    @Operation(summary = "Listar marcas", description = "Obtiene todas las marcas registradas") // Swagger
    @ApiResponse(responseCode = "200", description = "Lista obtenida correctamente") // Swagger
    public List<Marca> listarMarcas() { // Metodos
        return marcaService.listarMarcas();
    }

    @GetMapping("/{id}") //Buscar por ID
    @Operation(summary = "Obtener marca por ID", description = "Devuelve una marca existente o 404 si no existe") // Swagger
    @ApiResponses({ // Swagger
        @ApiResponse(responseCode = "200", description = "Marca encontrada"),
        @ApiResponse(responseCode = "404", description = "Marca no encontrada")
    })
    public ResponseEntity<Marca> obtenerMarca(@Parameter(description = "ID de la marca", example = "1") @PathVariable Integer id) { // Metodos
        return marcaService.obtenerMarcaPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping //Crear
    @Operation(summary = "Crear marca", description = "Crea una nueva marca") // Swagger
    @ApiResponse(responseCode = "200", description = "Marca creada correctamente") // Swagger
    public Marca crearMarca(@RequestBody Marca marca) { // Metodos
        return marcaService.guardarMarca(marca);
    }

    @DeleteMapping("/{id}") //Eliminar por ID
    @Operation(summary = "Eliminar marca", description = "Elimina una marca por su ID") // Swagger
    @ApiResponse(responseCode = "204", description = "Marca eliminada") // Swagger
    public ResponseEntity<Void> eliminarMarca(@Parameter(description = "ID de la marca", example = "1") @PathVariable Integer id) { // Metodos
        marcaService.eliminarMarca(id);
        return ResponseEntity.noContent().build();
    }
}
