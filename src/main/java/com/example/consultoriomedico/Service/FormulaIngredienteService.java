package com.example.consultoriomedico.Service;


import com.example.consultoriomedico.Model.FormulaIngrediente;
import com.example.consultoriomedico.Model.FormulaIngredienteId;
import com.example.consultoriomedico.Repository.FormulaIngredienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class FormulaIngredienteService {


    private final FormulaIngredienteRepository repository;


    public FormulaIngredienteService(FormulaIngredienteRepository repository){
        this.repository = repository;
    }



    public List<FormulaIngrediente> listar(){

        return repository.findAll();

    }



    public FormulaIngrediente guardar(FormulaIngrediente formulaIngrediente){

        return repository.save(formulaIngrediente);

    }



    public FormulaIngrediente buscar(FormulaIngredienteId id){

        return repository.findById(id)
                .orElse(null);

    }



    public void eliminar(FormulaIngredienteId id){

        repository.deleteById(id);

    }


}