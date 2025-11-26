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
@RequestMapping("/api/proxy/carritos")
@RequiredArgsConstructor
@Tag(name = "Carritos Proxy", description = "Gateway: /api/proxy/carritos → Backend: ${services.carritos.base-url}${services.carritos.base-path}")
public class CarritosProxyController {

    private final RestTemplate restTemplate;
    private final JwtService jwtService;

    @Value("${services.carritos.base-url}")
    private String carritosBaseUrl;

    @Value("${services.carritos.base-path}")
    private String carritosBasePath;

    @Value("${services.carritos.carrito-producto-base-path:/api/carrito-producto}")
    private String carritoProductoBasePath;

    @RequestMapping(value = {"", "/**"}, method = {RequestMethod.GET, RequestMethod.POST})
    @Operation(
        summary = "Proxy Carritos (público)",
        description = "Alias del Gateway y destino en Backend:\n" +
                      "- /api/proxy/carritos/** → ${services.carritos.base-path}/** en ${services.carritos.base-url}\n" +
                      "Métodos permitidos: GET, POST."
    )
    public ResponseEntity<?> proxyCarritosPublic(HttpServletRequest request,
                                                 @RequestBody(required = false) String body,
                                                 @RequestHeader HttpHeaders headers) {
        return handleProxy(request, body, headers);
    }

    @RequestMapping(value = {"", "/**"}, method = {RequestMethod.PUT, RequestMethod.DELETE})
    @Operation(
        summary = "Proxy Carritos (seguro)",
        description = "PUT/DELETE requieren JWT (rol=admin). Alias:\n" +
                      "- /api/proxy/carritos/** → ${services.carritos.base-path}/**"
    )
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<?> proxyCarritosSecure(HttpServletRequest request,
                                                 @RequestBody(required = false) String body,
                                                 @RequestHeader HttpHeaders headers) {
        return handleProxy(request, body, headers);
    }

    private ResponseEntity<?> handleProxy(HttpServletRequest request, String body, HttpHeaders headers) {
        String originalPath = request.getRequestURI().replace("/api/proxy/carritos", "");

        String effectiveBasePath;
        String pathRemainder;
        if (originalPath.startsWith("/carrito-producto")) {
            effectiveBasePath = carritoProductoBasePath;
            pathRemainder = originalPath.replaceFirst("^/carrito-producto", "");
        } else {
            effectiveBasePath = carritosBasePath;
            pathRemainder = originalPath;
        }

        String targetUrl = org.springframework.web.util.UriComponentsBuilder
                .fromHttpUrl(carritosBaseUrl)
                .path(effectiveBasePath)
                .path(pathRemainder)
                .build(true)
                .toUriString();

        HttpMethod method = HttpMethod.valueOf(request.getMethod());
        System.out.println("➡️ CARITOS targetUrl: " + targetUrl + "  METHOD: " + method);

        if (method == HttpMethod.DELETE || method == HttpMethod.PUT) {
            if (!originalPath.startsWith("/carrito-producto")) {
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
        }

        HttpHeaders cleanHeaders = new HttpHeaders();
        headers.forEach((key, value) -> {
            if (!key.equalsIgnoreCase(HttpHeaders.CONTENT_LENGTH)) {
                if (method == HttpMethod.GET && key.equalsIgnoreCase(HttpHeaders.AUTHORIZATION)) {
                    return; // no propagar Authorization en GET
                }
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
