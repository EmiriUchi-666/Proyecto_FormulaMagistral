package com.example.consultoriomedico.WebController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Muestra la pantalla de login. El procesamiento real del POST /app/login
 * (validar credenciales) lo hace Spring Security, no este controller
 * (ver SecurityConfig.loginProcessingUrl).
 */
@Controller
public class AuthWebController {

    @GetMapping("/app/login")
    public String login() {
        return "login";
    }

}
