package com.example.consultoriomedico.Controller;

import com.example.consultoriomedico.Model.UnidadMedida;
import com.example.consultoriomedico.Service.UnidadMedidaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/unidades-medida")
public class UnidadMedidaController {

    private final UnidadMedidaService service;

    public UnidadMedidaController(UnidadMedidaService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<UnidadMedida>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UnidadMedida> buscar(@PathVariable Integer id) {
        UnidadMedida unidad = service.buscar(id);
        if (unidad == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(unidad);
    }

    @PostMapping
    public ResponseEntity<UnidadMedida> guardar(@Valid @RequestBody UnidadMedida unidad) {
        UnidadMedida nueva = service.guardar(unidad);
        return new ResponseEntity<>(nueva, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UnidadMedida> actualizar(@PathVariable Integer id, @Valid @RequestBody UnidadMedida unidad) {
        UnidadMedida existente = service.buscar(id);
        if (existente == null) {
            return ResponseEntity.notFound().build();
        }
        existente.setNombre(unidad.getNombre());
        existente.setAbreviatura(unidad.getAbreviatura());
        return ResponseEntity.ok(service.guardar(existente));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        if (service.buscar(id) == null) {
            return ResponseEntity.notFound().build();
        }
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }

}