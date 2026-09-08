package com.example.consultoriomedico.Model;


import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;


@Entity
@Data
@Table(name="usuario")
public class Usuario {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idUsuario;


    private String nombres;


    private String apellidos;


    private String email;


    private String passwordHash;


    private Boolean activo;


    @Column(name="fecha_creacion", insertable = false, updatable = false)
    private LocalDateTime fechaCreacion;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="id_rol")
    private Rol rol;


}