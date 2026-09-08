package com.example.consultoriomedico.WebController;

import com.example.consultoriomedico.Model.LoteMateriaPrima;
import com.example.consultoriomedico.Service.LoteMateriaPrimaService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/app/inventario")
public class InventarioWebController {

    private final LoteMateriaPrimaService loteService;

    public InventarioWebController(LoteMateriaPrimaService loteService) {
        this.loteService = loteService;
    }

    @GetMapping
    public String listar(Model model) {
        List<Map<String, Object>> lotes = loteService.listar().stream()
                .map(this::aVista)
                .toList();
        model.addAttribute("lotes", lotes);
        return "inventario/list";
    }

    private Map<String, Object> aVista(LoteMateriaPrima l) {
        Map<String, Object> m = new LinkedHashMap<>();
        m.put("id", l.getCodigoLote());
        m.put("materia", l.getMateriaPrima() != null ? l.getMateriaPrima().getNombre() : "—");
        m.put("vencimiento", l.getFechaVencimiento());
        m.put("stock", l.getCantidadActual() != null ? l.getCantidadActual().intValue() : 0);
        m.put("max", l.getCantidadInicial() != null ? l.getCantidadInicial().intValue() : 1);
        return m;
    }

}
