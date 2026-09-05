package com.example.farmacia.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;
import java.util.Map;

@Controller
public class DashboardController {

    @GetMapping("/")
    public String dashboard(Model model) {
        model.addAttribute("kpiVentasHoy", 14);
        model.addAttribute("kpiIngresos", "S/ 1,250.00");
        model.addAttribute("kpiOrdenes", 6);
        model.addAttribute("kpiAlertas", 3);
        model.addAttribute("chartLabels", List.of("Lun", "Mar", "Mie", "Jue", "Vie", "Sab", "Dom"));
        model.addAttribute("chartData", List.of(320, 480, 410, 655, 890, 1250, 720));
        model.addAttribute("ultimasVentas", List.of(
            Map.of("id", "V-0007", "cliente", "Juan Ydrogo", "total", "S/ 85.00", "estado", "PAGADA"),
            Map.of("id", "V-0006", "cliente", "Maria Garcia", "total", "S/ 120.50", "estado", "PENDIENTE"),
            Map.of("id", "V-0005", "cliente", "Carlos Ramirez", "total", "S/ 45.00", "estado", "PAGADA"),
            Map.of("id", "V-0004", "cliente", "Ana Torres", "total", "S/ 210.00", "estado", "ANULADA")
        ));
        return "dashboard";
    }
}