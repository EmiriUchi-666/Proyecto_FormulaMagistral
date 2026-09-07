package com.example.consultoriomedico.Model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name="rol")
public class Rol {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idRol;


    @Column(name="nombre")
    private String nombre;
    
    

}
