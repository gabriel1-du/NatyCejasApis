package com.gateway.jwt.service;

import com.gateway.jwt.dto.*;
import com.gateway.jwt.model.Usuario;
import com.gateway.jwt.repository.UsuarioRepository;
import com.gateway.jwt.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.*;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UsuarioRepository usuarioRepository;
    private final AuthenticationManager authManager;
    private final JwtUtil jwtUtil;

    public AuthResponse login(LoginRequest request) {
        String emailInput = request.getEmail() != null ? request.getEmail() : request.getCorreo();
        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(emailInput, request.getContrasena()));

        Usuario usuario = usuarioRepository.findByEmail(emailInput)
                .orElseThrow();

        String rol = Boolean.TRUE.equals(usuario.getAdmin()) ? "admin" : "user";
        String token = jwtUtil.generateToken(usuario.getEmail(), rol);
        return new AuthResponse(token);
    }
}
