package com.example.consultoriomedico.Model;


import jakarta.persistence.*;
import lombok.Data;


@Entity
@Data
@Table(name="proveedor")
public class Proveedor {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_proveedor")
    private Integer idProveedor;


    private String nombre;


    private String ruc;


    private String telefono;


    private String email;


    private String direccion;


    private Boolean activo;


}