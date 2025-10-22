package com.natycejas.GestionUsuariosApi.ModelFolder;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "usuario")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Integer idUsuario;
    
    @Column(name = "nombre", nullable = false)
    private String nombre;
    
    @Column(name = "apellido", nullable = false)
    private String apellido;
    
    @Column(name = "rut", nullable = false, unique = true)
    private String rut;
    
    @Column(name = "email", nullable = false, unique = true)
    private String email;
    
    @Column(name = "telefono")
    private String telefono;
    
    @Column(name = "direccion")
    private String direccion;
    
    @Column(name = "contrasena", nullable = false)
    private String contrasena;
    
    @Column(name = "admin")
    private Boolean admin = false;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "region_usuario", referencedColumnName = "id_region")
    private Region region;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "comuna_usuario", referencedColumnName = "id_comuna")
    private Comuna comuna;
}