package com.example.consultoriomedico.Controller;


import com.example.consultoriomedico.Model.FormulaIngrediente;
import com.example.consultoriomedico.Model.FormulaIngredienteId;
import com.example.consultoriomedico.Service.FormulaIngredienteService;

import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/formula-ingredientes")
public class FormulaIngredienteController {


    private final FormulaIngredienteService service;


    public FormulaIngredienteController(FormulaIngredienteService service){

        this.service = service;

    }



    @GetMapping
    public List<FormulaIngrediente> listar(){

        return service.listar();

    }



    @PostMapping
    public FormulaIngrediente guardar(@RequestBody FormulaIngrediente formulaIngrediente){

        return service.guardar(formulaIngrediente);

    }



    @GetMapping("/{idFormula}/{idMateriaPrima}")
    public FormulaIngrediente buscar(
            @PathVariable Integer idFormula,
            @PathVariable Integer idMateriaPrima
    ){

        FormulaIngredienteId id = new FormulaIngredienteId();

        id.setIdFormula(idFormula);
        id.setIdMateriaPrima(idMateriaPrima);

        return service.buscar(id);

    }



    @DeleteMapping("/{idFormula}/{idMateriaPrima}")
    public void eliminar(
            @PathVariable Integer idFormula,
            @PathVariable Integer idMateriaPrima
    ){

        FormulaIngredienteId id = new FormulaIngredienteId();

        id.setIdFormula(idFormula);
        id.setIdMateriaPrima(idMateriaPrima);

        service.eliminar(id);

    }


}