package com.natycejas.GestionUsuariosApi.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.natycejas.GestionUsuariosApi.Service.UsuarioService;
import com.natycejas.GestionUsuariosApi.DTOFolder.UsuarioDtosFolder.UsuarioDTO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/auth")
@Tag(name = "Auth", description = "Autenticación de usuarios")
public class AuthController {

    @Autowired
    private UsuarioService usuarioService;

    @Operation(summary = "Login de usuario", description = "Autentica un usuario usando email y contraseña")
    @ApiResponse(responseCode = "200", description = "Usuario autenticado",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = UsuarioDTO.class)))
    @ApiResponse(responseCode = "404", description = "Credenciales no validas", content = @Content)
    @PostMapping("/login")
    public ResponseEntity<UsuarioDTO> login(@RequestBody AuthRequest authRequest) {
        try {
            UsuarioDTO usuario = usuarioService.encontrarUsuarioPorCorreoyContrasena(authRequest.getEmail(), authRequest.getContrasena());
            return ResponseEntity.ok(usuario);
        } catch (RuntimeException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Credenciales no validas");
        }
    }
}