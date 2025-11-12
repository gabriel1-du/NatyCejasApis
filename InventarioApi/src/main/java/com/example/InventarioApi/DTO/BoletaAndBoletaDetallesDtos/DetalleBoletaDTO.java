package com.example.InventarioApi.DTO.BoletaAndBoletaDetallesDtos;

import com.example.InventarioApi.Model.BoletaModelsFolder.Boleta;

import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DetalleBoletaDTO {

    private Integer idProducto;
    private Integer cantidad;
    private Integer precioUnitario;

}
