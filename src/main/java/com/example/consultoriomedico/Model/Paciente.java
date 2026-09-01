package com.example.consultoriomedico.Model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;


@Entity
@Data
@Table(name="paciente")
public class Paciente {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_paciente")
    private Integer idPaciente;


    @Column(name="tipo_documento")
    private String tipoDocumento;


    @Column(name="num_documento")
    private String numDocumento;


    private String nombres;


    private String apellidos;


    private String telefono;


    private String email;


    @Column(name="fecha_registro")
    private LocalDateTime fechaRegistro;

}