package com.example.InventarioApi.ServiceImpl.ServicioServiceImplsFolder;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.InventarioApi.DTO.ServicioDTOs.CreateServicioDTO;
import com.example.InventarioApi.DTO.ServicioDTOs.ServicioDTO;
import com.example.InventarioApi.DTO.ServicioDTOs.ServicioMapper;
import com.example.InventarioApi.DTO.ServicioDTOs.UpdateServicioDTO;
import com.example.InventarioApi.Model.ServiciosModelsFolder.CategoriaServicio;
import com.example.InventarioApi.Model.ServiciosModelsFolder.Servicio;
import com.example.InventarioApi.Repository.ServiciosRepositoriesFolder.CategoriaServicioRepository;
import com.example.InventarioApi.Repository.ServiciosRepositoriesFolder.ServicioRepository;
import com.example.InventarioApi.Service.ServicioServiceFolder.ServicioService;

@Service
public class ServicioServiceImpl implements ServicioService{

    @Autowired
    private ServicioRepository servicioRepository; //inyeccion repo servicio

    @Autowired
    private CategoriaServicioRepository categoriaServicioRepository; //inyeccion categoria repo


     public ServicioServiceImpl(ServicioRepository servicioRepository, CategoriaServicioRepository categoriaServicioRepository) {
        this.servicioRepository = servicioRepository;
        this.categoriaServicioRepository = categoriaServicioRepository;
    }

    @Override
    public List<ServicioDTO> obtenerTodos() { //Mostrar todos los Servicios (GET)
        return servicioRepository.findAll()
                .stream()
                .map(ServicioMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ServicioDTO obtenerPorId(Integer id) { //Mostrar servicio por su id (GET)
        Servicio servicio = servicioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Servicio no encontrado con id: " + id));
        return ServicioMapper.toDTO(servicio);
    }

    @Override
    public ServicioDTO guardar(CreateServicioDTO dto) { //Metodo para un servicio , devuelve DTO (POST)

        Servicio servicio = new Servicio(); // Se crea objeto
        servicio.setNombre_servicio(dto.getNombre_servicio());
        servicio.setDescripcion(dto.getDescripcion());
        servicio.setPrecio(Integer.valueOf(dto.getPrecio())); // cuidado: en tu DTO está como String

        CategoriaServicio categoria = categoriaServicioRepository.findById(dto.getId_categoria_servicio())
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada con id: " + dto.getId_categoria_servicio()));
        servicio.setCategoriaServicio(categoria);

        Servicio guardado = servicioRepository.save(servicio);
        return ServicioMapper.toDTO(guardado);
    }

    @Override
    public ServicioDTO actualizar(Integer id, UpdateServicioDTO dto) { //Metodo para actualizar devuelve DTO (PUT)
        Servicio servicio = servicioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Servicio no encontrado con id: " + id));

        if (dto.getNombre_servicio() != null) servicio.setNombre_servicio(dto.getNombre_servicio());
        if (dto.getDescripcion() != null) servicio.setDescripcion(dto.getDescripcion());
        if (dto.getPrecio() != null) servicio.setPrecio(dto.getPrecio());

        if (dto.getId_categoria_servicio() != null) {
            CategoriaServicio categoria = categoriaServicioRepository.findById(dto.getId_categoria_servicio())
                    .orElseThrow(() -> new RuntimeException("Categoría no encontrada con id: " + dto.getId_categoria_servicio()));
            servicio.setCategoriaServicio(categoria);
        }

        Servicio actualizado = servicioRepository.save(servicio);
        return ServicioMapper.toDTO(actualizado);
    }

    @Override
    public void eliminar(Integer id) { //Metodo para eliminar por us id (DELETE)
        if (!servicioRepository.existsById(id)) {
            throw new RuntimeException("Servicio no encontrado con id: " + id);
        }
        servicioRepository.deleteById(id);
    }
    




}
