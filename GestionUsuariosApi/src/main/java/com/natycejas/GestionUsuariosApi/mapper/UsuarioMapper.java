package com.natycejas.GestionUsuariosApi.mapper;

import com.natycejas.GestionUsuariosApi.dto.UsuarioDtosFolder.UsuarioCreateDTO;
import com.natycejas.GestionUsuariosApi.dto.UsuarioDtosFolder.UsuarioDTO;
import com.natycejas.GestionUsuariosApi.dto.UsuarioDtosFolder.UsuarioUpdateDTO;
import com.natycejas.GestionUsuariosApi.model.Usuario;
import org.springframework.stereotype.Component;

@Component
public class UsuarioMapper {

    public UsuarioDTO toDTO(Usuario usuario) {
        if (usuario == null) {
            return null;
        }
        
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setIdUsuario(usuario.getIdUsuario());
        usuarioDTO.setNombre(usuario.getNombre());
        usuarioDTO.setApellido(usuario.getApellido());
        usuarioDTO.setRut(usuario.getRut());
        usuarioDTO.setEmail(usuario.getEmail());
        usuarioDTO.setTelefono(usuario.getTelefono());
        usuarioDTO.setDireccion(usuario.getDireccion());
        usuarioDTO.setAdmin(usuario.getAdmin());
        
        return usuarioDTO;
    }
    
    public Usuario toEntity(UsuarioCreateDTO usuarioCreateDTO) {
        if (usuarioCreateDTO == null) {
            return null;
        }
        
        Usuario usuario = new Usuario();
        usuario.setNombre(usuarioCreateDTO.getNombre());
        usuario.setApellido(usuarioCreateDTO.getApellido());
        usuario.setRut(usuarioCreateDTO.getRut());
        usuario.setEmail(usuarioCreateDTO.getEmail());
        usuario.setTelefono(usuarioCreateDTO.getTelefono());
        usuario.setDireccion(usuarioCreateDTO.getDireccion());
        usuario.setContrasena(usuarioCreateDTO.getContrasena());
        usuario.setAdmin(usuarioCreateDTO.getAdmin());
        
        return usuario;
    }
    
    public void updateEntityFromDTO(UsuarioUpdateDTO usuarioUpdateDTO, Usuario usuario) {
        if (usuarioUpdateDTO == null || usuario == null) {
            return;
        }
        
        if (usuarioUpdateDTO.getNombre() != null) {
            usuario.setNombre(usuarioUpdateDTO.getNombre());
        }
        
        if (usuarioUpdateDTO.getApellido() != null) {
            usuario.setApellido(usuarioUpdateDTO.getApellido());
        }
        
        if (usuarioUpdateDTO.getTelefono() != null) {
            usuario.setTelefono(usuarioUpdateDTO.getTelefono());
        }
        
        if (usuarioUpdateDTO.getDireccion() != null) {
            usuario.setDireccion(usuarioUpdateDTO.getDireccion());
        }
        
        if (usuarioUpdateDTO.getContrasena() != null) {
            usuario.setContrasena(usuarioUpdateDTO.getContrasena());
        }
        
        // Actualizar el campo admin
        usuario.setAdmin(usuarioUpdateDTO.getAdmin());
    }
}