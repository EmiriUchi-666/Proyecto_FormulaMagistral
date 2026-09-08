package com.example.consultoriomedico.Controller;


import com.example.consultoriomedico.Model.Receta;
import com.example.consultoriomedico.Service.RecetaService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/recetas")
public class RecetaController {


    private final RecetaService service;


    public RecetaController(RecetaService service){
        this.service = service;
    }



    // LISTAR TODAS
    @GetMapping
    public ResponseEntity<List<Receta>> listar(){

        return ResponseEntity.ok(service.listar());

    }



    // BUSCAR POR ID
    @GetMapping("/{id}")
    public ResponseEntity<Receta> buscar(
            @PathVariable Integer id
    ){

        Receta receta = service.buscar(id);

        if(receta == null){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(receta);

    }



    // REGISTRAR
    @PostMapping
    public ResponseEntity<Receta> guardar(
            @RequestBody Receta receta
    ){

        Receta nueva = service.guardar(receta);

        return new ResponseEntity<>(
                nueva,
                HttpStatus.CREATED
        );

    }



    // ACTUALIZAR
    @PutMapping("/{id}")
    public ResponseEntity<Receta> actualizar(
            @PathVariable Integer id,
            @RequestBody Receta receta
    ){

        Receta existente = service.buscar(id);


        if(existente == null){
            return ResponseEntity.notFound().build();
        }


        existente.setMedico(receta.getMedico());
        existente.setNumColegiatura(receta.getNumColegiatura());
        existente.setFechaEmision(receta.getFechaEmision());
        existente.setArchivoAdjunto(receta.getArchivoAdjunto());
        existente.setPaciente(receta.getPaciente());


        Receta actualizada = service.guardar(existente);


        return ResponseEntity.ok(actualizada);

    }



    // ELIMINAR
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(
            @PathVariable Integer id
    ){

        Receta receta = service.buscar(id);


        if(receta == null){
            return ResponseEntity.notFound().build();
        }


        service.eliminar(id);


        return ResponseEntity.noContent().build();

    }

}