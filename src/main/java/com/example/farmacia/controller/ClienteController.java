package com.example.farmacia.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.*;

@Controller
@RequestMapping("/clientes")
public class ClienteController {

    private static final List<Map<String, Object>> CLIENTES = new ArrayList<>(List.of(
        Map.of("id", 1, "num_documento", "72345678", "nombres", "Juan Diego Ydrogo Fernandez"),
        Map.of("id", 2, "num_documento", "45678912", "nombres", "Maria Garcia Lopez"),
        Map.of("id", 3, "num_documento", "10234567", "nombres", "Carlos Ramirez Soto"),
        Map.of("id", 4, "num_documento", "87654321", "nombres", "Ana Lucia Torres Vega")
    ));

    @GetMapping
    public String listar(@RequestParam(required = false) String q, @RequestParam(defaultValue = "1") int page, Model model) {
        List<Map<String, Object>> filtrados = CLIENTES.stream()
            .filter(c -> q == null || q.isBlank() || c.get("nombres").toString().toLowerCase().contains(q.toLowerCase()) || c.get("num_documento").toString().contains(q))
            .toList();
        
        int pageSize = 5;
        int totalPages = Math.max(1, (int) Math.ceil(filtrados.size() / (double) pageSize));
        int actual = Math.min(Math.max(1, page), totalPages);
        int desde = Math.min((actual - 1) * pageSize, filtrados.size());
        int hasta = Math.min(desde + pageSize, filtrados.size());
        
        model.addAttribute("clientes", filtrados.subList(desde, hasta));
        model.addAttribute("q", q);
        model.addAttribute("page", actual);
        model.addAttribute("totalPages", totalPages);
        return "clientes/list";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("cliente", new HashMap<>());
        return "clientes/form";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Integer id, Model model) {
        model.addAttribute("cliente", CLIENTES.stream().filter(c -> c.get("id").equals(id)).findFirst().orElse(new HashMap<>()));
        return "clientes/form";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Map<String, Object> cliente, RedirectAttributes ra) {
        cliente.put("id", CLIENTES.size() + 1);
        CLIENTES.add(cliente);
        ra.addFlashAttribute("success", "Cliente registrado exitosamente");
        return "redirect:/clientes";
    }

    @PostMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Integer id, RedirectAttributes ra) {
        CLIENTES.removeIf(c -> c.get("id").equals(id));
        ra.addFlashAttribute("success", "Cliente eliminado");
        return "redirect:/clientes";
    }
}