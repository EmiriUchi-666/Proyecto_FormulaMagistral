package com.example.consultoriomedico.Controller;


import com.example.consultoriomedico.Model.CategoriaFormula;
import com.example.consultoriomedico.Service.CategoriaFormulaService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/categorias-formula")
public class CategoriaFormulaController {



    private final CategoriaFormulaService service;


    public CategoriaFormulaController(CategoriaFormulaService service){

        this.service = service;

    }



    @GetMapping
    public ResponseEntity<List<CategoriaFormula>> listar(){

        return ResponseEntity.ok(service.listar());

    }



    @GetMapping("/{id}")
    public ResponseEntity<CategoriaFormula> buscar(
            @PathVariable Integer id){

        CategoriaFormula categoria = service.buscar(id);


        if(categoria == null){

            return ResponseEntity.notFound().build();

        }


        return ResponseEntity.ok(categoria);

    }




    @PostMapping
    public ResponseEntity<CategoriaFormula> guardar(
            @RequestBody CategoriaFormula categoria){


        CategoriaFormula nueva = service.guardar(categoria);


        return new ResponseEntity<>(
                nueva,
                HttpStatus.CREATED
        );

    }



    @PutMapping("/{id}")
    public ResponseEntity<CategoriaFormula> actualizar(
            @PathVariable Integer id,
            @RequestBody CategoriaFormula categoria){


        CategoriaFormula existente = service.buscar(id);


        if(existente == null){

            return ResponseEntity.notFound().build();

        }


        existente.setNombre(categoria.getNombre());


        return ResponseEntity.ok(
                service.guardar(existente)
        );

    }





    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(
            @PathVariable Integer id){


        CategoriaFormula categoria = service.buscar(id);


        if(categoria == null){

            return ResponseEntity.notFound().build();

        }


        service.eliminar(id);


        return ResponseEntity.noContent().build();

    }

}