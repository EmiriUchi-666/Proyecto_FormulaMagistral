package com.example.consultoriomedico.Controller;


import com.example.consultoriomedico.Model.CitaMedica;
import com.example.consultoriomedico.Service.CitaMedicaService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@RestController
@RequestMapping("/citas")
public class CitaMedicaController {



    private final CitaMedicaService service;



    public CitaMedicaController(CitaMedicaService service){

        this.service = service;

    }



    // GET listar

    @GetMapping
    public ResponseEntity<List<CitaMedica>> listar(){

        return ResponseEntity.ok(service.listar());

    }



    // GET buscar por id

    @GetMapping("/{id}")
    public ResponseEntity<CitaMedica> buscar(
            @PathVariable Integer id){


        CitaMedica cita = service.buscar(id);


        if(cita == null){

            return ResponseEntity.notFound().build();

        }


        return ResponseEntity.ok(cita);

    }




    // POST registrar

    @PostMapping
    public ResponseEntity<CitaMedica> guardar(
            @RequestBody CitaMedica cita){


        return new ResponseEntity<>(
                service.guardar(cita),
                HttpStatus.CREATED
        );

    }




    // PUT actualizar

    @PutMapping("/{id}")
    public ResponseEntity<CitaMedica> actualizar(
            @PathVariable Integer id,
            @RequestBody CitaMedica cita){



        CitaMedica actualizado =
                service.actualizar(id,cita);



        if(actualizado == null){

            return ResponseEntity.notFound().build();

        }



        return ResponseEntity.ok(actualizado);


    }




    // DELETE eliminar

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(
            @PathVariable Integer id){


        CitaMedica cita = service.buscar(id);


        if(cita == null){

            return ResponseEntity.notFound().build();

        }


        service.eliminar(id);


        return ResponseEntity.ok().build();

    }


}