package com.example.consultoriomedico.Controller;

import com.example.consultoriomedico.Model.MovimientoInventario;
import com.example.consultoriomedico.Service.MovimientoInventarioService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Expone el registro de entradas/salidas de inventario.
 * A diferencia de un CRUD simple, POST aquí ejecuta la regla de
 * negocio de MovimientoInventarioService (actualiza stock, valida
 * stock suficiente en salidas) y por eso puede devolver 400/409
 * cuando la operación no es válida.
 */
@RestController
@RequestMapping("/movimientos-inventario")
public class MovimientoInventarioController {

    private final MovimientoInventarioService service;

    public MovimientoInventarioController(MovimientoInventarioService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<MovimientoInventario>> listar(
            @RequestParam(required = false) Integer idLote) {
        if (idLote != null) {
            return ResponseEntity.ok(service.listarPorLote(idLote));
        }
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovimientoInventario> buscar(@PathVariable Integer id) {
        MovimientoInventario movimiento = service.buscar(id);
        if (movimiento == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(movimiento);
    }

    @PostMapping
    public ResponseEntity<?> registrar(@Valid @RequestBody MovimientoInventario movimiento) {
        try {
            MovimientoInventario registrado = service.registrar(movimiento);
            return new ResponseEntity<>(registrado, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

}