package com.example.consultoriomedico.Model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name="categoria_formula")
public class CategoriaFormula {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_categoria")
    private Integer idCategoria;


    @Column(name="nombre")
    private String nombre;

}