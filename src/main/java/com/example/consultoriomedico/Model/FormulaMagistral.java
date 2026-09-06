package com.example.consultoriomedico.Model;


import java.math.BigDecimal;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Data
@Table(name="formula_magistral")
public class FormulaMagistral {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_formula")
    private Integer idFormula;



    private String nombre;



    @ManyToOne
    @JoinColumn(name="id_categoria")
    private CategoriaFormula categoria;



    private String presentacion;



    private String instrucciones;



    @Column(name="requiere_receta")
    private Boolean requiereReceta;



    @Column(name="precio_venta")
    private BigDecimal precioVenta;


    private Boolean activo;

}