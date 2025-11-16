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

import com.natycejas.GestionUsuariosApi.DTOFolder.CarritoProductoDtosFolder.CarritoProductoCreateDTO;
import com.natycejas.GestionUsuariosApi.DTOFolder.CarritoProductoDtosFolder.CarritoProductoDTO;
 
import com.natycejas.GestionUsuariosApi.MapperFolder.CarritoProductoMapper;
import com.natycejas.GestionUsuariosApi.ModelFolder.CarritoProducto;
import com.natycejas.GestionUsuariosApi.RepositoryFolder.CarritoProductoRepository;

@ExtendWith(MockitoExtension.class)
class CarritoProductoServiceImplTest {

    @InjectMocks
    private CarritoProductoServiceImpl carritoProductoService;

    @Mock
    private CarritoProductoRepository carritoProductoRepository;

    @Mock
    private CarritoProductoMapper carritoProductoMapper;

    private CarritoProductoCreateDTO createDTO;
    private CarritoProducto entity;
    private CarritoProducto entityGuardado;
    private CarritoProductoDTO dto;

    @BeforeEach
    void setUp() {
        createDTO = new CarritoProductoCreateDTO(5, 100, 2);
        entity = new CarritoProducto();
        entityGuardado = new CarritoProducto();
        entityGuardado.setIdCarritoProducto(7);
        dto = new CarritoProductoDTO(entityGuardado.getIdCarritoProducto(), null, 100, 2);
    }

    @Test
    void crear_deberiaGuardarYRetornarDTO() {
        when(carritoProductoMapper.toEntity(createDTO)).thenReturn(entity);
        when(carritoProductoRepository.save(entity)).thenReturn(entityGuardado);
        when(carritoProductoMapper.toDTO(entityGuardado)).thenReturn(dto);

        CarritoProductoDTO result = carritoProductoService.crear(createDTO);
        assertNotNull(result);
        assertEquals(7, result.getIdCarritoProducto());

        verify(carritoProductoMapper).toEntity(createDTO);
        verify(carritoProductoRepository).save(entity);
        verify(carritoProductoMapper).toDTO(entityGuardado);
    }

    @Test
    void listarTodos_deberiaRetornarListaDTO() {
        when(carritoProductoRepository.findAll()).thenReturn(List.of(entityGuardado));
        when(carritoProductoMapper.toDTO(entityGuardado)).thenReturn(dto);

        List<CarritoProductoDTO> result = carritoProductoService.listarTodos();
        assertEquals(1, result.size());
        assertEquals(7, result.get(0).getIdCarritoProducto());
        verify(carritoProductoRepository).findAll();
        verify(carritoProductoMapper).toDTO(entityGuardado);
    }

    @Test
    void buscarPorId_deberiaRetornarDTO() {
        when(carritoProductoRepository.findById(7)).thenReturn(Optional.of(entityGuardado));
        when(carritoProductoMapper.toDTO(entityGuardado)).thenReturn(dto);

        CarritoProductoDTO result = carritoProductoService.buscarPorId(7);
        assertEquals(7, result.getIdCarritoProducto());
        verify(carritoProductoRepository).findById(7);
        verify(carritoProductoMapper).toDTO(entityGuardado);
    }

    @Test
    void listarPorCarritoId_deberiaRetornarListaDTO() {
        when(carritoProductoRepository.findByCarrito_IdCarrito(1)).thenReturn(List.of(entityGuardado));
        when(carritoProductoMapper.toDTO(entityGuardado)).thenReturn(dto);

        List<CarritoProductoDTO> result = carritoProductoService.listarPorCarritoId(1);
        assertEquals(1, result.size());
        assertEquals(7, result.get(0).getIdCarritoProducto());
        verify(carritoProductoRepository).findByCarrito_IdCarrito(1);
        verify(carritoProductoMapper).toDTO(entityGuardado);
    }
}