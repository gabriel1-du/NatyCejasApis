package com.gateway.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.servers.Server;
import io.swagger.v3.oas.models.ExternalDocumentation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springdoc.core.models.GroupedOpenApi;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API Gateway")
                        .description("Documentación de endpoints del API Gateway")
                        .license(new License().name("MIT")))
                .addSecurityItem(new SecurityRequirement().addList("bearerAuth"))
                .components(new Components()
                        .addSecuritySchemes("bearerAuth",
                                new SecurityScheme()
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")))
                .servers(java.util.List.of(
                        new Server().url("http://localhost:8888").description("Gateway"),
                        new Server().url("http://localhost:8095/api/usuarios").description("API Usuarios base"),
                        new Server().url("http://localhost:8097").description("API Inventario base")
                ))
                .externalDocs(new ExternalDocumentation()
                        .description("Swagger backend: Usuarios e Inventario")
                        .url("http://localhost:8095/swagger-ui/index.html"));
    }

    @Bean
    public GroupedOpenApi authGroup() {
        return GroupedOpenApi.builder()
                .group("auth")
                .pathsToMatch("/api/auth/**")
                .build();
    }

    @Bean
    public GroupedOpenApi usuariosGroup() {
        return GroupedOpenApi.builder()
                .group("usuarios")
                .pathsToMatch("/api/proxy/usuarios/**")
                .build();
    }

    @Bean
    public GroupedOpenApi inventarioGroup() {
        return GroupedOpenApi.builder()
                .group("inventario")
                .pathsToMatch("/api/proxy/inventario/**")
                .build();
    }
}
