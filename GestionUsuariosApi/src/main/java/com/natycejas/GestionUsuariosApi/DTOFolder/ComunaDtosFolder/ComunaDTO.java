package com.natycejas.GestionUsuariosApi.DTOFolder.ComunaDtosFolder;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "DTO de salida para Comuna")
public class ComunaDTO {
    @Schema(description = "Identificador de la comuna", example = "10")
    private Integer idComuna;

    @Schema(description = "Nombre de la comuna", example = "Santiago")
    private String nombreComuna;

    @Schema(description = "Identificador de la región asociada", example = "1")
    private Integer idRegion;

    @Schema(description = "Nombre de la región asociada", example = "Región Metropolitana")
    private String nombreRegion;
}