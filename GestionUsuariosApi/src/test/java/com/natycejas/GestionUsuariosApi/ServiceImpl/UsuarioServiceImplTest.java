package com.natycejas.GestionUsuariosApi.ServiceImpl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.natycejas.GestionUsuariosApi.DTOFolder.UsuarioDtosFolder.UsuarioCreateDTO;
import com.natycejas.GestionUsuariosApi.DTOFolder.UsuarioDtosFolder.UsuarioDTO;
import com.natycejas.GestionUsuariosApi.MapperFolder.UsuarioMapper;
import com.natycejas.GestionUsuariosApi.ModelFolder.Comuna;
import com.natycejas.GestionUsuariosApi.ModelFolder.Region;
import com.natycejas.GestionUsuariosApi.ModelFolder.Usuario;
import com.natycejas.GestionUsuariosApi.RepositoryFolder.ComunaRepository;
import com.natycejas.GestionUsuariosApi.RepositoryFolder.RegionRepository;
import com.natycejas.GestionUsuariosApi.RepositoryFolder.UsuarioRepository;

@ExtendWith(MockitoExtension.class)
class UsuarioServiceImplTest {

    @InjectMocks
    private UsuarioServiceImpl usuarioService;

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private UsuarioMapper usuarioMapper;

    @Mock
    private RegionRepository regionRepository;

    @Mock
    private ComunaRepository comunaRepository;

    private UsuarioCreateDTO createDTO;
    private Usuario usuarioEntity;
    private Usuario usuarioGuardado;
    private UsuarioDTO usuarioDTO;
    private Region region;
    private Comuna comuna;

    @BeforeEach
    void setUp() {
        // Objetos base para los tests
        region = new Region(1, "Metropolitana");
        comuna = new Comuna(2, "Santiago", region);

        createDTO = new UsuarioCreateDTO(
                "Naty",
                "Cejas",
                "11.111.111-1",
                "naty@example.com",
                "123456789",
                "Calle Falsa 123",
                "secreta",
                false,
                region.getIdRegion(),
                comuna.getIdComuna()
        );

        usuarioEntity = new Usuario();
        usuarioEntity.setNombre(createDTO.getNombre());
        usuarioEntity.setApellido(createDTO.getApellido());
        usuarioEntity.setRut(createDTO.getRut());
        usuarioEntity.setEmail(createDTO.getEmail());
        usuarioEntity.setTelefono(createDTO.getTelefono());
        usuarioEntity.setDireccion(createDTO.getDireccion());
        usuarioEntity.setContrasena(createDTO.getContrasena());
        usuarioEntity.setAdmin(createDTO.getAdmin());

        usuarioGuardado = new Usuario();
        usuarioGuardado.setIdUsuario(10);
        usuarioGuardado.setNombre(createDTO.getNombre());
        usuarioGuardado.setApellido(createDTO.getApellido());
        usuarioGuardado.setRut(createDTO.getRut());
        usuarioGuardado.setEmail(createDTO.getEmail());
        usuarioGuardado.setTelefono(createDTO.getTelefono());
        usuarioGuardado.setDireccion(createDTO.getDireccion());
        usuarioGuardado.setContrasena(createDTO.getContrasena());
        usuarioGuardado.setAdmin(createDTO.getAdmin());
        usuarioGuardado.setRegion(region);
        usuarioGuardado.setComuna(comuna);

        usuarioDTO = new UsuarioDTO(
                usuarioGuardado.getIdUsuario(),
                usuarioGuardado.getNombre(),
                usuarioGuardado.getApellido(),
                usuarioGuardado.getRut(),
                usuarioGuardado.getEmail(),
                usuarioGuardado.getTelefono(),
                usuarioGuardado.getDireccion(),
                usuarioGuardado.getAdmin(),
                region.getNombreRegion(),
                comuna.getNombreComuna()
        );
    }

    @Test
    void crearUsuario_deberiaGuardarYRetornarDTO() {
        when(usuarioMapper.toEntity(createDTO)).thenReturn(usuarioEntity);
        when(regionRepository.findById(region.getIdRegion())).thenReturn(Optional.of(region));
        when(comunaRepository.findById(comuna.getIdComuna())).thenReturn(Optional.of(comuna));
        when(usuarioRepository.save(usuarioEntity)).thenReturn(usuarioGuardado);
        when(usuarioMapper.toDTO(usuarioGuardado)).thenReturn(usuarioDTO);

        UsuarioDTO result = usuarioService.crearUsuario(createDTO);

        assertNotNull(result);
        assertEquals(usuarioDTO.getIdUsuario(), result.getIdUsuario());
        assertEquals("Metropolitana", result.getNombreRegion());
        assertEquals("Santiago", result.getNombreComuna());

        verify(usuarioMapper).toEntity(createDTO);
        verify(regionRepository).findById(region.getIdRegion());
        verify(comunaRepository).findById(comuna.getIdComuna());
        verify(usuarioRepository).save(usuarioEntity);
        verify(usuarioMapper).toDTO(usuarioGuardado);
    }
}