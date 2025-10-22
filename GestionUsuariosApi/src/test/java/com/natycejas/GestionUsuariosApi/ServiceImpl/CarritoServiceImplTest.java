package com.natycejas.GestionUsuariosApi.ServiceImpl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.natycejas.GestionUsuariosApi.DTOFolder.CarritoDtosFolder.CarritoCreateDTO;
import com.natycejas.GestionUsuariosApi.DTOFolder.CarritoDtosFolder.CarritoDTO;
import com.natycejas.GestionUsuariosApi.MapperFolder.CarritoMapper;
import com.natycejas.GestionUsuariosApi.ModelFolder.Carrito;
import com.natycejas.GestionUsuariosApi.RepositoryFolder.CarritoRepository;

@ExtendWith(MockitoExtension.class)
class CarritoServiceImplTest {

    @InjectMocks
    private CarritoServiceImpl carritoService;

    @Mock
    private CarritoRepository carritoRepository;

    @Mock
    private CarritoMapper carritoMapper;

    private CarritoCreateDTO createDTO;
    private Carrito carritoEntity;
    private Carrito carritoGuardado;
    private CarritoDTO carritoDTO;

    @BeforeEach
    void setUp() {
        createDTO = new CarritoCreateDTO(10);

        carritoEntity = new Carrito();
        carritoGuardado = new Carrito();
        carritoGuardado.setIdCarrito(5);

        carritoDTO = new CarritoDTO(
            carritoGuardado.getIdCarrito(),
            null,
            carritoGuardado.getFechaCreacion(),
            carritoGuardado.getEstado()
        );
    }

    @Test
    void crear_deberiaGuardarYRetornarDTO() {
        when(carritoMapper.toEntity(createDTO)).thenReturn(carritoEntity);
        when(carritoRepository.save(carritoEntity)).thenReturn(carritoGuardado);
        when(carritoMapper.toDTO(carritoGuardado)).thenReturn(carritoDTO);

        CarritoDTO result = carritoService.crear(createDTO);

        assertNotNull(result);
        assertEquals(5, result.getIdCarrito());
        verify(carritoMapper).toEntity(createDTO);
        verify(carritoRepository).save(carritoEntity);
        verify(carritoMapper).toDTO(carritoGuardado);
    }

    @Test
    void buscarPorId_deberiaRetornarDTO() {
        when(carritoRepository.findById(5)).thenReturn(Optional.of(carritoGuardado));
        when(carritoMapper.toDTO(carritoGuardado)).thenReturn(carritoDTO);

        CarritoDTO result = carritoService.buscarPorId(5);
        assertEquals(5, result.getIdCarrito());
        verify(carritoRepository).findById(5);
        verify(carritoMapper).toDTO(carritoGuardado);
    }

    @Test
    void listarTodos_deberiaRetornarListaDTO() {
        when(carritoRepository.findAll()).thenReturn(List.of(carritoGuardado));
        when(carritoMapper.toDTO(carritoGuardado)).thenReturn(carritoDTO);

        List<CarritoDTO> result = carritoService.listarTodos();
        assertEquals(1, result.size());
        assertEquals(5, result.get(0).getIdCarrito());
        verify(carritoRepository).findAll();
        verify(carritoMapper, times(1)).toDTO(carritoGuardado);
    }
}