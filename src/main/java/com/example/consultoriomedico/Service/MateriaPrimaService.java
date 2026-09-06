package com.example.consultoriomedico.Service;

import com.example.consultoriomedico.Model.MateriaPrima;
import com.example.consultoriomedico.Repository.MateriaPrimaRepository;

import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class MateriaPrimaService {


    private final MateriaPrimaRepository repository;


    public MateriaPrimaService(MateriaPrimaRepository repository){
        this.repository = repository;
    }


    public List<MateriaPrima> listar(){
        return repository.findAll();
    }


    public MateriaPrima guardar(MateriaPrima materiaPrima){
        return repository.save(materiaPrima);
    }


    public MateriaPrima buscar(Integer id){

        return repository.findById(id)
                .orElse(null);

    }


    public void eliminar(Integer id){

        repository.deleteById(id);

    }

}