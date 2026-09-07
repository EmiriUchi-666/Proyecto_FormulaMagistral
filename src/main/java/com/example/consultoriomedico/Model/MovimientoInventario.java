package com.example.consultoriomedico.Model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.Data;

/**
 * Entidad que representa un movimiento de inventario (entrada o
 * salida) sobre un lote de materia prima. Mapea la tabla
 * "movimiento_inventario".
 *
 * Nota: "id_usuario" se guarda como Integer simple (no como relación
 * @ManyToOne) porque la entidad Usuario pertenece al módulo de
 * Seguridad/Usuarios, desarrollado por otro integrante del equipo.
 * Cuando ese módulo esté integrado, se puede reemplazar por una
 * relación real sin afectar la tabla.
 */
@Entity
@Data
@Table(name = "movimiento_inventario")
public class MovimientoInventario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_movimiento")
    private Integer idMovimiento;

    @NotNull(message = "El lote es obligatorio")
    @ManyToOne
    @JoinColumn(name = "id_lote", nullable = false)
    private LoteMateriaPrima lote;

    /** Valores esperados: "ENTRADA" o "SALIDA" (la validación de negocio real está en el Service). */
    @NotNull(message = "El tipo de movimiento es obligatorio")
    @Pattern(regexp = "(?i)ENTRADA|SALIDA", message = "El tipo de movimiento debe ser ENTRADA o SALIDA")
    @Column(name = "tipo_movimiento")
    private String tipoMovimiento;

    @NotNull(message = "La cantidad es obligatoria")
    @Positive(message = "La cantidad debe ser mayor a cero")
    private BigDecimal cantidad;

    private String motivo;

    @NotNull(message = "El usuario que registra el movimiento es obligatorio")
    @Column(name = "id_usuario")
    private Integer idUsuario;

    private LocalDateTime fecha;

}