package com.natycejas.GestionUsuariosApi.ServiceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.natycejas.GestionUsuariosApi.DTOFolder.UsuarioDtosFolder.UsuarioCreateDTO;
import com.natycejas.GestionUsuariosApi.DTOFolder.UsuarioDtosFolder.UsuarioDTO;
import com.natycejas.GestionUsuariosApi.DTOFolder.UsuarioDtosFolder.UsuarioUpdateDTO;
import com.natycejas.GestionUsuariosApi.MapperFolder.UsuarioMapper;
import com.natycejas.GestionUsuariosApi.ModelFolder.Usuario;
import com.natycejas.GestionUsuariosApi.ModelFolder.Region;
import com.natycejas.GestionUsuariosApi.ModelFolder.Comuna;
import com.natycejas.GestionUsuariosApi.RepositoryFolder.UsuarioRepository;
import com.natycejas.GestionUsuariosApi.RepositoryFolder.RegionRepository;
import com.natycejas.GestionUsuariosApi.RepositoryFolder.ComunaRepository;
import com.natycejas.GestionUsuariosApi.RepositoryFolder.CarritoRepository;
import com.natycejas.GestionUsuariosApi.RepositoryFolder.PedidoRepository;
import com.natycejas.GestionUsuariosApi.RepositoryFolder.CarritoProductoRepository;
import com.natycejas.GestionUsuariosApi.Service.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private UsuarioMapper usuarioMapper;
    @Autowired
    private RegionRepository regionRepository;
    @Autowired
    private ComunaRepository comunaRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private CarritoRepository carritoRepository;
    @Autowired
    private PedidoRepository pedidoRepository;
    @Autowired
    private CarritoProductoRepository carritoProductoRepository;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository, UsuarioMapper usuarioMapper,
                              RegionRepository regionRepository, ComunaRepository comunaRepository) {
        this.usuarioRepository = usuarioRepository;
        this.usuarioMapper = usuarioMapper;
        this.regionRepository = regionRepository;
        this.comunaRepository = comunaRepository;
    }

    @Override
    public UsuarioDTO crearUsuario(UsuarioCreateDTO usuarioCreateDTO) {
        Usuario usuario = usuarioMapper.toEntity(usuarioCreateDTO);

        //Implementacion de hasheo de contraseñas
        if (usuario.getContrasena() != null && !usuario.getContrasena().isEmpty()) {
            String hash = passwordEncoder.encode(usuario.getContrasena());
            usuario.setContrasena(hash);
        }

        //Se asignan de región 
        if (usuarioCreateDTO.getIdRegion() != null) {
            Region region = regionRepository.findById(usuarioCreateDTO.getIdRegion())
                .orElseThrow(() -> new RuntimeException("Región no encontrada con id " + usuarioCreateDTO.getIdRegion()));
            usuario.setRegion(region);
        }
        //Asignacion de comuna
        if (usuarioCreateDTO.getIdComuna() != null) {
            Comuna comuna = comunaRepository.findById(usuarioCreateDTO.getIdComuna())
                .orElseThrow(() -> new RuntimeException("Comuna no encontrada con id " + usuarioCreateDTO.getIdComuna()));
            usuario.setComuna(comuna);
        }
        //Se guarda el usuario
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

        if (usuarioUpdateDTO.getIdRegion() != null) {
            Region region = regionRepository.findById(usuarioUpdateDTO.getIdRegion())
                .orElseThrow(() -> new RuntimeException("Región no encontrada con id " + usuarioUpdateDTO.getIdRegion()));
            usuario.setRegion(region);
        }
        if (usuarioUpdateDTO.getIdComuna() != null) {
            Comuna comuna = comunaRepository.findById(usuarioUpdateDTO.getIdComuna())
                .orElseThrow(() -> new RuntimeException("Comuna no encontrada con id " + usuarioUpdateDTO.getIdComuna()));
            usuario.setComuna(comuna);
        }

        Usuario actualizado = usuarioRepository.save(usuario);

        return usuarioMapper.toDTO(actualizado);
    }

    @Override
    @Transactional
    public void eliminarUsuario(Integer id) {
        if (!usuarioRepository.existsById(id)) {
            throw new RuntimeException("Usuario no encontrado con id " + id);
        }

        carritoRepository.findByUsuarioId(id).ifPresent(carrito -> {
            Integer idCarrito = carrito.getIdCarrito();
            pedidoRepository.deleteBoletasByCarritoId(idCarrito);
            carritoProductoRepository.deleteByCarrito_IdCarrito(idCarrito);
            pedidoRepository.deleteByCarrito_IdCarrito(idCarrito);
            carritoRepository.deleteById(idCarrito);
        });
        usuarioRepository.deleteBoletaByUsuarioId(id);
        usuarioRepository.deleteById(id);
    }

    // Autenticación por correo y contraseña
    @Override
    public UsuarioDTO encontrarUsuarioPorCorreoyContrasena(String email, String contrasena) {
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Credenciales no validas"));

        if (!passwordEncoder.matches(contrasena, usuario.getContrasena())) {
            throw new RuntimeException("Credenciales no validas");
        }

        return usuarioMapper.toDTO(usuario);
    }

    // Crudo (devuelve la entidad Usuario)
    @Override
    public Usuario obtenerUsuarioCrudoPorId(Integer id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con id " + id));
    }

    @Override
    public List<Usuario> listarUsuariosCrudo() {
        return usuarioRepository.findAll();
    }
}
