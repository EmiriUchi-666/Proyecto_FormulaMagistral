package com.example.consultoriomedico.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

/**
 * Entidad que representa a un proveedor de materias primas.
 * Mapea la tabla "proveedor" de la base de datos "farmacia".
 *
 * Nota: la columna se llama "nombre" (antes era "razon_social",
 * renombrada por el equipo el 06/09/2026). Se agregó también la
 * columna "activo" para poder dar de baja lógica a un proveedor.
 */
@Entity
@Data
@Table(name = "proveedor")
public class Proveedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_proveedor")
    private Integer idProveedor;

    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    @NotBlank(message = "El RUC es obligatorio")
    @Pattern(regexp = "\\d{11}", message = "El RUC debe tener 11 dígitos")
    private String ruc;

    private String telefono;

    @Email(message = "El email no tiene un formato válido")
    private String email;

    private String direccion;

    private Boolean activo;

}