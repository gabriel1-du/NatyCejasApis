package com.natycejas.GestionUsuariosApi.ModelFolder;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "pedido")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pedido {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pedido")
    private Integer idPedido;
    
    @ManyToOne
    @JoinColumn(name = "id_carrito", nullable = false)
    private Carrito carrito;
    
    @Column(name = "fecha", nullable = false)
    private LocalDateTime fecha = LocalDateTime.now();
    
    @Column(name = "total", nullable = false)
    private Integer total;
    
    @Column(name = "estado")
    private String estado = "PAGADO";

    @Column(name = "id_boleta")
    private Integer idBoleta;
}
