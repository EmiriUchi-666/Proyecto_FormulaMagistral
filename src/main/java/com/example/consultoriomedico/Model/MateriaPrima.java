package com.example.consultoriomedico.Model;

import java.math.BigDecimal;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name="materia_prima")
public class MateriaPrima {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_materia_prima")
    private Integer idMateriaPrima;


    private String nombre;


    private String tipo;


    @ManyToOne
    @JoinColumn(name="id_unidad")
    private UnidadMedida unidad;


   @Column(name="stock_minimo")
    private BigDecimal stockMinimo;


    @ManyToOne
    @JoinColumn(name="id_proveedor")
    private Proveedor proveedor;


    private Boolean activo;

}