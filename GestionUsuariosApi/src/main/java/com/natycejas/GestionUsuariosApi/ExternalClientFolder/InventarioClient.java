package com.natycejas.GestionUsuariosApi.ExternalClientFolder;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

/**
 * Cliente para el API de Inventario usando patrón Builder.
 */
public class InventarioClient {
    private final String baseUrl;
    private final HttpClient httpClient;

    private InventarioClient(Builder builder) {
        this.baseUrl = builder.baseUrl;
        this.httpClient = HttpClient.newBuilder()
                .connectTimeout(Duration.ofMillis(builder.connectTimeoutMillis))
                .build();
    }

    public static Builder builder() {
        return new Builder();
    }

    /**
     * Resta stock del producto en el API externo.
     *  URL: {baseUrl}/productos/{id}/restar?cantidad={cantidad}
     */
    public void restarStock(Integer idProducto, Integer cantidad) {
        if (idProducto == null || cantidad == null || cantidad <= 0) {
            throw new IllegalArgumentException("idProducto y cantidad deben ser válidos");
        }
        String url = baseUrl + "/productos/" + idProducto + "/restar?cantidad=" + cantidad;
        HttpRequest request = HttpRequest.newBuilder(URI.create(url))
                .header("Accept", "application/json")
                .method("PATCH", HttpRequest.BodyPublishers.noBody())
                .build();
        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            int code = response.statusCode();
            if (code < 200 || code >= 300) {
                throw new RuntimeException("Error al restar stock: HTTP " + code + " - " + response.body());
            }
        } catch (IOException | InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Fallo al llamar API de inventario", e);
        }
    }

    public static class Builder {
        private String baseUrl;
        private long connectTimeoutMillis = 5000;

        public Builder baseUrl(String baseUrl) {
            this.baseUrl = baseUrl;
            return this;
        }

        public Builder connectTimeoutMillis(long connectTimeoutMillis) {
            this.connectTimeoutMillis = connectTimeoutMillis;
            return this;
        }

        public InventarioClient build() {
            if (this.baseUrl == null || this.baseUrl.isBlank()) {
                throw new IllegalStateException("baseUrl es requerido");
            }
            return new InventarioClient(this);
        }
    }
}