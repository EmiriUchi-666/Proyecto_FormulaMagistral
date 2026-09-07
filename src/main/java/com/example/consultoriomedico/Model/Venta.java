package com.example.consultoriomedico.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Entidad que representa la cabecera de una venta. Mapea la tabla
 * "venta".
 *
 * Nota: "id_usuario" se guarda como Integer simple porque Usuario
 * pertenece al módulo de Seguridad/Usuarios (otro integrante).
 * "paciente" sí se mapea como relación real porque la entidad
 * Paciente ya existe en el proyecto (módulo de Pacientes).
 */
@Entity
@Data
@Table(name = "venta")
public class Venta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_venta")
    private Integer idVenta;

    @NotNull(message = "El paciente es obligatorio")
    @ManyToOne
    @JoinColumn(name = "id_paciente", nullable = false)
    private Paciente paciente;

    @NotNull(message = "El usuario que registra la venta es obligatorio")
    @Column(name = "id_usuario")
    private Integer idUsuario;

    @Column(name = "fecha_venta")
    private LocalDateTime fechaVenta;

    /** Ej: "BOLETA", "FACTURA". */
    @NotBlank(message = "El tipo de comprobante es obligatorio")
    @Column(name = "tipo_comprobante")
    private String tipoComprobante;

    @Column(name = "num_comprobante")
    private String numComprobante;

    @NotNull(message = "El total es obligatorio")
    @PositiveOrZero(message = "El total no puede ser negativo")
    private BigDecimal total;

    /** Ej: "EMITIDA", "ANULADA". Se asigna en el Service si no viene informado. */
    private String estado;

}