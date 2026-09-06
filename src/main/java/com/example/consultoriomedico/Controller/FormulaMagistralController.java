package com.example.consultoriomedico.Controller;


import com.example.consultoriomedico.Model.FormulaMagistral;
import com.example.consultoriomedico.Service.FormulaMagistralService;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;



@RestController
@RequestMapping("/formulas")
public class FormulaMagistralController {



    private final FormulaMagistralService service;



    public FormulaMagistralController(
            FormulaMagistralService service){

        this.service = service;

    }




    @GetMapping
    public ResponseEntity<List<FormulaMagistral>> listar(){

        return ResponseEntity.ok(service.listar());

    }





    @GetMapping("/{id}")
    public ResponseEntity<FormulaMagistral> buscar(
            @PathVariable Integer id){


        FormulaMagistral formula = service.buscar(id);


        if(formula == null){

            return ResponseEntity.notFound().build();

        }


        return ResponseEntity.ok(formula);

    }





    @PostMapping
    public ResponseEntity<FormulaMagistral> guardar(
            @RequestBody FormulaMagistral formula){


        return new ResponseEntity<>(
                service.guardar(formula),
                HttpStatus.CREATED
        );

    }






    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(
            @PathVariable Integer id){


        service.eliminar(id);


        return ResponseEntity.noContent().build();

    }

}