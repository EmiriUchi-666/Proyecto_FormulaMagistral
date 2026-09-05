error id: file:///C:/Users/USUARIO/Documents/farmacia/farmacia/src/main/java/com/example/farmacia/controller/RecetaController.java:_empty_/RedirectAttributes#
file:///C:/Users/USUARIO/Documents/farmacia/farmacia/src/main/java/com/example/farmacia/controller/RecetaController.java
empty definition using pc, found symbol in pc: _empty_/RedirectAttributes#
empty definition using semanticdb
empty definition using fallback
non-local guesses:

offset: 2197
uri: file:///C:/Users/USUARIO/Documents/farmacia/farmacia/src/main/java/com/example/farmacia/controller/RecetaController.java
text:
```scala
package com.example.farmacia.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.*;

@Controller
@RequestMapping("/recetas")
public class RecetaController {

    // TODO BACKEND: reemplazar por repositorio
    private static final List<Map<String, Object>> RECETAS = new ArrayList<>(List.of(
        crearReceta(1, "REC-001", "Juan Diego Ydrogo", "Dr. Carlos Mendoza", "2024-01-15"),
        crearReceta(2, "REC-002", "María García", "Dra. Ana López", "2024-01-14"),
        crearReceta(3, "REC-003", "Carlos Ramírez", "Dr. Pedro Sánchez", "2024-01-13")
    ));

    private static Map<String, Object> crearReceta(int id, String num, String cliente, String medico, String fecha) {
        Map<String, Object> m = new LinkedHashMap<>();
        m.put("id", id);
        m.put("num_receta", num);
        m.put("cliente", cliente);
        m.put("medico", medico);
        m.put("fecha_emision", fecha);
        return m;
    }

    @GetMapping
    public String listar(@RequestParam(required = false) String q, Model model) {
        // TODO BACKEND: reemplazar por repositorio
        var filtradas = RECETAS.stream()
            .filter(r -> q == null || q.isBlank() || r.toString().toLowerCase().contains(q.toLowerCase()))
            .toList();
        model.addAttribute("recetas", filtradas);
        model.addAttribute("q", q);
        return "recetas/list";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("receta", new HashMap<String, Object>());
        return "recetas/form";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Map<String, Object> receta, RedirectAttributes ra) {
        // TODO BACKEND: repository.save()
        receta.put("id", RECETAS.size() + 1);
        RECETAS.add(receta);
        ra.addFlashAttribute("success", "Receta registrada correctamente");
        return "redirect:/recetas";
    }

    @PostMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Integer id, RedirectAttribute@@s ra) {
        // TODO BACKEND: repository.deleteById()
        RECETAS.removeIf(r -> r.get("id").equals(id));
        ra.addFlashAttribute("success", "Receta eliminada");
        return "redirect:/recetas";
    }
}
```


#### Short summary: 

empty definition using pc, found symbol in pc: _empty_/RedirectAttributes#