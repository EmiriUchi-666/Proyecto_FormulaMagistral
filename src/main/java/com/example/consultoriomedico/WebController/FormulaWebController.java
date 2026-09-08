package com.example.consultoriomedico.WebController;

import com.example.consultoriomedico.Model.CategoriaFormula;
import com.example.consultoriomedico.Model.FormulaMagistral;
import com.example.consultoriomedico.Service.CategoriaFormulaService;
import com.example.consultoriomedico.Service.FormulaMagistralService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/app/formulas")
public class FormulaWebController {

    private final FormulaMagistralService formulaService;
    private final CategoriaFormulaService categoriaService;

    public FormulaWebController(FormulaMagistralService formulaService, CategoriaFormulaService categoriaService) {
        this.formulaService = formulaService;
        this.categoriaService = categoriaService;
    }

    @GetMapping
    public String listar(@RequestParam(required = false) String q, Model model) {
        List<Map<String, Object>> filtradas = formulaService.listar().stream()
                .filter(f -> q == null || q.isBlank() || f.getNombre().toLowerCase().contains(q.toLowerCase()))
                .map(this::aVista)
                .toList();
        model.addAttribute("formulas", filtradas);
        model.addAttribute("q", q);
        return "formulas/list";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("categorias", categoriaService.listar());
        return "formulas/form";
    }

    @PostMapping("/guardar")
    public String guardar(@RequestParam String nombre,
                           @RequestParam Integer idCategoria,
                           @RequestParam(required = false) String presentacion,
                           @RequestParam BigDecimal precioVenta,
                           @RequestParam(required = false) Boolean requiereReceta,
                           RedirectAttributes ra) {

        CategoriaFormula categoria = categoriaService.buscar(idCategoria);

        FormulaMagistral formula = new FormulaMagistral();
        formula.setNombre(nombre);
        formula.setCategoria(categoria);
        formula.setPresentacion(presentacion);
        formula.setPrecioVenta(precioVenta);
        formula.setRequiereReceta(requiereReceta != null && requiereReceta);
        formula.setActivo(true);

        formulaService.guardar(formula);
        ra.addFlashAttribute("success", "Fórmula registrada correctamente");
        return "redirect:/app/formulas";
    }

    @PostMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Integer id, RedirectAttributes ra) {
        formulaService.eliminar(id);
        ra.addFlashAttribute("success", "Fórmula eliminada");
        return "redirect:/app/formulas";
    }

    private Map<String, Object> aVista(FormulaMagistral f) {
        Map<String, Object> m = new LinkedHashMap<>();
        m.put("id", f.getIdFormula());
        m.put("nombre", f.getNombre());
        m.put("categoria", f.getCategoria() != null ? f.getCategoria().getNombre() : "—");
        m.put("precio_venta", f.getPrecioVenta());
        return m;
    }

}
