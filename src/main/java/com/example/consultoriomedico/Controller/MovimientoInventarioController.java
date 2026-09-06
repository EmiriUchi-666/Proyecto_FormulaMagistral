package com.example.consultoriomedico.Controller;


import com.example.consultoriomedico.Model.MovimientoInventario;
import com.example.consultoriomedico.Service.MovimientoInventarioService;

import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/movimientos")
public class MovimientoInventarioController {


    private final MovimientoInventarioService service;


    public MovimientoInventarioController(
            MovimientoInventarioService service){

        this.service = service;
    }



    @GetMapping
    public List<MovimientoInventario> listar(){

        return service.listar();
    }



    @PostMapping
    public MovimientoInventario guardar(
            @RequestBody MovimientoInventario movimiento){

        return service.guardar(movimiento);
    }



    @GetMapping("/{id}")
    public MovimientoInventario buscar(
            @PathVariable Integer id){

        return service.buscar(id);
    }



    @DeleteMapping("/{id}")
    public void eliminar(
            @PathVariable Integer id){

        service.eliminar(id);
    }

}