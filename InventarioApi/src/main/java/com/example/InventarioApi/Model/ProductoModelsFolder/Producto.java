package com.example.InventarioApi.Model.ProductoModelsFolder;

import com.example.InventarioApi.Model.Marca;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
@Table(name = "PRODUCTO")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_producto; //atributo id

    @Column(name = "nombre_producto", nullable = false)
    private String nombre_producto;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "precio", nullable = false)
    private Integer precio;

    @Column(name = "stock", nullable = false)
    private Integer stock;

    @ManyToOne
    @JoinColumn(name = "id_marca", nullable = false)
    private Marca marca; //Atributo relacion a marca

    @ManyToOne
    @JoinColumn(name = "categoria")
    private CategoriaProducto categoria; // Nueva relación con CATEGORIA_PRODUCTO

    @Column(name= "foto_url") //Atributo para subir la foto
    private String foto_url;


}
