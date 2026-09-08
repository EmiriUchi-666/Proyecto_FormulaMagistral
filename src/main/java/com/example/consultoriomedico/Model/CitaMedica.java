package com.example.consultoriomedico.Model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;


@Entity
@Data
@Table(name="cita_medica")
public class CitaMedica {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_cita")
    private Integer idCita;


    @Column(name="fecha_hora")
    private LocalDateTime fechaHora;


    private String motivo;


    private String estado;


    @ManyToOne
    @JoinColumn(name="id_paciente")
    private Paciente paciente;


}