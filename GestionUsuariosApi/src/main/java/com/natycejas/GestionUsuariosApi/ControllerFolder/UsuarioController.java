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

import com.natycejas.GestionUsuariosApi.DTOFolder.UsuarioDtosFolder.UsuarioCreateDTO;
import com.natycejas.GestionUsuariosApi.DTOFolder.UsuarioDtosFolder.UsuarioDTO;
import com.natycejas.GestionUsuariosApi.DTOFolder.UsuarioDtosFolder.UsuarioUpdateDTO;
import com.natycejas.GestionUsuariosApi.ModelFolder.Usuario;
import com.natycejas.GestionUsuariosApi.Service.UsuarioService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/usuarios")
@Tag(name = "Usuarios", description = "API para la gestión de usuarios del sistema")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Operation(
        summary = "Crear un nuevo usuario",
        description = "Crea un nuevo usuario en el sistema con la información proporcionada. " +
                     "Valida que el email y RUT sean únicos antes de crear el usuario."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Usuario creado exitosamente",
                    content = @Content(mediaType = "application/json", 
                                     schema = @Schema(implementation = UsuarioDTO.class))),
        @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos",
                    content = @Content),
        @ApiResponse(responseCode = "409", description = "Email o RUT ya existe en el sistema",
                    content = @Content)
    })
    @PostMapping
    public ResponseEntity<UsuarioDTO> crearUsuarioController(
            @Parameter(description = "Datos del usuario a crear", required = true)
            @RequestBody UsuarioCreateDTO usuarioCreateDTO) {
        return ResponseEntity.ok(usuarioService.crearUsuario(usuarioCreateDTO));
    }

    @Operation(
        summary = "Obtener usuario por ID",
        description = "Recupera la información completa de un usuario específico mediante su ID único."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Usuario encontrado exitosamente",
                    content = @Content(mediaType = "application/json", 
                                     schema = @Schema(implementation = UsuarioDTO.class))),
        @ApiResponse(responseCode = "404", description = "Usuario no encontrado",
                    content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> obtenerUsuarioController(
            @Parameter(description = "ID único del usuario", required = true, example = "1")
            @PathVariable Integer id) {
        return ResponseEntity.ok(usuarioService.obtenerUsuarioPorId(id));
    }

    @Operation(
        summary = "Listar todos los usuarios",
        description = "Obtiene una lista completa de todos los usuarios registrados en el sistema."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de usuarios obtenida exitosamente",
                    content = @Content(mediaType = "application/json", 
                                     schema = @Schema(implementation = UsuarioDTO.class)))
    })
    @GetMapping
    public ResponseEntity<List<UsuarioDTO>> listarUsuariosController() {
        return ResponseEntity.ok(usuarioService.listarUsuarios());
    }

    // Endpoints crudo (devuelven la entidad Usuario)
    @GetMapping("/crudo/{id}")
    public ResponseEntity<Usuario> obtenerUsuarioCrudoPorId(
            @Parameter(description = "ID único del usuario", required = true, example = "1")
            @PathVariable Integer id) {
        return ResponseEntity.ok(usuarioService.obtenerUsuarioCrudoPorId(id));
    }

    @GetMapping("/crudo")
    public ResponseEntity<List<Usuario>> listarUsuariosCrudo() {
        return ResponseEntity.ok(usuarioService.listarUsuariosCrudo());
    }

    @Operation(
        summary = "Actualizar usuario existente",
        description = "Actualiza la información de un usuario existente. Solo se actualizarán " +
                     "los campos proporcionados en el DTO de actualización."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Usuario actualizado exitosamente",
                    content = @Content(mediaType = "application/json", 
                                     schema = @Schema(implementation = UsuarioDTO.class))),
        @ApiResponse(responseCode = "404", description = "Usuario no encontrado",
                    content = @Content),
        @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos",
                    content = @Content),
        @ApiResponse(responseCode = "409", description = "Email ya existe en el sistema",
                    content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<UsuarioDTO> actualizarUsuarioController(
            @Parameter(description = "ID único del usuario a actualizar", required = true, example = "1")
            @PathVariable Integer id,
            @Parameter(description = "Datos actualizados del usuario", required = true)
            @RequestBody UsuarioUpdateDTO usuarioUpdateDTO) {
        return ResponseEntity.ok(usuarioService.actualizarUsuario(id, usuarioUpdateDTO));
    }

    @Operation(
        summary = "Eliminar usuario",
        description = "Elimina permanentemente un usuario del sistema mediante su ID único."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Usuario eliminado exitosamente"),
        @ApiResponse(responseCode = "404", description = "Usuario no encontrado",
                    content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarUsuarioController(
            @Parameter(description = "ID único del usuario a eliminar", required = true, example = "1")
            @PathVariable Integer id) {
        usuarioService.eliminarUsuario(id);
        return ResponseEntity.noContent().build();
    }

}
