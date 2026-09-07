package com.example.consultoriomedico.Controller;


import com.example.consultoriomedico.Model.Rol;
import com.example.consultoriomedico.Service.RolService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/roles")
public class RolController {


    private final RolService service;


    public RolController(RolService service){

        this.service = service;

    }


    @GetMapping
    public ResponseEntity<List<Rol>> listar(){

        return ResponseEntity.ok(service.listar());

    }


    @GetMapping("/{id}")
    public ResponseEntity<Rol> buscar(@PathVariable Integer id){

        Rol rol = service.buscar(id);

        if(rol == null){

            return ResponseEntity.notFound().build();

        }

        return ResponseEntity.ok(rol);

    }


    @PostMapping
    public ResponseEntity<Rol> guardar(@RequestBody Rol rol){

        Rol nuevo = service.guardar(rol);

        return new ResponseEntity<>(nuevo, HttpStatus.CREATED);

    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id){

        service.eliminar(id);

        return ResponseEntity.noContent().build();

    }

}