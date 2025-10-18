package com.natycejas.GestionUsuariosApi.DTOFolder.CarritoDtosFolder;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

import com.natycejas.GestionUsuariosApi.DTOFolder.UsuarioDtosFolder.UsuarioDTO;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarritoDTO {
    private Integer idCarrito;
    private UsuarioDTO usuario;
    private LocalDateTime fechaCreacion;
    private String estado;
}