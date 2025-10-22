package com.natycejas.GestionUsuariosApi.DTOFolder.RegionDtosFolder;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "DTO de actualización de Región")
public class RegionUpdateDTO {
    @Schema(description = "Nombre de la región", example = "Región del Biobío")
    private String nombreRegion;
}