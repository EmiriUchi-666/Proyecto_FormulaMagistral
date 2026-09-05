package com.example.farmacia.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.time.LocalDate;
import java.util.*;

@Controller
@RequestMapping("/ventas")
public class VentaController {

    // TODO BACKEND: reemplazar por repositorio
    private static final List<Map<String, Object>> VENTAS = new ArrayList<>(List.of(
        venta("V-001", "Juan Ydrogo", 85.00, "PAGADA"),
        venta("V-002", "María García", 120.50, "PENDIENTE"),
        venta("V-003", "Carlos Ramírez", 45.00, "PAGADA")));

    private static Map<String, Object> venta(String id, String cliente, double total, String estado) {
        Map<String, Object> m = new LinkedHashMap<>();
        m.put("id", id); m.put("cliente", cliente); m.put("total", total);
        m.put("estado", estado); m.put("fecha", LocalDate.now().toString());
        return m;
    }

    @GetMapping
    public String listar(@RequestParam(required = false) String q, Model model) {
        // TODO BACKEND: reemplazar por repositorio
        var filtradas = VENTAS.stream()
            .filter(v -> q == null || q.isBlank() || v.toString().toLowerCase().contains(q.toLowerCase()))
            .toList();
        model.addAttribute("ventas", filtradas);
        model.addAttribute("q", q);
        return "ventas/list";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("venta", new HashMap<String, Object>());
        model.addAttribute("clientes", List.of("Juan Ydrogo", "María García", "Carlos Ramírez"));
        return "ventas/form";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Map<String, Object> venta, RedirectAttributes ra) {
        // TODO BACKEND: repository.save()
        String id = "V-" + String.format("%03d", VENTAS.size() + 1);
        venta.put("id", id);
        VENTAS.add(venta);
        ra.addFlashAttribute("success", "Venta registrada correctamente");
        return "redirect:/ventas";
    }

    @GetMapping("/{id}/ticket")
    public String ticket(@PathVariable String id, Model model) {
        // TODO BACKEND: reemplazar por repositorio
        var v = VENTAS.stream().filter(venta -> venta.get("id").equals(id)).findFirst().orElse(new HashMap<>());
        model.addAttribute("venta", v);
        model.addAttribute("items", List.of(
            Map.of("producto", "Crema Hidratante", "cantidad", 2, "precio", 45.00),
            Map.of("producto", "Jarabe Expectorante", "cantidad", 1, "precio", 32.50)));
        return "ventas/ticket";
    }
}