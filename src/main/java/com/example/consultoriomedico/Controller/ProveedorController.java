package com.example.consultoriomedico.Controller;

import com.example.consultoriomedico.Model.Proveedor;
import com.example.consultoriomedico.Service.ProveedorService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/proveedores")
public class ProveedorController {


    private final ProveedorService service;


    public ProveedorController(ProveedorService service){
        this.service = service;
    }



    // LISTAR TODOS
    @GetMapping
    public List<Proveedor> listar(){

        return service.listar();

    }



    // REGISTRAR
    @PostMapping
    public Proveedor guardar(
            @RequestBody Proveedor proveedor){

        return service.guardar(proveedor);

    }



    // BUSCAR POR ID
    @GetMapping("/{id}")
    public Proveedor buscar(
            @PathVariable Integer id){

        return service.buscar(id);

    }



    // ACTUALIZAR
    @PutMapping("/{id}")
    public ResponseEntity<Proveedor> actualizar(
            @PathVariable Integer id,
            @RequestBody Proveedor proveedor){


        Optional<Proveedor> encontrado = service.buscarPorId(id);


        if(encontrado.isPresent()){


            Proveedor p = encontrado.get();


            p.setNombre(proveedor.getNombre());
            p.setTelefono(proveedor.getTelefono());
            p.setDireccion(proveedor.getDireccion());
            p.setActivo(proveedor.getActivo());


            return ResponseEntity.ok(service.guardar(p));


        }else{

            return ResponseEntity.notFound().build();

        }

    }



    // ELIMINAR
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(
            @PathVariable Integer id){

        service.eliminar(id);

        return ResponseEntity.ok().build();

    }

}