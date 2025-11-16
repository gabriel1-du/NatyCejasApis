package com.natycejas.GestionUsuariosApi.ServiceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.natycejas.GestionUsuariosApi.DTOFolder.CarritoDtosFolder.CarritoCreateDTO;
import com.natycejas.GestionUsuariosApi.DTOFolder.CarritoDtosFolder.CarritoDTO;
import com.natycejas.GestionUsuariosApi.DTOFolder.CarritoDtosFolder.CarritoUpdateDTO;
import com.natycejas.GestionUsuariosApi.MapperFolder.CarritoMapper;
import com.natycejas.GestionUsuariosApi.ModelFolder.Carrito;
import com.natycejas.GestionUsuariosApi.RepositoryFolder.CarritoRepository;
import com.natycejas.GestionUsuariosApi.Service.CarritoService;

@Service
public class CarritoServiceImpl implements CarritoService {

    @Autowired
    private CarritoRepository carritoRepository;

    @Autowired
    private CarritoMapper carritoMapper;

    
    public List<CarritoDTO> listarTodos() {
        List<Carrito> carritos = carritoRepository.findAll();
        return carritos.stream()
                .map(carritoMapper::toDTO)
                .collect(Collectors.toList());
    }

    
    public CarritoDTO buscarPorId(Integer id) {
        Carrito carrito = carritoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Carrito no encontrado con ID: " + id));
        return carritoMapper.toDTO(carrito);
    }

    @Override
    public CarritoDTO buscarPorUsuarioId(Integer idUsuario) {
        Carrito carrito = carritoRepository.findByUsuarioId(idUsuario)
                .orElseThrow(() -> new RuntimeException("Carrito no encontrado para usuario con ID: " + idUsuario));
        return carritoMapper.toDTO(carrito);
    }

    
    public CarritoDTO crear(CarritoCreateDTO carritoCreateDTO) {
        Carrito carrito = carritoMapper.toEntity(carritoCreateDTO);
        Carrito carritoGuardado = carritoRepository.save(carrito);
        return carritoMapper.toDTO(carritoGuardado);
    }


    public CarritoDTO actualizar(Integer id, CarritoUpdateDTO carritoUpdateDTO) {
        Carrito carritoExistente = carritoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Carrito no encontrado con ID: " + id));

        carritoMapper.updateEntityFromDTO(carritoUpdateDTO, carritoExistente);

        Carrito carritoActualizado = carritoRepository.save(carritoExistente);
        return carritoMapper.toDTO(carritoActualizado);
    }

    
    public void eliminar(Integer id) {
        if (!carritoRepository.existsById(id)) {
            throw new RuntimeException("No se puede eliminar. Carrito no encontrado con ID: " + id);
        }
        carritoRepository.deleteById(id);
    }
}



