package com.example.consultoriomedico.Controller;


import com.example.consultoriomedico.Model.MateriaPrima;
import com.example.consultoriomedico.Service.MateriaPrimaService;

import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/materias-primas")
public class MateriaPrimaController {


    private final MateriaPrimaService service;


    public MateriaPrimaController(MateriaPrimaService service){
        this.service = service;
    }



    @GetMapping
    public List<MateriaPrima> listar(){

        return service.listar();

    }



    @PostMapping
    public MateriaPrima guardar(
            @RequestBody MateriaPrima materiaPrima){

        return service.guardar(materiaPrima);

    }



    @GetMapping("/{id}")
    public MateriaPrima buscar(
            @PathVariable Integer id){

        return service.buscar(id);

    }



    @DeleteMapping("/{id}")
    public void eliminar(
            @PathVariable Integer id){

        service.eliminar(id);

    }

}