package com.natycejas.GestionUsuariosApi.DTOFolder.UsuarioDtosFolder;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioCreateDTO {
    private String nombre;
    private String apellido;
    private String rut;
    private String email;
    private String telefono;
    private String direccion;
    private String contrasena;
    private Boolean admin = false;
}