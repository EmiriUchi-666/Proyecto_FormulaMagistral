package com.example.consultoriomedico.Model;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

/**
 * Entidad que representa un lote físico de una materia prima
 * (con fecha de vencimiento y stock propio). Mapea la tabla
 * "lote_materia_prima".
 *
 * El control de stock del sistema se hace a nivel de lote, no a
 * nivel de materia prima, porque cada lote vence en fechas distintas
 * y tiene su propio costo unitario.
 */
@Entity
@Data
@Table(name = "lote_materia_prima")
public class LoteMateriaPrima {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_lote")
    private Integer idLote;

    @NotNull(message = "La materia prima es obligatoria")
    @ManyToOne
    @JoinColumn(name = "id_materia_prima", nullable = false)
    private MateriaPrima materiaPrima;

    @NotBlank(message = "El código de lote es obligatorio")
    @Column(name = "codigo_lote")
    private String codigoLote;

    @NotNull(message = "La fecha de ingreso es obligatoria")
    @Column(name = "fecha_ingreso")
    private LocalDate fechaIngreso;

    @NotNull(message = "La fecha de vencimiento es obligatoria")
    @Column(name = "fecha_vencimiento")
    private LocalDate fechaVencimiento;

    @NotNull(message = "La cantidad inicial es obligatoria")
    @Positive(message = "La cantidad inicial debe ser mayor a cero")
    @Column(name = "cantidad_inicial")
    private BigDecimal cantidadInicial;

    @NotNull(message = "La cantidad actual es obligatoria")
    @PositiveOrZero(message = "La cantidad actual no puede ser negativa")
    @Column(name = "cantidad_actual")
    private BigDecimal cantidadActual;

    @NotNull(message = "El costo unitario es obligatorio")
    @Positive(message = "El costo unitario debe ser mayor a cero")
    @Column(name = "costo_unitario")
    private BigDecimal costoUnitario;

}