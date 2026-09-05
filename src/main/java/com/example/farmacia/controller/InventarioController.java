package com.example.farmacia.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/inventario")
public class InventarioController {

    @GetMapping
    public String listar(Model model) {
        // TODO BACKEND: reemplazar por repositorio
        LocalDate hoy = LocalDate.now();
        model.addAttribute("lotes", List.of(
            lote("L-001", "Paracetamol BP", hoy.plusDays(200), 500, 1000),
            lote("L-002", "Ácido Hialurónico", hoy.plusDays(15), 50, 200),
            lote("L-003", "Gelatina Farmacéutica", hoy.minusDays(5), 10, 100),
            lote("L-004", "Glicerina", hoy.plusDays(90), 300, 500)));
        return "inventario/list";
    }

    private Map<String, Object> lote(String id, String materia, LocalDate vencimiento, int stock, int max) {
        Map<String, Object> m = new LinkedHashMap<>();
        m.put("id", id); m.put("materia", materia); m.put("vencimiento", vencimiento);
        m.put("stock", stock); m.put("max", max);
        return m;
    }
}