package com.example.consultoriomedico.Controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.consultoriomedico.Model.MateriaPrima;
import com.example.consultoriomedico.Service.MateriaPrimaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/materias-primas")
public class MateriaPrimaController {

    private final MateriaPrimaService service;

    public MateriaPrimaController(MateriaPrimaService service) {
        this.service = service;
    }

    /**
     * Lista materias primas. Admite filtros opcionales por query param
     * (se evalúan en este orden si vienen varios):
     *  - /materias-primas?nombre=paracetamol
     *  - /materias-primas?tipo=excipiente
     *  - /materias-primas?soloActivas=true
     *  - /materias-primas                     -> todas
     */
    @GetMapping
    public ResponseEntity<List<MateriaPrima>> listar(
            @RequestParam(required = false) String nombre,
            @RequestParam(required = false) String tipo,
            @RequestParam(required = false, defaultValue = "false") boolean soloActivas) {

        if (nombre != null) {
            return ResponseEntity.ok(service.buscarPorNombre(nombre));
        }
        if (tipo != null) {
            return ResponseEntity.ok(service.buscarPorTipo(tipo));
        }
        return ResponseEntity.ok(soloActivas ? service.listarActivas() : service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MateriaPrima> buscar(@PathVariable Integer id) {
        MateriaPrima materiaPrima = service.buscar(id);
        if (materiaPrima == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(materiaPrima);
    }

    @PostMapping
    public ResponseEntity<MateriaPrima> guardar(@Valid @RequestBody MateriaPrima materiaPrima) {
        MateriaPrima nueva = service.guardar(materiaPrima);
        return new ResponseEntity<>(nueva, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MateriaPrima> actualizar(@PathVariable Integer id, @Valid @RequestBody MateriaPrima materiaPrima) {
        MateriaPrima existente = service.buscar(id);
        if (existente == null) {
            return ResponseEntity.notFound().build();
        }
        existente.setNombre(materiaPrima.getNombre());
        existente.setTipo(materiaPrima.getTipo());
        existente.setUnidadMedida(materiaPrima.getUnidadMedida());
        existente.setStockMinimo(materiaPrima.getStockMinimo());
        existente.setProveedor(materiaPrima.getProveedor());
        return ResponseEntity.ok(service.guardar(existente));
    }

    /** Baja lógica en vez de DELETE físico, para no perder el histórico de lotes. */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> desactivar(@PathVariable Integer id) {
        if (service.buscar(id) == null) {
            return ResponseEntity.notFound().build();
        }
        service.desactivar(id);
        return ResponseEntity.noContent().build();
    }

}
