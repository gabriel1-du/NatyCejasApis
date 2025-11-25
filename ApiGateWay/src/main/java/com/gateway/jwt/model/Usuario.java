package com.gateway.jwt.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "usuario")
@Data
@ToString(exclude = {"region", "comuna"})
@EqualsAndHashCode(exclude = {"region", "comuna"})
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "region_usuario", referencedColumnName = "id_region")
    private Region region;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comuna_usuario", referencedColumnName = "id_comuna")
    private Comuna comuna;
}
