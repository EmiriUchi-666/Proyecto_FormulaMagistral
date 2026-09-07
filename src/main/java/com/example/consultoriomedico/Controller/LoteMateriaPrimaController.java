package com.example.consultoriomedico.Controller;

import com.example.consultoriomedico.Model.LoteMateriaPrima;
import com.example.consultoriomedico.Service.LoteMateriaPrimaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lotes-materia-prima")
public class LoteMateriaPrimaController {

    private final LoteMateriaPrimaService service;

    public LoteMateriaPrimaController(LoteMateriaPrimaService service) {
        this.service = service;
    }

    /**
     * Lista lotes. Admite filtros opcionales por query param
     * (se evalúan en este orden si vienen varios):
     *  - /lotes-materia-prima?idMateriaPrima=5
     *  - /lotes-materia-prima?vencidos=true
     *  - /lotes-materia-prima?stockBajo=true
     *  - /lotes-materia-prima                     -> todos
     */
    @GetMapping
    public ResponseEntity<List<LoteMateriaPrima>> listar(
            @RequestParam(required = false) Integer idMateriaPrima,
            @RequestParam(required = false, defaultValue = "false") boolean vencidos,
            @RequestParam(required = false, defaultValue = "false") boolean stockBajo) {

        if (idMateriaPrima != null) {
            return ResponseEntity.ok(service.listarPorMateriaPrima(idMateriaPrima));
        }
        if (vencidos) {
            return ResponseEntity.ok(service.listarVencidos());
        }
        if (stockBajo) {
            return ResponseEntity.ok(service.listarConStockBajo());
        }
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<LoteMateriaPrima> buscar(@PathVariable Integer id) {
        LoteMateriaPrima lote = service.buscar(id);
        if (lote == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(lote);
    }

    @PostMapping
    public ResponseEntity<LoteMateriaPrima> guardar(@Valid @RequestBody LoteMateriaPrima lote) {
        LoteMateriaPrima nuevo = service.guardar(lote);
        return new ResponseEntity<>(nuevo, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LoteMateriaPrima> actualizar(@PathVariable Integer id, @Valid @RequestBody LoteMateriaPrima lote) {
        LoteMateriaPrima existente = service.buscar(id);
        if (existente == null) {
            return ResponseEntity.notFound().build();
        }
        existente.setCodigoLote(lote.getCodigoLote());
        existente.setFechaIngreso(lote.getFechaIngreso());
        existente.setFechaVencimiento(lote.getFechaVencimiento());
        existente.setCantidadInicial(lote.getCantidadInicial());
        existente.setCantidadActual(lote.getCantidadActual());
        existente.setCostoUnitario(lote.getCostoUnitario());
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