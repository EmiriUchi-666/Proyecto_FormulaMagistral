package com.example.consultoriomedico.Service;

import com.example.consultoriomedico.Model.Proveedor;
import com.example.consultoriomedico.Repository.ProveedorRepository;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class ProveedorService {


    private final ProveedorRepository repository;


    public ProveedorService(ProveedorRepository repository){

        this.repository = repository;

    }



    public List<Proveedor> listar(){

        return repository.findAll();

    }



    public Proveedor guardar(Proveedor proveedor){

        return repository.save(proveedor);

    }



    public Proveedor buscar(Integer id){

        return repository.findById(id).orElse(null);

    }



    public Optional<Proveedor> buscarPorId(Integer id){

        return repository.findById(id);

    }



    public void eliminar(Integer id){

        repository.deleteById(id);

    }

}