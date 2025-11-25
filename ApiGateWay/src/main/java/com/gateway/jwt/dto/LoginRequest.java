package com.gateway.jwt.dto;

import lombok.Data;

@Data
public class LoginRequest {
    private String email;
    private String correo;
    private String contrasena;
}