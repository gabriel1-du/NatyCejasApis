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

    @Bean
    public GroupedOpenApi carritosGroup() {
        return GroupedOpenApi.builder()
                .group("carritos")
                .pathsToMatch("/api/proxy/carritos/**")
                .build();
    }

    @Bean
    public GroupedOpenApi regionesGroup() {
        return GroupedOpenApi.builder()
                .group("regiones")
                .pathsToMatch("/api/proxy/regiones/**")
                .build();
    }

    @Bean
    public GroupedOpenApi comunasGroup() {
        return GroupedOpenApi.builder()
                .group("comunas")
                .pathsToMatch("/api/proxy/comunas/**")
                .build();
    }

    @Bean
    public GroupedOpenApi pedidosGroup() {
        return GroupedOpenApi.builder()
                .group("pedidos")
                .pathsToMatch("/api/proxy/pedidos/**")
                .build();
    }

    @Bean
    public GroupedOpenApi inventarioBoletaGroup() {
        return GroupedOpenApi.builder()
                .group("inventario-boleta")
                .pathsToMatch("/api/proxy/inventario/boleta/**")
                .build();
    }

    @Bean
    public GroupedOpenApi inventarioBoletaDetalleGroup() {
        return GroupedOpenApi.builder()
                .group("inventario-boleta-detalle")
                .pathsToMatch("/api/proxy/inventario/boleta-detalle/**")
                .build();
    }

    @Bean
    public GroupedOpenApi inventarioProductosGroup() {
        return GroupedOpenApi.builder()
                .group("inventario-productos")
                .pathsToMatch("/api/proxy/inventario/productos/**")
                .build();
    }

    @Bean
    public GroupedOpenApi inventarioMarcasGroup() {
        return GroupedOpenApi.builder()
                .group("inventario-marcas")
                .pathsToMatch("/api/proxy/inventario/marcas/**")
                .build();
    }

    @Bean
    public GroupedOpenApi inventarioServiciosGroup() {
        return GroupedOpenApi.builder()
                .group("inventario-servicios")
                .pathsToMatch("/api/proxy/inventario/servicios/**")
                .build();
    }

    @Bean
    public GroupedOpenApi inventarioCitasGroup() {
        return GroupedOpenApi.builder()
                .group("inventario-citas")
                .pathsToMatch("/api/proxy/inventario/citas/**")
                .build();
    }
}
