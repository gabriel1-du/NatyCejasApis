package com.example.InventarioApi.DTO.BoletaAndBoletaDetallesDtos;

import java.util.List;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Component
public class BoletaDTO {
    
    @JsonProperty("id_pedido")
    @JsonAlias({"idPedido"})
    @NotNull(message = "idPedido es obligatorio")
    private Integer idPedido;

    @JsonProperty("id_usuario")
    @JsonAlias({"idUsuario"})
    @NotNull(message = "idUsuario es obligatorio")
    private Integer idUsuario;

    @JsonProperty("total")
    @NotNull(message = "total es obligatorio")
    private Integer total;

    @JsonProperty("detalles")
    @JsonAlias({"detalles"})
    private List<DetalleBoletaDTO> detalles;
   
}
