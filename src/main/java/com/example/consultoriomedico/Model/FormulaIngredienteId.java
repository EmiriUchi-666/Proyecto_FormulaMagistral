package com.example.consultoriomedico.Model;

import jakarta.persistence.Embeddable;
import lombok.Data;

import java.io.Serializable;

@Data
@Embeddable
public class FormulaIngredienteId implements Serializable {

    private Integer idFormula;

    private Integer idMateriaPrima;

}