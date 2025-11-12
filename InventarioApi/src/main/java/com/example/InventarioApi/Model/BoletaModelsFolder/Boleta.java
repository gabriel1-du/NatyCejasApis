package com.example.InventarioApi.Model.BoletaModelsFolder;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.CascadeType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "boleta")
@NoArgsConstructor
@Data
public class Boleta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_boleta")
    private Integer idBoleta;

    @Column(name = "id_pedido", nullable = false)
    private Integer idPedido;

    @Column(name = "id_usuario", nullable = false)
    private Integer idUsuario;

    @Column(name = "fecha_emision", nullable = false)
    private LocalDateTime fechaEmision;

    @Column(name = "total", nullable = false)
    private Integer total;

    @Column(name = "estado")
    private String estado;

    @OneToMany(mappedBy = "boleta",  orphanRemoval = true)
    private List<DetalleBoleta> detalles; //Foreign key hacia DetalleBoleta

}
