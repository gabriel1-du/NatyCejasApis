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
import com.natycejas.GestionUsuariosApi.Service.UsuarioService;


@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<UsuarioDTO> crearUsuarioController(@RequestBody UsuarioCreateDTO usuarioCreateDTO) {
        return ResponseEntity.ok(usuarioService.crearUsuario(usuarioCreateDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> obtenerUsuarioController(@PathVariable Integer id) {
        return ResponseEntity.ok(usuarioService.obtenerUsuarioPorId(id));
    }

    @GetMapping
    public ResponseEntity<List<UsuarioDTO>> listarUsuariosController() {
        return ResponseEntity.ok(usuarioService.listarUsuarios());
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioDTO> actualizarUsuarioController(
            @PathVariable Integer id,
            @RequestBody UsuarioUpdateDTO usuarioUpdateDTO) {
        return ResponseEntity.ok(usuarioService.actualizarUsuario(id, usuarioUpdateDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarUsuarioController(@PathVariable Integer id) {
        usuarioService.eliminarUsuario(id);
        return ResponseEntity.noContent().build();
    }

}
