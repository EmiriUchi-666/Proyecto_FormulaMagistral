package com.example.consultoriomedico.Service;


import com.example.consultoriomedico.Model.CitaMedica;
import com.example.consultoriomedico.Repository.CitaMedicaRepository;

import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CitaMedicaService {


    private final CitaMedicaRepository repository;


    public CitaMedicaService(CitaMedicaRepository repository){

        this.repository = repository;

    }


    public List<CitaMedica> listar(){

        return repository.findAll();

    }



    public CitaMedica guardar(CitaMedica cita){

        return repository.save(cita);

    }



    public CitaMedica buscar(Integer id){

        return repository.findById(id)
                .orElse(null);

    }



    public CitaMedica actualizar(Integer id, CitaMedica cita){


        CitaMedica existente = buscar(id);


        if(existente != null){

            existente.setFechaHora(cita.getFechaHora());
            existente.setMotivo(cita.getMotivo());
            existente.setEstado(cita.getEstado());
            existente.setPaciente(cita.getPaciente());


            return repository.save(existente);

        }


        return null;

    }



    public void eliminar(Integer id){

        repository.deleteById(id);

    }


}