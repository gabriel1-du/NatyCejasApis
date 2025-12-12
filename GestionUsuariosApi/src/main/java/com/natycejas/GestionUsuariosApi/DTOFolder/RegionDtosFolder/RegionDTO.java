package com.natycejas.GestionUsuariosApi.DTOFolder.RegionDtosFolder;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "DTO de salida para Región")
public class RegionDTO {
    @Schema(description = "Identificador de la región", example = "1")
    private Integer idRegion;

    @Schema(description = "Nombre de la región", example = "Región Metropolitana")
    private String nombreRegion;
}