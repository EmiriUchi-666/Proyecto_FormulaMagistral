package com.example.consultoriomedico.Service;


import com.example.consultoriomedico.Model.CategoriaFormula;
import com.example.consultoriomedico.Repository.CategoriaFormulaRepository;

import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CategoriaFormulaService {


    private final CategoriaFormulaRepository repository;


    public CategoriaFormulaService(CategoriaFormulaRepository repository){
        this.repository = repository;
    }



    public List<CategoriaFormula> listar(){

        return repository.findAll();

    }



    public CategoriaFormula guardar(CategoriaFormula categoria){

        return repository.save(categoria);

    }



    public CategoriaFormula buscar(Integer id){

        return repository.findById(id)
                .orElse(null);

    }



    public void eliminar(Integer id){

        repository.deleteById(id);

    }

}