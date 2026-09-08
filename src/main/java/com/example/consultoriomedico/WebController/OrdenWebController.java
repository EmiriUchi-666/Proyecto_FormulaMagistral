package com.example.consultoriomedico.WebController;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * TODO BACKEND: reemplazar por OrdenPreparacionService cuando el
 * módulo de Preparación (tabla orden_preparacion) tenga su
 * Model/Repository/Service/Controller implementado por el equipo.
 */
@Controller
@RequestMapping("/app/ordenes")
public class OrdenWebController {

    private static final List<Map<String, Object>> ORDENES = new ArrayList<>(List.of(
            orden(1, "ORD-001", "F-001", "REC-001", "Juan Ydrogo", "EN_PREPARACION"),
            orden(2, "ORD-002", "F-002", "REC-002", "María García", "PENDIENTE"),
            orden(3, "ORD-003", "F-003", "REC-003", "Carlos Ramírez", "LISTA"),
            orden(4, "ORD-004", "F-004", "REC-001", "Juan Ydrogo", "ENTREGADA")));

    private static Map<String, Object> orden(int id, String num, String formula, String receta, String cliente, String estado) {
        Map<String, Object> m = new LinkedHashMap<>();
        m.put("id", id); m.put("num_orden", num); m.put("formula", formula);
        m.put("receta", receta); m.put("cliente", cliente); m.put("estado", estado);
        return m;
    }

    @GetMapping
    public String listar(@RequestParam(required = false) String estado, Model model) {
        var filtradas = ORDENES.stream()
                .filter(o -> estado == null || estado.isBlank() || o.get("estado").equals(estado))
                .toList();
        model.addAttribute("ordenes", filtradas);
        model.addAttribute("estado", estado);
        return "ordenes/list";
    }

    @PostMapping("/cambiar-estado/{id}/{nuevoEstado}")
    public String cambiarEstado(@PathVariable Integer id, @PathVariable String nuevoEstado, RedirectAttributes ra) {
        ORDENES.stream().filter(o -> o.get("id").equals(id)).findFirst()
                .ifPresent(o -> o.put("estado", nuevoEstado));
        ra.addFlashAttribute("success", "Estado actualizado a " + nuevoEstado);
        return "redirect:/app/ordenes";
    }

}
