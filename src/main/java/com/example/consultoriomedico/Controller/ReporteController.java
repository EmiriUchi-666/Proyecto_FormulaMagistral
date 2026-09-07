package com.example.consultoriomedico.Controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.consultoriomedico.DTO.FormulaMasVendidaDTO;
import com.example.consultoriomedico.DTO.InventarioValorizadoDTO;
import com.example.consultoriomedico.DTO.LotePorVencerDTO;
import com.example.consultoriomedico.Service.ReporteService;

/**
 * Expone los 3 reportes del módulo, cada uno respaldado por su
 * vista SQL correspondiente. Son endpoints de solo lectura (GET).
 */
@RestController
@RequestMapping("/reportes")
public class ReporteController {

    private final ReporteService service;

    public ReporteController(ReporteService service) {
        this.service = service;
    }

    @GetMapping("/lotes-por-vencer")
    public ResponseEntity<List<LotePorVencerDTO>> lotesPorVencer() {
        return ResponseEntity.ok(service.lotesPorVencer());
    }

    @GetMapping("/formulas-mas-vendidas")
    public ResponseEntity<List<FormulaMasVendidaDTO>> formulasMasVendidas() {
        return ResponseEntity.ok(service.formulasMasVendidas());
    }

    @GetMapping("/inventario-valorizado")
    public ResponseEntity<List<InventarioValorizadoDTO>> inventarioValorizado() {
        return ResponseEntity.ok(service.inventarioValorizado());
    }

}