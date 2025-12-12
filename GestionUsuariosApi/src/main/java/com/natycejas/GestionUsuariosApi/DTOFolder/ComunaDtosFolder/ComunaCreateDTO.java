package com.natycejas.GestionUsuariosApi.DTOFolder.ComunaDtosFolder;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "DTO de creación de Comuna")
public class ComunaCreateDTO {
    @Schema(description = "Nombre de la comuna", example = "Providencia", requiredMode = Schema.RequiredMode.REQUIRED)
    private String nombreComuna;

    @Schema(description = "ID de la región asociada", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer idRegion;
}