package com.example.InventarioApi.Controller.ServicioControllerFolder;

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

import com.example.InventarioApi.DTO.ServicioDTOs.CreateServicioDTO;
import com.example.InventarioApi.DTO.ServicioDTOs.ServicioDTO;
import com.example.InventarioApi.DTO.ServicioDTOs.UpdateServicioDTO;
import com.example.InventarioApi.ServiceImpl.ServicioServiceImplsFolder.ServicioServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Controller
@RestController
@RequestMapping("inventario/servicios")//URL acceso
@Tag(name = "Servicios", description = "Operaciones CRUD de servicios")
public class ServicioController {

    @Autowired
    private ServicioServiceImpl servicioServiceImpl; //Inyeccion de servicio impl
    

    @GetMapping
    @Operation(summary = "Listar servicios", description = "Obtiene todos los servicios")
    @ApiResponse(responseCode = "200", description = "Lista obtenida correctamente")
    public List<ServicioDTO> obtenerTodosController() {//Metodo para traer todos los metods (GET)
        return servicioServiceImpl.obtenerTodos();
    }

    @GetMapping("/{id}")//URL
    @Operation(summary = "Obtener servicio por ID", description = "Devuelve un servicio existente o 404 si no existe")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Servicio encontrado"),
        @ApiResponse(responseCode = "404", description = "Servicio no encontrado")
    })
    public ResponseEntity<ServicioDTO> obtenerPorIdController(@Parameter(description = "ID del servicio", example = "1") @PathVariable Integer id) {//Obtener por id al servicio por id (GET)
        try {
            return ResponseEntity.ok(servicioServiceImpl.obtenerPorId(id));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    @Operation(summary = "Crear servicio", description = "Crea un nuevo servicio")
    @ApiResponse(responseCode = "200", description = "Servicio creado correctamente")
    public ServicioDTO guardarController(@RequestBody CreateServicioDTO dto) {//Guardar Servicio con el DTO de crear
        return servicioServiceImpl.guardar(dto);
    }


    @PutMapping("/{id}")//Ingresar id URL
    @Operation(summary = "Actualizar servicio", description = "Actualiza completamente un servicio por ID")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Servicio actualizado"),
        @ApiResponse(responseCode = "404", description = "Servicio no encontrado")
    })
    public ResponseEntity<ServicioDTO> actualizarController(@Parameter(description = "ID del servicio", example = "1") @PathVariable Integer id, @RequestBody UpdateServicioDTO dto) { //Metodo para actullizar (PUT
        try {
            return ResponseEntity.ok(servicioServiceImpl.actualizar(id, dto));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")//Ingresar id URl
    @Operation(summary = "Eliminar servicio", description = "Elimina un servicio por ID")
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "Servicio eliminado"),
        @ApiResponse(responseCode = "404", description = "Servicio no encontrado")
    })
    public ResponseEntity<Void> eliminarController(@Parameter(description = "ID del servicio", example = "1") @PathVariable Integer id) {//Eliminar id 
        try {
            servicioServiceImpl.eliminar(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }










}
