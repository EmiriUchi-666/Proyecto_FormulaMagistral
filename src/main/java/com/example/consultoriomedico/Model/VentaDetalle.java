package com.example.consultoriomedico.Model;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

/**
 * Entidad que representa una línea (detalle) de una venta, asociada
 * a una orden de preparación de una fórmula magistral. Mapea la
 * tabla "venta_detalle".
 *
 * Nota: "id_orden" se guarda como Integer simple porque
 * OrdenPreparacion pertenece al módulo de Preparación (otro
 * integrante del equipo).
 */
@Entity
@Data
@Table(name = "venta_detalle")
public class VentaDetalle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_venta_detalle")
    private Integer idVentaDetalle;

    @NotNull(message = "La venta es obligatoria")
    @ManyToOne
    @JoinColumn(name = "id_venta", nullable = false)
    private Venta venta;

    @NotNull(message = "La orden de preparación es obligatoria")
    @Column(name = "id_orden")
    private Integer idOrden;

    @NotNull(message = "La cantidad es obligatoria")
    @Positive(message = "La cantidad debe ser mayor a cero")
    private BigDecimal cantidad;

    @NotNull(message = "El precio unitario es obligatorio")
    @Positive(message = "El precio unitario debe ser mayor a cero")
    @Column(name = "precio_unitario")
    private BigDecimal precioUnitario;

    /** Se calcula en el Service (cantidad * precioUnitario); no se valida como entrada. */
    private BigDecimal subtotal;

}