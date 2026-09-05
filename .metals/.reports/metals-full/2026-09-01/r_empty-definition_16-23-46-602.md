error id: file:///C:/Users/USUARIO/Documents/farmacia/farmacia/src/main/java/com/example/farmacia/controller/FormulaController.java:_empty_/RedirectAttributes#
file:///C:/Users/USUARIO/Documents/farmacia/farmacia/src/main/java/com/example/farmacia/controller/FormulaController.java
empty definition using pc, found symbol in pc: _empty_/RedirectAttributes#
empty definition using semanticdb
empty definition using fallback
non-local guesses:

offset: 2201
uri: file:///C:/Users/USUARIO/Documents/farmacia/farmacia/src/main/java/com/example/farmacia/controller/FormulaController.java
text:
```scala
package com.example.farmacia.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.*;

@Controller
@RequestMapping("/formulas")
public class FormulaController {

    // TODO BACKEND: reemplazar por repositorio
    private static final List<Map<String, Object>> FORMULAS = new ArrayList<>(List.of(
        formula(1, "F-001", "Crema Hidratante", "Dermatológica", 45.00),
        formula(2, "F-002", "Jarabe Expectorante", "Respiratorio", 32.50),
        formula(3, "F-003", "Gel Antiinflamatorio", "Muscular", 28.00),
        formula(4, "F-004", "Cápsulas Vitamínicas", "Suplemento", 55.00)));

    private static Map<String, Object> formula(int id, String cod, String nombre, String categoria, double precio) {
        Map<String, Object> m = new LinkedHashMap<>();
        m.put("id", id); m.put("codigo", cod); m.put("nombre", nombre);
        m.put("categoria", categoria); m.put("precio_venta", precio);
        return m;
    }

    @GetMapping
    public String listar(@RequestParam(required = false) String q, Model model) {
        // TODO BACKEND: reemplazar por repositorio
        var filtradas = FORMULAS.stream()
            .filter(f -> q == null || q.isBlank() || f.toString().toLowerCase().contains(q.toLowerCase()))
            .toList();
        model.addAttribute("formulas", filtradas);
        model.addAttribute("q", q);
        return "formulas/list";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("formula", new HashMap<String, Object>());
        return "formulas/form";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Map<String, Object> formula, RedirectAttributes ra) {
        // TODO BACKEND: repository.save()
        formula.put("id", FORMULAS.size() + 1);
        FORMULAS.add(formula);
        ra.addFlashAttribute("success", "Fórmula registrada correctamente");
        return "redirect:/formulas";
    }

    @PostMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Integer id, Redire@@ctAttributes ra) {
        // TODO BACKEND: repository.deleteById()
        FORMULAS.removeIf(f -> f.get("id").equals(id));
        ra.addFlashAttribute("success", "Fórmula eliminada");
        return "redirect:/formulas";
    }
}
```


#### Short summary: 

empty definition using pc, found symbol in pc: _empty_/RedirectAttributes#