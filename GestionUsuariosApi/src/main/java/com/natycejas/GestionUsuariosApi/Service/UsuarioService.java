package com.natycejas.GestionUsuariosApi.Service;

import java.util.List;


import com.natycejas.GestionUsuariosApi.DTOFolder.UsuarioDtosFolder.UsuarioCreateDTO;
import com.natycejas.GestionUsuariosApi.DTOFolder.UsuarioDtosFolder.UsuarioDTO;
import com.natycejas.GestionUsuariosApi.DTOFolder.UsuarioDtosFolder.UsuarioUpdateDTO;
import com.natycejas.GestionUsuariosApi.ModelFolder.Usuario;


public interface UsuarioService {
    
    UsuarioDTO crearUsuario(UsuarioCreateDTO usuarioCreateDTO);
    UsuarioDTO obtenerUsuarioPorId(Integer id);
    List<UsuarioDTO> listarUsuarios();
    UsuarioDTO actualizarUsuario(Integer id, UsuarioUpdateDTO usuarioUpdateDTO);
    void eliminarUsuario(Integer id);

    // Autenticación
    UsuarioDTO encontrarUsuarioPorCorreoyContrasena(String email, String contrasena);

    // Crudo (devuelve entidad Usuario)
    Usuario obtenerUsuarioCrudoPorId(Integer id);
    List<Usuario> listarUsuariosCrudo();
}
