package com.example.consultoriomedico.Model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;


@Entity
@Data
@Table(name="movimiento_inventario")
public class MovimientoInventario {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_movimiento")
    private Integer idMovimiento;


    @ManyToOne
    @JoinColumn(name="id_lote")
    private LoteMateriaPrima lote;


    @Column(name="tipo_movimiento")
    private String tipoMovimiento;


    private BigDecimal cantidad;


    private String motivo;


    @ManyToOne
    @JoinColumn(name="id_usuario")
    private Usuario usuario;


    @CreationTimestamp
    @Column(name="fecha")
    private LocalDateTime fecha;

}