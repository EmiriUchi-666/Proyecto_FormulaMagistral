package com.example.consultoriomedico.Repository;


import com.example.consultoriomedico.Model.FormulaIngrediente;
import com.example.consultoriomedico.Model.FormulaIngredienteId;
import org.springframework.data.jpa.repository.JpaRepository;


public interface FormulaIngredienteRepository 
extends JpaRepository<FormulaIngrediente, FormulaIngredienteId> {


}