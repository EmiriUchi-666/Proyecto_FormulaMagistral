package com.example.consultoriomedico.Service;


import com.example.consultoriomedico.Model.LoteMateriaPrima;
import com.example.consultoriomedico.Repository.LoteMateriaPrimaRepository;

import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class LoteMateriaPrimaService {


    private final LoteMateriaPrimaRepository repository;


    public LoteMateriaPrimaService(
            LoteMateriaPrimaRepository repository){

        this.repository = repository;

    }



    public List<LoteMateriaPrima> listar(){

        return repository.findAll();

    }



    public LoteMateriaPrima guardar(
            LoteMateriaPrima lote){

        return repository.save(lote);

    }



    public LoteMateriaPrima buscar(Integer id){

        return repository.findById(id)
                .orElse(null);

    }



    public void eliminar(Integer id){

        repository.deleteById(id);

    }

}