package com.natycejas.GestionUsuariosApi.ModelFolder;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "carrito_producto")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarritoProducto {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_carrito_producto")
    private Integer idCarritoProducto;
    
    @ManyToOne
    @JoinColumn(name = "id_carrito", nullable = false)
    private Carrito carrito;
    
    @Column(name = "id_producto", nullable = false)
    private Integer idProducto;
    
    @Column(name = "cantidad", nullable = false)
    private Integer cantidad;
}