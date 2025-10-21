package com.example.InventarioApi.Controller.ProductoControllerFolder;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.MediaType;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;

import com.example.InventarioApi.DTO.ProductoDTOs.CreateProductoDTO;
import com.example.InventarioApi.DTO.ProductoDTOs.ProductoDTO;
import com.example.InventarioApi.DTO.ProductoDTOs.UpdateProductoDTO;
import com.example.InventarioApi.Model.ProductoModelsFolder.Producto;
import com.example.InventarioApi.ServiceImpl.ProductoServiceImplFolder.ProductoServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;


@RestController
@RequestMapping("/inventario/productos")
@Tag(name = "Productos", description = "Operaciones CRUD y gestión de stock de productos")
public class ProductoController {


    @Autowired
    private ProductoServiceImpl productoService;

    //---Metodos crud---//

    @GetMapping //Obtener Todos
    @Operation(summary = "Listar productos", description = "Obtiene todos los productos") // Swagger
    @ApiResponse(responseCode = "200", description = "Lista obtenida correctamente") // Swagger
    public List<ProductoDTO> obtenerTodosLosProductosController() { // Metodos
        return productoService.obtenerTodosLosProductos();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener producto por ID", description = "Devuelve un producto existente") // Swagger
    @ApiResponses({ // Swagger
        @ApiResponse(responseCode = "200", description = "Producto encontrado"),
        @ApiResponse(responseCode = "404", description = "Producto no encontrado")
    })
    public ProductoDTO obtenerProductoPorIdController(@Parameter(description = "ID del producto", example = "1") @PathVariable Integer id) { // Metodos
        return productoService.obtenerProductoPorId(id);
    }

    @PostMapping
    @Operation(summary = "Crear producto", description = "Crea un nuevo producto") // Swagger
    @ApiResponse(responseCode = "200", description = "Producto creado correctamente") // Swagger
    public ProductoDTO crearProducto(@RequestBody CreateProductoDTO createProductoDTO) { // Metodos
        return productoService.crearProducto(createProductoDTO);
    }

    @PutMapping("/{id}") //Actulizar
    @Operation(summary = "Actualizar producto", description = "Actualiza completamente un producto por ID") // Swagger
    @ApiResponses({ // Swagger
        @ApiResponse(responseCode = "200", description = "Producto actualizado"),
        @ApiResponse(responseCode = "404", description = "Producto no encontrado")
    })
    public ProductoDTO updateProducto(@Parameter(description = "ID del producto", example = "1") @PathVariable Integer id, @RequestBody UpdateProductoDTO updateDTO) { // Metodos
        return productoService.actualizarProducto(id, updateDTO);
    }

    @PatchMapping("/{id}/sumar")
    @Operation(summary = "Sumar stock", description = "Incrementa el stock del producto") // Swagger
    public Producto sumarStock(@Parameter(description = "ID del producto", example = "1") @PathVariable Integer id,
                               @Parameter(description = "Cantidad a sumar", example = "5") @RequestParam Integer cantidad) { // Metodos
        return productoService.sumarStock(id, cantidad);
    }

    @PatchMapping("/{id}/restar")
    @Operation(summary = "Restar stock", description = "Reduce el stock del producto") // Swagger
    public Producto restarStock(@Parameter(description = "ID del producto", example = "1") @PathVariable Integer id,
                                @Parameter(description = "Cantidad a restar", example = "3") @RequestParam Integer cantidad) { // Metodos
        return productoService.restarStock(id, cantidad);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar producto", description = "Elimina un producto por ID") // Swagger
    @ApiResponses({ // Swagger
        @ApiResponse(responseCode = "204", description = "Producto eliminado"),
        @ApiResponse(responseCode = "404", description = "Producto no encontrado")
    })
    public ResponseEntity<Void> eliminarProducto(@Parameter(description = "ID del producto", example = "1") @PathVariable Integer id) { // Metodos
        productoService.eliminarProducto(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping(value = "/con-foto", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Crear producto con imagen", description = "Crea un producto recibiendo datos y una imagen vía multipart/form-data, guarda la imagen en uploads/productos y persiste la ruta en la BD") // Swagger
    @ApiResponses({ // Swagger
        @ApiResponse(responseCode = "200", description = "Producto creado con imagen"),
        @ApiResponse(responseCode = "400", description = "Datos inválidos")
    })
    public ProductoDTO crearProductoConFoto(
        @Parameter(description = "Nombre del producto") @RequestParam String nombre_producto,
        @Parameter(description = "Descripción") @RequestParam String descripcion,
        @Parameter(description = "Precio") @RequestParam Integer precio,
        @Parameter(description = "Stock") @RequestParam Integer stock,
        @Parameter(description = "ID de la marca") @RequestParam Integer id_marca,
        @Parameter(description = "ID de la categoría") @RequestParam Integer id_categoria,
        @Parameter(description = "Archivo de la foto") @RequestParam(value = "foto", required = false) MultipartFile foto
    ) { // Metodos
        return productoService.crearProductoConFoto(
            nombre_producto, descripcion, precio, stock, id_marca, id_categoria, foto
        );
    }

    @PutMapping(value = "/{id}/con-foto", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Actualizar producto con imagen", description = "Actualiza datos y opcionalmente reemplaza la imagen del producto vía multipart/form-data") // Swagger
    @ApiResponses({ // Swagger
        @ApiResponse(responseCode = "200", description = "Producto actualizado con imagen"),
        @ApiResponse(responseCode = "404", description = "Producto no encontrado")
    })
    public ProductoDTO actualizarProductoConFoto(
        @Parameter(description = "ID del producto", example = "1") @PathVariable Integer id,
        @Parameter(description = "Nombre del producto") @RequestParam String nombre_producto,
        @Parameter(description = "Descripción") @RequestParam String descripcion,
        @Parameter(description = "Precio") @RequestParam Integer precio,
        @Parameter(description = "Stock") @RequestParam Integer stock,
        @Parameter(description = "ID de la marca") @RequestParam Integer id_marca,
        @Parameter(description = "ID de la categoría") @RequestParam Integer id_categoria,
        @Parameter(description = "Archivo de la foto") @RequestParam(value = "foto", required = false) MultipartFile foto
    ) { // Metodos
        return productoService.actualizarProductoConFoto(
            id, nombre_producto, descripcion, precio, stock, id_marca, id_categoria, foto
        );
    }

    @GetMapping("/{id}/foto")
    @Operation(summary = "Obtener foto del producto", description = "Devuelve la imagen del producto guardada en el servidor") // Swagger
    @ApiResponses({ // Swagger
        @ApiResponse(responseCode = "200", description = "Imagen devuelta"),
        @ApiResponse(responseCode = "404", description = "Foto no encontrada")
    })
    public ResponseEntity<byte[]> obtenerFotoProducto(@Parameter(description = "ID del producto", example = "1") @PathVariable Integer id) { // Metodos
        String fotoUrl = productoService.obtenerRutaFotoProducto(id);
        if (fotoUrl == null || fotoUrl.isBlank()) {
            return ResponseEntity.notFound().build();
        }
        try {
            Path path = Paths.get(fotoUrl);
            if (!Files.exists(path)) {
                return ResponseEntity.notFound().build();
            }
            byte[] bytes = Files.readAllBytes(path);
            MediaType mediaType = mediaTypeFromPath(fotoUrl);
            return ResponseEntity.ok().contentType(mediaType).body(bytes);
        } catch (IOException e) {
            return ResponseEntity.notFound().build();
        }
    }

    private MediaType mediaTypeFromPath(String path) {
        String lower = path.toLowerCase();
        if (lower.endsWith(".jpg") || lower.endsWith(".jpeg")) return MediaType.IMAGE_JPEG;
        if (lower.endsWith(".png")) return MediaType.IMAGE_PNG;
        if (lower.endsWith(".gif")) return MediaType.IMAGE_GIF;
        if (lower.endsWith(".webp")) return MediaType.parseMediaType("image/webp");
        return MediaType.APPLICATION_OCTET_STREAM;
    }
}


