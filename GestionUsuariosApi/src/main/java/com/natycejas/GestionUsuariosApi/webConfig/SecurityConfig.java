package com.natycejas.GestionUsuariosApi.webConfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration //Archivo de configuracion de seguridad
public class SecurityConfig {

    @Bean //MEtodo para configurar las reglas de seguridad HTTP
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable()) // Deshabilitar CSRF para simplificar (no recomendado para produccion)
            .authorizeHttpRequests(requests -> requests.
                    anyRequest().permitAll());
        return http.build();
    }

    @Bean //Metodo para encpitar contraseñas
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }



}
