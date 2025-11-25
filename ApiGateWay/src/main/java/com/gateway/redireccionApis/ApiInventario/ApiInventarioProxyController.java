package com.gateway.redireccionApis.ApiInventario;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.gateway.jwt.service.JwtService;
import org.springframework.beans.factory.annotation.Value;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/api/proxy/inventario")
@RequiredArgsConstructor
@Tag(name = "Inventario Proxy")
public class ApiInventarioProxyController {

    private final RestTemplate restTemplate;
    private final JwtService jwtService;

    @Value("${services.inventario.base-url}")
    private String inventarioBaseUrl;

    @Value("${services.inventario.base-path:}")
    private String inventarioBasePath;

    @Value("${services.inventario.boleta-base-path:/api/boletas}")
    private String boletaBasePath;

    @Value("${services.inventario.boleta-detalle-base-path:/api/detalleboleta}")
    private String boletaDetalleBasePath;

    @RequestMapping(value = {"", "/**"}, method = {RequestMethod.GET, RequestMethod.POST})
    @Operation(
        summary = "Proxy Inventario (público)",
        description = "Aliases del Gateway y su destino en ApiInventario:\n" +
                      "- /api/proxy/inventario/boleta → /api/boletas\n" +
                      "- /api/proxy/inventario/boleta-detalle → /api/detalleboleta\n" +
                      "- /api/proxy/inventario/** → /inventario/**\n" +
                      "Los segmentos adicionales del path se concatenan al destino correspondiente."
    )
    public ResponseEntity<?> proxyInventarioPublic(HttpServletRequest request,
                                                  @RequestBody(required = false) String body,
                                                  @RequestHeader HttpHeaders headers) {
        return handleProxy(request, body, headers);
    }

    @RequestMapping(value = {"", "/**"}, method = {RequestMethod.PUT, RequestMethod.DELETE})
    @Operation(
        summary = "Proxy Inventario (seguro)",
        description = "PUT/DELETE requieren JWT (rol=admin). Aliases y destino:\n" +
                      "- /api/proxy/inventario/boleta → /api/boletas\n" +
                      "- /api/proxy/inventario/boleta-detalle → /api/detalleboleta\n" +
                      "- /api/proxy/inventario/** → /inventario/**"
    )
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<?> proxyInventarioSecure(HttpServletRequest request,
                                                  @RequestBody(required = false) String body,
                                                  @RequestHeader HttpHeaders headers) {
        return handleProxy(request, body, headers);
    }

    @GetMapping("/boleta")
    @Operation(summary = "Inventario: Boletas (GET)", description = "Gateway: /api/proxy/inventario/boleta → Backend: /api/boletas")
    public ResponseEntity<?> getBoletas(HttpServletRequest request,
                                        @RequestHeader HttpHeaders headers) {
        return handleProxy(request, null, headers);
    }

    @GetMapping("/boleta-detalle")
    @Operation(summary = "Inventario: Detalle Boleta (GET)", description = "Gateway: /api/proxy/inventario/boleta-detalle → Backend: /api/detalleboleta")
    public ResponseEntity<?> getBoletasDetalle(HttpServletRequest request,
                                               @RequestHeader HttpHeaders headers) {
        return handleProxy(request, null, headers);
    }

    @GetMapping("/productos")
    @Operation(summary = "Inventario: Productos (GET)", description = "Gateway: /api/proxy/inventario/productos → Backend: /inventario/productos")
    public ResponseEntity<?> getProductos(HttpServletRequest request,
                                          @RequestHeader HttpHeaders headers) {
        return handleProxy(request, null, headers);
    }

    @GetMapping("/producto/{id}")
    @Operation(summary = "Inventario: Producto por ID (GET)", description = "Gateway: /api/proxy/inventario/producto/{id} → Backend: /inventario/producto/{id}")
    public ResponseEntity<?> getProductoById(HttpServletRequest request,
                                             @RequestHeader HttpHeaders headers) {
        return handleProxy(request, null, headers);
    }

    private ResponseEntity<?> handleProxy(HttpServletRequest request, String body, HttpHeaders headers) {
        String originalPath = request.getRequestURI().replace("/api/proxy/inventario", "");
        String effectiveBasePath;
        String pathRemainder;
        if (originalPath.startsWith("/boleta")) {
            effectiveBasePath = boletaBasePath;
            pathRemainder = originalPath.replaceFirst("^/boleta", "");
        } else if (originalPath.startsWith("/boleta-detalle")) {
            effectiveBasePath = boletaDetalleBasePath;
            pathRemainder = originalPath.replaceFirst("^/boleta-detalle", "");
        } else {
            effectiveBasePath = (inventarioBasePath == null ? "" : inventarioBasePath);
            pathRemainder = originalPath;
        }
        String targetUrl = UriComponentsBuilder
                .fromHttpUrl(inventarioBaseUrl)
                .path(effectiveBasePath)
                .path(pathRemainder)
                .build(true)
                .toUriString();
        System.out.println("➡️ INVENTARIO targetUrl: " + targetUrl + "  METHOD: " + request.getMethod());
        HttpMethod method = HttpMethod.valueOf(request.getMethod());

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
            System.out.println("🔐 INVENTARIO auth: rol=" + rol);

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
