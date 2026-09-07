package com.example.consultoriomedico.Controller;

import com.example.consultoriomedico.Model.VentaDetalle;
import com.example.consultoriomedico.Service.VentaDetalleService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ventas-detalle")
public class VentaDetalleController {

    private final VentaDetalleService service;

    public VentaDetalleController(VentaDetalleService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<VentaDetalle>> listar(@RequestParam(required = false) Integer idVenta) {
        if (idVenta != null) {
            return ResponseEntity.ok(service.listarPorVenta(idVenta));
        }
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<VentaDetalle> buscar(@PathVariable Integer id) {
        VentaDetalle detalle = service.buscar(id);
        if (detalle == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(detalle);
    }

    @PostMapping
    public ResponseEntity<VentaDetalle> guardar(@Valid @RequestBody VentaDetalle detalle) {
        VentaDetalle nuevo = service.guardar(detalle);
        return new ResponseEntity<>(nuevo, HttpStatus.CREATED);
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