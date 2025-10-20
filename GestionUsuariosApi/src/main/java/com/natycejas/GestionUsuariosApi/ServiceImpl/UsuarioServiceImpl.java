package com.natycejas.GestionUsuariosApi.ServiceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.natycejas.GestionUsuariosApi.DTOFolder.UsuarioDtosFolder.UsuarioCreateDTO;
import com.natycejas.GestionUsuariosApi.DTOFolder.UsuarioDtosFolder.UsuarioDTO;
import com.natycejas.GestionUsuariosApi.DTOFolder.UsuarioDtosFolder.UsuarioUpdateDTO;
import com.natycejas.GestionUsuariosApi.MapperFolder.UsuarioMapper;
import com.natycejas.GestionUsuariosApi.ModelFolder.Usuario;
import com.natycejas.GestionUsuariosApi.RepositoryFolder.UsuarioRepository;
import com.natycejas.GestionUsuariosApi.Service.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private UsuarioMapper usuarioMapper;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository, UsuarioMapper usuarioMapper) {
        this.usuarioRepository = usuarioRepository;
        this.usuarioMapper = usuarioMapper;
    }

    @Override
    public UsuarioDTO crearUsuario(UsuarioCreateDTO usuarioCreateDTO) {
        Usuario usuario = usuarioMapper.toEntity(usuarioCreateDTO);
        Usuario guardado = usuarioRepository.save(usuario);
        return usuarioMapper.toDTO(guardado);
    }

    @Override
    public UsuarioDTO obtenerUsuarioPorId(Integer id) {
        return usuarioRepository.findById(id)
                .map(usuarioMapper::toDTO)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con id " + id));
    }

    @Override
    public List<UsuarioDTO> listarUsuarios() {
        return usuarioRepository.findAll()
                .stream()
                .map(usuarioMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public UsuarioDTO actualizarUsuario(Integer id, UsuarioUpdateDTO usuarioUpdateDTO) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con id " + id));

        usuarioMapper.updateEntityFromDTO(usuarioUpdateDTO, usuario);
        Usuario actualizado = usuarioRepository.save(usuario);

        return usuarioMapper.toDTO(actualizado);
    }

    @Override
    public void eliminarUsuario(Integer id) {
        if (!usuarioRepository.existsById(id)) {
            throw new RuntimeException("Usuario no encontrado con id " + id);
        }
        usuarioRepository.deleteById(id);
    }

    // Autenticación por correo y contraseña
    @Override
    public UsuarioDTO encontrarUsuarioPorCorreoyContrasena(String email, String contrasena) {
        return usuarioRepository.findByEmailAndContrasena(email, contrasena)
                .map(usuarioMapper::toDTO)
                .orElseThrow(() -> new RuntimeException("Credenciales no validas"));
    }
}
