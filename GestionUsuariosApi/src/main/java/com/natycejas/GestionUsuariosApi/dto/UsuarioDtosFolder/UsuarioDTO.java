package com.natycejas.GestionUsuariosApi.dto.UsuarioDtosFolder;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDTO {
    private Integer idUsuario;
    private String nombre;
    private String apellido;
    private String rut;
    private String email;
    private String telefono;
    private String direccion;
    private Boolean admin;
}