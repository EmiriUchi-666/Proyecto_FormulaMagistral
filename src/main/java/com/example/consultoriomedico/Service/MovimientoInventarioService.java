package com.example.consultoriomedico.Service;


import com.example.consultoriomedico.Model.MovimientoInventario;
import com.example.consultoriomedico.Repository.MovimientoInventarioRepository;

import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class MovimientoInventarioService {


    private final MovimientoInventarioRepository repository;


    public MovimientoInventarioService(
            MovimientoInventarioRepository repository){

        this.repository = repository;
    }



    public List<MovimientoInventario> listar(){

        return repository.findAll();
    }



    public MovimientoInventario guardar(
            MovimientoInventario movimiento){

        return repository.save(movimiento);
    }



    public MovimientoInventario buscar(Integer id){

        return repository.findById(id)
                .orElse(null);
    }



    public void eliminar(Integer id){

        repository.deleteById(id);
    }

}