package com.example.farmacia.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/recetas")
public class RecetaController {

    private static final List<Map<String, Object>> RECETAS = new ArrayList<>(List.of(
        Map.of("id", 1, "num_receta", "REC-001", "cliente", "Juan Ydrogo", "medico", "Dr. Carlos Mendoza", "fecha_emision", "2024-01-15"),
        Map.of("id", 2, "num_receta", "REC-002", "cliente", "Maria Garcia", "medico", "Dra. Ana Lopez", "fecha_emision", "2024-01-14")
    ));

    @GetMapping
    public String listar(@RequestParam(required = false) String q, Model model) {
        var filtradas = RECETAS.stream().filter(r -> q == null || q.isBlank() || r.toString().toLowerCase().contains(q.toLowerCase())).toList();
        model.addAttribute("recetas", filtradas);
        model.addAttribute("q", q);
        return "recetas/list";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("receta", new HashMap<>());
        return "recetas/form";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Map<String, Object> receta, RedirectAttributes ra) {
        receta.put("id", RECETAS.size() + 1);
        RECETAS.add(receta);
        ra.addFlashAttribute("success", "Receta registrada correctamente");
        return "redirect:/recetas";
    }

    @PostMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Integer id, RedirectAttributes ra) {
        RECETAS.removeIf(r -> r.get("id").equals(id));
        ra.addFlashAttribute("success", "Receta eliminada");
        return "redirect:/recetas";
    }
}