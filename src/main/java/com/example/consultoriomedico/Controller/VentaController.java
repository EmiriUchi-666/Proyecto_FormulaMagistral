package com.example.consultoriomedico.Controller;

import com.example.consultoriomedico.Model.Venta;
import com.example.consultoriomedico.Service.VentaService;
import jakarta.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/ventas")
public class VentaController {

    private final VentaService service;

    public VentaController(VentaService service) {
        this.service = service;
    }

    /**
     * Lista ventas. Admite filtros opcionales por query param
     * (se evalúan en este orden si vienen varios):
     *  - /ventas?idPaciente=3
     *  - /ventas?estado=ANULADA
     *  - /ventas?desde=2026-09-01T00:00:00&hasta=2026-09-30T23:59:59
     *  - /ventas                                -> todas
     */
    @GetMapping
    public ResponseEntity<List<Venta>> listar(
            @RequestParam(required = false) Integer idPaciente,
            @RequestParam(required = false) String estado,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime desde,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime hasta) {

        if (idPaciente != null) {
            return ResponseEntity.ok(service.listarPorPaciente(idPaciente));
        }
        if (estado != null) {
            return ResponseEntity.ok(service.buscarPorEstado(estado));
        }
        if (desde != null && hasta != null) {
            return ResponseEntity.ok(service.buscarPorRangoFechas(desde, hasta));
        }
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Venta> buscar(@PathVariable Integer id) {
        Venta venta = service.buscar(id);
        if (venta == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(venta);
    }

    @PostMapping
    public ResponseEntity<Venta> registrar(@Valid @RequestBody Venta venta) {
        Venta nueva = service.registrar(venta);
        return new ResponseEntity<>(nueva, HttpStatus.CREATED);
    }

    @PutMapping("/{id}/anular")
    public ResponseEntity<Venta> anular(@PathVariable Integer id) {
        Venta anulada = service.anular(id);
        if (anulada == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(anulada);
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