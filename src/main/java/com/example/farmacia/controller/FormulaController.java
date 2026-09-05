package com.example.farmacia.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequestMapping("/formulas")
public class FormulaController {

    private static final List<Map<String, Object>> FORMULAS = new ArrayList<>(List.of(
        Map.of("id", 1, "codigo", "F-001", "nombre", "Crema Hidratante", "categoria", "Dermatologica", "precio_venta", 45.00),
        Map.of("id", 2, "codigo", "F-002", "nombre", "Jarabe Expectorante", "categoria", "Respiratorio", "precio_venta", 32.50),
        Map.of("id", 3, "codigo", "F-003", "nombre", "Gel Antiinflamatorio", "categoria", "Muscular", "precio_venta", 28.00),
        Map.of("id", 4, "codigo", "F-004", "nombre", "Capsulas Vitaminicas", "categoria", "Suplemento", "precio_venta", 55.00)
    ));

    @GetMapping
    public String listar(@RequestParam(required = false) String q, Model model) {
        var filtradas = FORMULAS.stream()
            .filter(f -> q == null || q.isBlank() || f.toString().toLowerCase().contains(q.toLowerCase()))
            .toList();
        model.addAttribute("formulas", filtradas);
        model.addAttribute("q", q);
        return "formulas/list";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("formula", new HashMap<>());
        return "formulas/form";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Map<String, Object> formula, RedirectAttributes ra) {
        formula.put("id", FORMULAS.size() + 1);
        FORMULAS.add(formula);
        ra.addFlashAttribute("success", "Formula registrada correctamente");
        return "redirect:/formulas";
    }

    @PostMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Integer id, RedirectAttributes ra) {
        FORMULAS.removeIf(f -> f.get("id").equals(id));
        ra.addFlashAttribute("success", "Formula eliminada");
        return "redirect:/formulas";
    }
}