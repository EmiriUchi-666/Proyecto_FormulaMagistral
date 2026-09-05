error id: file:///C:/Users/USUARIO/Documents/farmacia/farmacia/src/main/java/com/example/farmacia/controller/ClienteController.java:_empty_/PostMapping#
file:///C:/Users/USUARIO/Documents/farmacia/farmacia/src/main/java/com/example/farmacia/controller/ClienteController.java
empty definition using pc, found symbol in pc: _empty_/PostMapping#
empty definition using semanticdb
empty definition using fallback
non-local guesses:

offset: 2749
uri: file:///C:/Users/USUARIO/Documents/farmacia/farmacia/src/main/java/com/example/farmacia/controller/ClienteController.java
text:
```scala
package com.example.farmacia.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.*;

@Controller
@RequestMapping("/clientes")
public class ClienteController {

    // TODO BACKEND: reemplazar por repositorio
    private static final List<Map<String, Object>> CLIENTES = new ArrayList<>(List.of(
        cliente(1, "72345678", "Juan Diego Ydrogo Fernández"),
        cliente(2, "45678912", "María García López"),
        cliente(3, "10234567", "Carlos Ramírez Soto"),
        cliente(4, "87654321", "Ana Lucía Torres Vega"),
        cliente(5, "23456789", "Pedro Pablo Quispe Huanca"),
        cliente(6, "34567890", "Rosa María Chávez Díaz"),
        cliente(7, "56789012", "Luis Alberto Núñez Prado")));

    private static Map<String, Object> cliente(int id, String doc, String nombres) {
        Map<String, Object> m = new LinkedHashMap<>();
        m.put("id", id); m.put("num_documento", doc); m.put("nombres", nombres);
        return m;
    }

    @GetMapping
    public String listar(@RequestParam(required = false) String q,
                         @RequestParam(defaultValue = "1") int page, Model model) {
        // TODO BACKEND: reemplazar por repositorio (búsqueda + paginación real)
        List<Map<String, Object>> filtrados = CLIENTES.stream()
            .filter(c -> q == null || q.isBlank()
 c.get("nombres").toString().toLowerCase().contains(q.toLowerCase())
 c.get("num_documento").toString().contains(q))
            .toList();
        int pageSize = 5;
        int totalPages = Math.max(1, (int) Math.ceil(filtrados.size() / (double) pageSize));
        int actual = Math.min(Math.max(1, page), totalPages);
        int desde = Math.min((actual - 1) * pageSize, filtrados.size());
        int hasta = Math.min(desde + pageSize, filtrados.size());
        model.addAttribute("clientes", filtrados.subList(desde, hasta));
        model.addAttribute("q", q); model.addAttribute("page", actual);
        model.addAttribute("totalPages", totalPages);
        return "clientes/list";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("cliente", new HashMap<String, Object>());
        return "clientes/form";
    }

       @GetMapping("/editar/{id}")
    public String editar(@PathVariable Integer id, Model model) {
        // TODO BACKEND: reemplazar por repositorio
        model.addAttribute("cliente", CLIENTES.stream()
            .filter(c -> c.get("id").equals(id)).findFirst().orElse(new HashMap<>()));
        return "clientes/form";
    }

    @P@@ostMapping("/guardar")
```


#### Short summary: 

empty definition using pc, found symbol in pc: _empty_/PostMapping#