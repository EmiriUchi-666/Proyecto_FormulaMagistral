package com.example.consultoriomedico.Service;

import com.example.consultoriomedico.Model.Rol;
import com.example.consultoriomedico.Repository.RolRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RolService {


    private final RolRepository repository;


    public RolService(RolRepository repository){
        this.repository = repository;
    }


    public List<Rol> listar(){

        return repository.findAll();

    }


    public Rol guardar(Rol rol){

        return repository.save(rol);

    }


    public Rol buscar(Integer id){

        return repository.findById(id)
                .orElse(null);

    }


    public void eliminar(Integer id){

        repository.deleteById(id);

    }

}