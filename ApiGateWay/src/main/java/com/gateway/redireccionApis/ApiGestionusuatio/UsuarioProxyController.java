package com.gateway.redireccionApis.ApiGestionusuatio;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

import com.gateway.jwt.service.JwtService;
import org.springframework.beans.factory.annotation.Value;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/proxy/usuarios")
@RequiredArgsConstructor
@Tag(name = "Usuarios Proxy")
public class UsuarioProxyController {

    private final RestTemplate restTemplate;
    private final JwtService jwtService;

    @Value("${services.usuarios.base-url}")
    private String usuariosBaseUrl;

    @Value("${services.usuarios.base-path}")
    private String usuariosBasePath;

    @RequestMapping(value = {"", "/**"}, method = {RequestMethod.GET, RequestMethod.POST})
    @Operation(
        summary = "Proxy Usuarios (público)",
        description = "Alias del Gateway: /api/proxy/usuarios/** → destino: ${services.usuarios.base-path}/** en ${services.usuarios.base-url}.\n" +
                      "Los segmentos del path se mantienen y los headers se propagan."
    )
    public ResponseEntity<?> proxyUsuariosPublic(HttpServletRequest request,
                                                 @RequestBody(required = false) String body,
                                                 @RequestHeader HttpHeaders headers) {
        return handleProxy(request, body, headers);
    }

    @RequestMapping(value = {"", "/**"}, method = {RequestMethod.PUT, RequestMethod.DELETE})
    @Operation(
        summary = "Proxy Usuarios (seguro)",
        description = "PUT/DELETE requieren JWT (rol=admin). Alias: /api/proxy/usuarios/** → ${services.usuarios.base-path}/**"
    )
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<?> proxyUsuariosSecure(HttpServletRequest request,
                                                 @RequestBody(required = false) String body,
                                                 @RequestHeader HttpHeaders headers) {
        return handleProxy(request, body, headers);
    }

    private ResponseEntity<?> handleProxy(HttpServletRequest request, String body, HttpHeaders headers) {
        String originalPath = request.getRequestURI().replace("/api/proxy/usuarios", "");

        String targetUrl = org.springframework.web.util.UriComponentsBuilder
                .fromHttpUrl(usuariosBaseUrl)
                .path(usuariosBasePath)
                .path(originalPath)
                .build(true)
                .toUriString();

        HttpMethod method = HttpMethod.valueOf(request.getMethod());
        System.out.println("➡️ USUARIOS targetUrl: " + targetUrl + "  METHOD: " + method);

        if (method == HttpMethod.DELETE || method == HttpMethod.PUT) {
            String authHeader = headers.getFirst(HttpHeaders.AUTHORIZATION);
            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body("{\"error\": \"Token no presente o inválido\"}");
            }

            String token = authHeader.replace("Bearer ", "");
            String rol = jwtService.extractClaim(token, claims -> {
                String r = claims.get("rol", String.class);
                if (r == null) r = claims.get("role", String.class);
                return r;
            });

            if (!"admin".equalsIgnoreCase(rol)) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body("{\"error\": \"Operación restringida a admin\"}");
            }
        }

        HttpHeaders cleanHeaders = new HttpHeaders();
        headers.forEach((key, value) -> {
            if (!key.equalsIgnoreCase(HttpHeaders.CONTENT_LENGTH)) {
                cleanHeaders.put(key, value);
            }
        });
        cleanHeaders.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<>(body, cleanHeaders);

        try {
            ResponseEntity<String> response = restTemplate.exchange(targetUrl, method, entity, String.class);
            return ResponseEntity.status(response.getStatusCode())
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(response.getBody());
        } catch (HttpClientErrorException | HttpServerErrorException ex) {
            return ResponseEntity.status(ex.getStatusCode())
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(ex.getResponseBodyAsString());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body("{\"error\": \"Error inesperado en el API Gateway\", \"detalle\": \"" + ex.getMessage() + "\"}");
        }
    }

}
