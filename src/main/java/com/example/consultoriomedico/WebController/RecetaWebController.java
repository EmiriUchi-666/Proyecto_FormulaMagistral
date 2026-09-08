package com.example.consultoriomedico.WebController;

import com.example.consultoriomedico.Model.CitaMedica;
import com.example.consultoriomedico.Model.Paciente;
import com.example.consultoriomedico.Model.Receta;
import com.example.consultoriomedico.Service.CitaMedicaService;
import com.example.consultoriomedico.Service.PacienteService;
import com.example.consultoriomedico.Service.RecetaService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/app/recetas")
public class RecetaWebController {

    private final RecetaService recetaService;
    private final PacienteService pacienteService;
    private final CitaMedicaService citaMedicaService;

    public RecetaWebController(RecetaService recetaService, PacienteService pacienteService, CitaMedicaService citaMedicaService) {
        this.recetaService = recetaService;
        this.pacienteService = pacienteService;
        this.citaMedicaService = citaMedicaService;
    }

    @GetMapping
    public String listar(@RequestParam(required = false) String q, Model model) {
        List<Map<String, Object>> filtradas = recetaService.listar().stream()
                .filter(r -> q == null || q.isBlank() || (r.getMedico() != null && r.getMedico().toLowerCase().contains(q.toLowerCase())))
                .map(this::aVista)
                .toList();
        model.addAttribute("recetas", filtradas);
        model.addAttribute("q", q);
        return "recetas/list";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("clientes", pacienteService.listar());
        model.addAttribute("citas", citaMedicaService.listar());
        return "recetas/form";
    }

    @PostMapping("/guardar")
    public String guardar(@RequestParam Integer idPaciente,
                           @RequestParam String medico,
                           @RequestParam(required = false) String numColegiatura,
                           @RequestParam LocalDate fechaEmision,
                           @RequestParam(required = false) Integer idCita,
                           RedirectAttributes ra) {

        Paciente paciente = pacienteService.buscar(idPaciente);

        Receta receta = new Receta();
        receta.setPaciente(paciente);
        receta.setMedico(medico);
        receta.setNumColegiatura(numColegiatura);
        receta.setFechaEmision(fechaEmision);
        if (idCita != null) {
            CitaMedica cita = citaMedicaService.buscar(idCita);
            receta.setCitaMedica(cita);
        }

        recetaService.guardar(receta);
        ra.addFlashAttribute("success", "Receta registrada correctamente");
        return "redirect:/app/recetas";
    }

    @PostMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Integer id, RedirectAttributes ra) {
        recetaService.eliminar(id);
        ra.addFlashAttribute("success", "Receta eliminada");
        return "redirect:/app/recetas";
    }

    private Map<String, Object> aVista(Receta r) {
        Map<String, Object> m = new LinkedHashMap<>();
        m.put("id", r.getIdReceta());
        m.put("cliente", r.getPaciente() != null ? r.getPaciente().getNombres() + " " + r.getPaciente().getApellidos() : "—");
        m.put("medico", r.getMedico());
        m.put("fecha_emision", r.getFechaEmision());
        return m;
    }

}
