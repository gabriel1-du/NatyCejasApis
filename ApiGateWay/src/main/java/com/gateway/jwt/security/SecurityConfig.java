package com.gateway.jwt.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.*;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


import com.gateway.redireccionApis.ApiInventario.ApiInventarioPublicRoutes;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.http.HttpMethod; // Asegúrate de importar esto arriba

import static com.gateway.jwt.security.PublicRoutes.*; //importa las rutas publicas de jwt
import static com.gateway.redireccionApis.ApiGestionusuatio.UsuarioPublicRoutes.USUARIO_PUBLIC_GET; //Importa las rutas publicas de usuario
import static com.gateway.redireccionApis.ApiGestionusuatio.RegionesPublicRoutes.REGIONES_PUBLIC_GET;
import static com.gateway.redireccionApis.ApiGestionusuatio.PedidosPublicRoutes.PEDIDOS_PUBLIC_GET;
import static com.gateway.redireccionApis.ApiGestionusuatio.ComunasPublicRoutes.COMUNAS_PUBLIC_GET;
import static com.gateway.redireccionApis.ApiGestionusuatio.CarritosPublicRoutes.CARRITOS_PUBLIC_GET;


@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtFilter jwtFilter;
    private final UserDetailsServiceImpl userDetailsService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http.csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth

                // URL públicas JWT
                .requestMatchers(HttpMethod.POST, PUBLIC_POST).permitAll() // rutas publicas POST de PublicRoutes de JWT
                .requestMatchers(HttpMethod.GET, PUBLIC_GET).permitAll() // rutas publicas GET de PublicRoutes de JWT

                // URL públicas API Gestion
                .requestMatchers(HttpMethod.GET, USUARIO_PUBLIC_GET).permitAll()   // lista pública api GESTION GET
                .requestMatchers(HttpMethod.POST, "/api/proxy/usuarios/**").permitAll()
                .requestMatchers(HttpMethod.GET, REGIONES_PUBLIC_GET).permitAll()
                .requestMatchers(HttpMethod.GET, PEDIDOS_PUBLIC_GET).permitAll()
                .requestMatchers(HttpMethod.POST, "/api/proxy/pedidos/**").permitAll()
                .requestMatchers(HttpMethod.GET, COMUNAS_PUBLIC_GET).permitAll()
                .requestMatchers(HttpMethod.GET, CARRITOS_PUBLIC_GET).permitAll()
                .requestMatchers(HttpMethod.GET, "/api/proxy/carritos/**").permitAll()
                .requestMatchers(HttpMethod.POST, "/api/proxy/carritos/**").permitAll()
                .requestMatchers(HttpMethod.PUT, "/api/proxy/carritos/carrito-producto/**").permitAll()
                .requestMatchers(HttpMethod.DELETE, "/api/proxy/carritos/carrito-producto/**").permitAll()
                

                // — RUTAS PÚBLICAS DE CARRITO (GET) — 
            
                // — RUTAS PÚBLICAS INVENTARIO/BOLETA (GET)
                .requestMatchers(HttpMethod.GET, com.gateway.redireccionApis.ApiInventario.Boleta.BoletaPublicRoutes.BOLETA_PUBLIC_GET).permitAll()
                .requestMatchers(HttpMethod.GET, com.gateway.redireccionApis.ApiInventario.BoletaDetalle.BoletaDetallePublicRoutes.BOLETA_DETALLE_PUBLIC_GET).permitAll()
                // — RUTAS PÚBLICAS INVENTARIO/BOLETA (POST)
                .requestMatchers(HttpMethod.POST, "/api/proxy/inventario/boleta").permitAll()
                .requestMatchers(HttpMethod.POST, "/api/proxy/inventario/boleta/**").permitAll()
                .requestMatchers(HttpMethod.POST, "/api/proxy/inventario/boleta-detalle").permitAll()
                .requestMatchers(HttpMethod.POST, "/api/proxy/inventario/boleta-detalle/**").permitAll()

                // - RUTAS PUBLICAS DE INVENTARIO (GET)
                .requestMatchers(HttpMethod.GET, ApiInventarioPublicRoutes.INVENTARIO_PUBLIC_GET).permitAll()

                // — RUTAS PÚBLICAS SWAGGER/OPENAPI —
                .requestMatchers("/swagger-ui.html").permitAll()
                .requestMatchers("/swagger-ui/**").permitAll()
                .requestMatchers("/v3/api-docs").permitAll()
                .requestMatchers("/v3/api-docs/**").permitAll()




                // Otras URL Token obligatorio
                .anyRequest().authenticated()

            )
            .authenticationProvider(authenticationProvider(passwordEncoder()))
            .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
            .build();
    }


    @Bean
    public AuthenticationManager authenticationManager(AuthenticationProvider provider) {
        return new ProviderManager(provider);
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(BCryptPasswordEncoder passwordEncoder) {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder);
        return provider;
    }
}
