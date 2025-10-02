package com.example.InventarioApi.Controller.ServicioControllerFolder;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.InventarioApi.DTO.ServicioDTOs.CreateServicioDTO;
import com.example.InventarioApi.DTO.ServicioDTOs.ServicioDTO;
import com.example.InventarioApi.DTO.ServicioDTOs.UpdateServicioDTO;
import com.example.InventarioApi.ServiceImpl.ServicioServiceImplsFolder.ServicioServiceImpl;

@Controller
@RestController
@RequestMapping("inventario/servicios")//URL acceso
public class ServicioController {

    @Autowired
    private ServicioServiceImpl servicioServiceImpl; //Inyeccion de servicio impl
    

    @GetMapping
    public List<ServicioDTO> obtenerTodosController() {//Metodo para traer todos los metods (GET)
        return servicioServiceImpl.obtenerTodos();
    }

    @GetMapping("/{id}")//URL
    public ResponseEntity<ServicioDTO> obtenerPorIdController(@PathVariable Integer id) {//Obtener por id al servicio por id (GET)
        try {
            return ResponseEntity.ok(servicioServiceImpl.obtenerPorId(id));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ServicioDTO guardarController(@RequestBody CreateServicioDTO dto) {//Guardar Servicio con el DTO de crear
        return servicioServiceImpl.guardar(dto);
    }


    @PutMapping("/{id}")//Ingresar id URL
    public ResponseEntity<ServicioDTO> actualizarController(@PathVariable Integer id, @RequestBody UpdateServicioDTO dto) { //Metodo para actullizar (PUT
        try {
            return ResponseEntity.ok(servicioServiceImpl.actualizar(id, dto));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")//Ingresar id URl
    public ResponseEntity<Void> eliminarController(@PathVariable Integer id) {//Eliminar id 
        try {
            servicioServiceImpl.eliminar(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }










}
