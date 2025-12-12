package com.natycejas.GestionUsuariosApi.DTOFolder.ComunaDtosFolder;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "DTO de actualización de Comuna")
public class ComunaUpdateDTO {
    @Schema(description = "Nombre de la comuna", example = "Las Condes")
    private String nombreComuna;

    @Schema(description = "ID de la región asociada", example = "1")
    private Integer idRegion;
}