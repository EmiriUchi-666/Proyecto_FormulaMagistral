package com.example.consultoriomedico.Service;


import com.example.consultoriomedico.Model.Paciente;
import com.example.consultoriomedico.Repository.PacienteRepository;

import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class PacienteService {


    private final PacienteRepository repository;


    public PacienteService(PacienteRepository repository){

        this.repository = repository;

    }


    public List<Paciente> listar(){

        return repository.findAll();

    }


    public Paciente guardar(Paciente paciente){

        return repository.save(paciente);

    }


    public Paciente buscar(Integer id){

        return repository.findById(id).orElse(null);

    }


    public void eliminar(Integer id){

        repository.deleteById(id);

    }

}