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


    /**
     * Se agregó porque el equipo alteró la tabla "receta" con
     * ALTER TABLE receta ADD COLUMN id_cita ... (06/09/2026) pero
     * la entidad Java todavía no tenía el campo — sin esto, Hibernate
     * ignoraba la columna por completo.
     */
    @ManyToOne
    @JoinColumn(name="id_cita")
    private CitaMedica citaMedica;


    private String medico;


    @Column(name="num_colegiatura")
    private String numColegiatura;


    @Column(name="fecha_emision")
    private LocalDate fechaEmision;


    @Column(name="archivo_adjunto")
    private String archivoAdjunto;

}