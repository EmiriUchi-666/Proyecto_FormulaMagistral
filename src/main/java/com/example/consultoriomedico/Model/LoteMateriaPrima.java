package com.example.consultoriomedico.Model;


import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;


@Entity
@Data
@Table(name="lote_materia_prima")
public class LoteMateriaPrima {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_lote")
    private Integer idLote;



    @ManyToOne
    @JoinColumn(name="id_materia_prima")
    private MateriaPrima materiaPrima;



    @Column(name="codigo_lote")
    private String codigoLote;



    @Column(name="fecha_ingreso")
    private LocalDate fechaIngreso;



    @Column(name="fecha_vencimiento")
    private LocalDate fechaVencimiento;



    @Column(name="cantidad_inicial")
    private BigDecimal cantidadInicial;



    @Column(name="cantidad_actual")
    private BigDecimal cantidadActual;



    @Column(name="costo_unitario")
    private BigDecimal costoUnitario;

}