package com.example.consultoriomedico.Service;


import com.example.consultoriomedico.Model.Receta;
import com.example.consultoriomedico.Repository.RecetaRepository;

import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class RecetaService {


    private final RecetaRepository repository;


    public RecetaService(RecetaRepository repository){
        this.repository = repository;
    }



    public List<Receta> listar(){

        return repository.findAll();

    }



    public Receta guardar(Receta receta){

        return repository.save(receta);

    }



    public Receta buscar(Integer id){

        return repository.findById(id)
                .orElse(null);

    }



    public void eliminar(Integer id){

        repository.deleteById(id);

    }

}