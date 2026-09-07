package com.example.consultoriomedico.Controller;


import com.example.consultoriomedico.Model.Usuario;
import com.example.consultoriomedico.Service.UsuarioService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/usuarios")
public class UsuarioController {


    private final UsuarioService service;


    public UsuarioController(UsuarioService service){

        this.service = service;

    }



    // LISTAR TODOS
    @GetMapping
    public ResponseEntity<List<Usuario>> listar(){

        return ResponseEntity.ok(service.listar());

    }



    // BUSCAR POR ID
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> buscar(@PathVariable Integer id){

        Usuario usuario = service.buscar(id);


        if(usuario == null){

            return ResponseEntity.notFound().build();

        }


        return ResponseEntity.ok(usuario);

    }



    // REGISTRAR
    @PostMapping
    public ResponseEntity<Usuario> guardar(@RequestBody Usuario usuario){


        Usuario nuevo = service.guardar(usuario);


        return new ResponseEntity<>(
                nuevo,
                HttpStatus.CREATED
        );

    }



    // ELIMINAR
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id){


        Usuario usuario = service.buscar(id);


        if(usuario == null){

            return ResponseEntity.notFound().build();

        }


        service.eliminar(id);


        return ResponseEntity.noContent().build();

    }

}