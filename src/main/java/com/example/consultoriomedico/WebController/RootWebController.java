package com.example.consultoriomedico.WebController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * La raíz del sitio (http://localhost:8080/) no tiene contenido propio
 * — todo el frontend vive bajo /app/**. Esto evita el 404
 * "No static resource ." al entrar solo con el dominio, sin ruta.
 */
@Controller
public class RootWebController {

    @GetMapping("/")
    public String raiz() {
        return "redirect:/app/login";
    }

}