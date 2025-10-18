package com.example.InventarioApi.Controller.ProductoControllerFolder;

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

import com.example.InventarioApi.Model.ProductoModelsFolder.CategoriaProducto;
import com.example.InventarioApi.Service.ProductoServicesFolder.CategoriaProductoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/inventario/categorias")
@Tag(name = "Categorías", description = "Operaciones CRUD de categorías de producto")
public class CategoriaProductoController {

    @Autowired
    private CategoriaProductoService categoriaServicio;

    // Listar todas las categorías
    @GetMapping
    @Operation(summary = "Listar categorías", description = "Obtiene todas las categorías")
    @ApiResponse(responseCode = "200", description = "Lista obtenida correctamente")
    public List<CategoriaProducto> listarCategorias() {
        return categoriaServicio.listarCategorias();
    }

    // Obtener una categoría por ID
    @GetMapping("/{id}")
    @Operation(summary = "Obtener categoría por ID", description = "Devuelve una categoría existente")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Categoría encontrada"),
        @ApiResponse(responseCode = "404", description = "Categoría no encontrada")
    })
    public ResponseEntity<CategoriaProducto> obtenerCategoria(@Parameter(description = "ID de la categoría", example = "1") @PathVariable Integer id) {
        return categoriaServicio.obtenerCategoriaPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Crear una nueva categoría
    @PostMapping
    @Operation(summary = "Crear categoría", description = "Crea una nueva categoría")
    @ApiResponse(responseCode = "200", description = "Categoría creada correctamente")
    public CategoriaProducto crearCategoria(@RequestBody CategoriaProducto categoria) {
        return categoriaServicio.guardarCategoria(categoria);
    }

    // Eliminar una categoría por ID
    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar categoría", description = "Elimina una categoría por ID")
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "Categoría eliminada"),
        @ApiResponse(responseCode = "404", description = "Categoría no encontrada")
    })
    public ResponseEntity<Void> eliminarCategoria(@Parameter(description = "ID de la categoría", example = "1") @PathVariable Integer id) {
        categoriaServicio.eliminarCategoria(id);
        return ResponseEntity.noContent().build();
    }
    
}
