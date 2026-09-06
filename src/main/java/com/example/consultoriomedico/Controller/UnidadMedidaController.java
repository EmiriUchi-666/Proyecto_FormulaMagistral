package com.example.consultoriomedico.Controller;


import com.example.consultoriomedico.Model.UnidadMedida;
import com.example.consultoriomedico.Service.UnidadMedidaService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/unidades")
public class UnidadMedidaController {


    private final UnidadMedidaService service;


    public UnidadMedidaController(UnidadMedidaService service){

        this.service = service;

    }



    // LISTAR
    @GetMapping
    public List<UnidadMedida> listar(){

        return service.listar();

    }



    // REGISTRAR
    @PostMapping
    public UnidadMedida guardar(
            @RequestBody UnidadMedida unidad){

        return service.guardar(unidad);

    }



    // BUSCAR POR ID
    @GetMapping("/{id}")
    public UnidadMedida buscar(
            @PathVariable Integer id){

        return service.buscar(id);

    }



    // ACTUALIZAR
    @PutMapping("/{id}")
    public ResponseEntity<UnidadMedida> actualizar(
            @PathVariable Integer id,
            @RequestBody UnidadMedida unidad){


        UnidadMedida existente = service.buscar(id);


        if(existente == null){

            return ResponseEntity.notFound().build();

        }


        existente.setNombre(unidad.getNombre());
        existente.setAbreviatura(unidad.getAbreviatura());


        return ResponseEntity.ok(
                service.guardar(existente)
        );

    }



    // ELIMINAR
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(
            @PathVariable Integer id){


        service.eliminar(id);


        return ResponseEntity.ok().build();

    }

}