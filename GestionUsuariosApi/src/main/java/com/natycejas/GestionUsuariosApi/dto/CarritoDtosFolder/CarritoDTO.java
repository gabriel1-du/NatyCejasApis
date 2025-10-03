package com.natycejas.GestionUsuariosApi.dto.CarritoDtosFolder;

import com.natycejas.GestionUsuariosApi.dto.UsuarioDtosFolder.UsuarioDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarritoDTO {
    private Integer idCarrito;
    private UsuarioDTO usuario;
    private LocalDateTime fechaCreacion;
    private String estado;
}