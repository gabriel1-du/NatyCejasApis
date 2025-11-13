package com.example.InventarioApi.DTO.BoletaAndBoletaDetallesDtos;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Component
public class DetalleBoletaDTO {

    @JsonProperty("id_boleta")
    @JsonAlias({"idBoleta"})
    private Integer idBoleta;

    @JsonProperty("id_producto")
    @JsonAlias({"idProducto"})
    @NotNull(message = "idProducto es obligatorio")
    @Min(value = 1, message = "idProducto debe ser >= 1")
    private Integer idProducto;

    @JsonProperty("cantidad")
    @JsonAlias({"cantidad"})
    @NotNull(message = "cantidad es obligatoria")
    @Min(value = 1, message = "cantidad debe ser >= 1")
    private Integer cantidad;

    @JsonProperty("precio_unitario")
    @JsonAlias({"precioUnitario"})
    @NotNull(message = "precioUnitario es obligatorio")
    @Min(value = 1, message = "precioUnitario debe ser >= 1")
    private Integer precioUnitario;

}
