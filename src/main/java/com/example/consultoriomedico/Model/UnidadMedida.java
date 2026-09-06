package com.example.consultoriomedico.Model;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Data
@Table(name="unidad_medida")
public class UnidadMedida {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_unidad")
    private Integer idUnidad;


    private String nombre;


    private String abreviatura;

}