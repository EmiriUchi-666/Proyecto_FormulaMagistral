package com.example.consultoriomedico.Service;


import com.example.consultoriomedico.Model.FormulaMagistral;
import com.example.consultoriomedico.Repository.FormulaMagistralRepository;

import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class FormulaMagistralService {


    private final FormulaMagistralRepository repository;



    public FormulaMagistralService(
            FormulaMagistralRepository repository){

        this.repository = repository;

    }



    public List<FormulaMagistral> listar(){

        return repository.findAll();

    }



    public FormulaMagistral guardar(
            FormulaMagistral formula){

        return repository.save(formula);

    }



    public FormulaMagistral buscar(Integer id){

        return repository.findById(id)
                .orElse(null);

    }



    public void eliminar(Integer id){

        repository.deleteById(id);

    }

}