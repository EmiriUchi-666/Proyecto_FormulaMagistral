package com.example.consultoriomedico.Model;


import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;


@Entity
@Data
@Table(name="receta")
public class Receta {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_receta")
    private Integer idReceta;


    @ManyToOne
    @JoinColumn(name="id_paciente")
    private Paciente paciente;


    private String medico;


    @Column(name="num_colegiatura")
    private String numColegiatura;


    @Column(name="fecha_emision")
    private LocalDate fechaEmision;


    @Column(name="archivo_adjunto")
    private String archivoAdjunto;

}