package com.natycejas.GestionUsuariosApi.ServiceImpl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.natycejas.GestionUsuariosApi.DTOFolder.CarritoProductoDtosFolder.CarritoProductoCreateDTO;
import com.natycejas.GestionUsuariosApi.DTOFolder.CarritoProductoDtosFolder.CarritoProductoDTO;
import com.natycejas.GestionUsuariosApi.DTOFolder.CarritoProductoDtosFolder.CarritoProductoUpdateDTO;
import com.natycejas.GestionUsuariosApi.ExternalClientFolder.InventarioClient;
import com.natycejas.GestionUsuariosApi.MapperFolder.CarritoProductoMapper;
import com.natycejas.GestionUsuariosApi.ModelFolder.CarritoProducto;
import com.natycejas.GestionUsuariosApi.RepositoryFolder.CarritoProductoRepository;
import com.natycejas.GestionUsuariosApi.Service.CarritoProductoService;

@Service
public class CarritoProductoServiceImpl implements CarritoProductoService{

    @Autowired
    private CarritoProductoRepository carritoProductoRepository;
    
    @Autowired
    private CarritoProductoMapper carritoProductoMapper;


    @Override
    public List<CarritoProductoDTO> listarTodos() {
        List<CarritoProducto> lista = carritoProductoRepository.findAll();
        return lista.stream()
                .map(carritoProductoMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CarritoProductoDTO buscarPorId(Integer id) {
        Optional<CarritoProducto> carritoProductoOpt = carritoProductoRepository.findById(id);
        return carritoProductoOpt.map(carritoProductoMapper::toDTO).orElse(null);
    }

    @Override
    public CarritoProductoDTO crear(CarritoProductoCreateDTO carritoProductoCreateDTO) {
        CarritoProducto carritoProducto = carritoProductoMapper.toEntity(carritoProductoCreateDTO);
        carritoProducto = carritoProductoRepository.save(carritoProducto);
        return carritoProductoMapper.toDTO(carritoProducto);
    }

    @Override
    public CarritoProductoDTO actualizar(Integer id, CarritoProductoUpdateDTO carritoProductoUpdateDTO) {
        Optional<CarritoProducto> carritoProductoOpt = carritoProductoRepository.findById(id);
        if (carritoProductoOpt.isPresent()) {
            CarritoProducto carritoProducto = carritoProductoOpt.get();
            Integer cantidadAnterior = carritoProducto.getCantidad();
            Integer cantidadNueva = carritoProductoUpdateDTO.getCantidad();

            carritoProductoMapper.updateEntityFromDTO(carritoProductoUpdateDTO, carritoProducto);


            carritoProducto = carritoProductoRepository.save(carritoProducto);
            return carritoProductoMapper.toDTO(carritoProducto);
        } else {
            return null;
        }
    }

    @Override
    public void eliminar(Integer id) {
        carritoProductoRepository.deleteById(id);
    }
}
