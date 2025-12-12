package com.natycejas.GestionUsuariosApi.DTOFolder.RegionDtosFolder;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "DTO de creación de Región")
public class RegionCreateDTO {
    @Schema(description = "Nombre de la región", example = "Región de Valparaíso", requiredMode = Schema.RequiredMode.REQUIRED)
    private String nombreRegion;
}