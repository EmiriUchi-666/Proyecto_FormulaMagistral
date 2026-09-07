package com.example.consultoriomedico.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * Entidad que representa una unidad de medida (g, ml, mg, etc.)
 * usada por las materias primas. Mapea la tabla "unidad_medida".
 */
@Entity
@Data
@Table(name = "unidad_medida")
public class UnidadMedida {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_unidad")
    private Integer idUnidad;

    @NotBlank(message = "El nombre de la unidad es obligatorio")
    private String nombre;

    @NotBlank(message = "La abreviatura es obligatoria")
    private String abreviatura;

}