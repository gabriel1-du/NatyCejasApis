package com.natycejas.GestionUsuariosApi.ConfigFolder;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.natycejas.GestionUsuariosApi.ExternalClientFolder.InventarioClient;

@Configuration
public class ExternalClientsConfig {

    @Bean
    public InventarioClient inventarioClient(
            @Value("${inventario.api.base-url}") String baseUrl,
            @Value("${inventario.api.connect-timeout-ms:5000}") long connectTimeoutMs) {
        return InventarioClient.builder()
                .baseUrl(baseUrl)
                .connectTimeoutMillis(connectTimeoutMs)
                .build();
    }
}