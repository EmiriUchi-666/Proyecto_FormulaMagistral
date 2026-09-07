package com.example.consultoriomedico.Model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;


@Entity
@Data
@Table(name="formula_ingrediente")
public class FormulaIngrediente {


    @EmbeddedId
    private FormulaIngredienteId id;


    @Column(name="cantidad_requerida")
    private BigDecimal cantidadRequerida;


}