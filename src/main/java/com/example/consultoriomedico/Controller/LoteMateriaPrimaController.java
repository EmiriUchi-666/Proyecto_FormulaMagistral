package com.example.consultoriomedico.Controller;


import com.example.consultoriomedico.Model.LoteMateriaPrima;
import com.example.consultoriomedico.Service.LoteMateriaPrimaService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/lotes")
public class LoteMateriaPrimaController {


    private final LoteMateriaPrimaService service;


    public LoteMateriaPrimaController(
            LoteMateriaPrimaService service){

        this.service = service;

    }



    // LISTAR LOTES
    @GetMapping
    public List<LoteMateriaPrima> listar(){

        return service.listar();

    }



    // REGISTRAR LOTE
    @PostMapping
    public LoteMateriaPrima guardar(
            @RequestBody LoteMateriaPrima lote){

        return service.guardar(lote);

    }



    // BUSCAR POR ID
    @GetMapping("/{id}")
    public LoteMateriaPrima buscar(
            @PathVariable Integer id){

        return service.buscar(id);

    }



    // ACTUALIZAR LOTE
    @PutMapping("/{id}")
    public ResponseEntity<LoteMateriaPrima> actualizar(
            @PathVariable Integer id,
            @RequestBody LoteMateriaPrima lote){


        LoteMateriaPrima existente = service.buscar(id);


        if(existente == null){

            return ResponseEntity.notFound().build();

        }


        existente.setMateriaPrima(lote.getMateriaPrima());
        existente.setCodigoLote(lote.getCodigoLote());
        existente.setFechaIngreso(lote.getFechaIngreso());
        existente.setFechaVencimiento(lote.getFechaVencimiento());
        existente.setCantidadInicial(lote.getCantidadInicial());
        existente.setCantidadActual(lote.getCantidadActual());
        existente.setCostoUnitario(lote.getCostoUnitario());


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