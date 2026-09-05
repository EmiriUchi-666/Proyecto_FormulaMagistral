error id: file:///C:/Users/USUARIO/Documents/farmacia/farmacia/src/main/java/com/example/farmacia/controller/UsuarioController.java:_empty_/RedirectAttributes#
file:///C:/Users/USUARIO/Documents/farmacia/farmacia/src/main/java/com/example/farmacia/controller/UsuarioController.java
empty definition using pc, found symbol in pc: _empty_/RedirectAttributes#
empty definition using semanticdb
empty definition using fallback
non-local guesses:

offset: 1764
uri: file:///C:/Users/USUARIO/Documents/farmacia/farmacia/src/main/java/com/example/farmacia/controller/UsuarioController.java
text:
```scala
package com.example.farmacia.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.*;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

    // TODO BACKEND: reemplazar por repositorio
    private static final List<Map<String, Object>> USUARIOS = new ArrayList<>(List.of(
        usuario(1, "admin", "Administrador", "ADMIN"),
        usuario(2, "jdrogo", "Juan Ydrogo", "USER"),
        usuario(3, "mgarcia", "María García", "USER")));

    private static Map<String, Object> usuario(int id, String username, String nombres, String rol) {
        Map<String, Object> m = new LinkedHashMap<>();
        m.put("id", id); m.put("username", username); m.put("nombres", nombres); m.put("rol", rol);
        return m;
    }

    @GetMapping
    public String listar(Model model) {
        // TODO BACKEND: reemplazar por repositorio
        model.addAttribute("usuarios", USUARIOS);
        return "usuarios/list";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("usuario", new HashMap<String, Object>());
        return "usuarios/form";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Map<String, Object> usuario, RedirectAttributes ra) {
        // TODO BACKEND: repository.save()
        usuario.put("id", USUARIOS.size() + 1);
        USUARIOS.add(usuario);
        ra.addFlashAttribute("success", "Usuario registrado correctamente");
        return "redirect:/usuarios";
    }

    @PostMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Integer id, Redirect@@Attributes ra) {
        // TODO BACKEND: repository.deleteById()
        USUARIOS.removeIf(u -> u.get("id").equals(id));
        ra.addFlashAttribute("success", "Usuario eliminado");
        return "redirect:/usuarios";
    }
}
```


#### Short summary: 

empty definition using pc, found symbol in pc: _empty_/RedirectAttributes#