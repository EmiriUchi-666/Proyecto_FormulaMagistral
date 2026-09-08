package com.example.consultoriomedico.WebController;

import com.example.consultoriomedico.Model.CitaMedica;
import com.example.consultoriomedico.Model.Paciente;
import com.example.consultoriomedico.Service.CitaMedicaService;
import com.example.consultoriomedico.Service.PacienteService;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/app/citas")
public class CitaWebController {

    private final CitaMedicaService citaService;
    private final PacienteService pacienteService;

    public CitaWebController(CitaMedicaService citaService, PacienteService pacienteService) {
        this.citaService = citaService;
        this.pacienteService = pacienteService;
    }

    @GetMapping
    public String listar(Model model) {
        List<Map<String, Object>> citas = citaService.listar().stream()
                .map(this::aVista)
                .toList();
        model.addAttribute("citas", citas);
        return "citas/list";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("pacientes", pacienteService.listar());
        return "citas/form";
    }

    @PostMapping("/guardar")
    public String guardar(@RequestParam Integer idPaciente,
                           @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fechaHora,
                           @RequestParam String motivo,
                           @RequestParam String estado,
                           RedirectAttributes ra) {

        Paciente paciente = pacienteService.buscar(idPaciente);

        CitaMedica cita = new CitaMedica();
        cita.setPaciente(paciente);
        cita.setFechaHora(fechaHora);
        cita.setMotivo(motivo);
        cita.setEstado(estado);

        citaService.guardar(cita);
        ra.addFlashAttribute("success", "Cita registrada correctamente");
        return "redirect:/app/citas";
    }

    @PostMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Integer id, RedirectAttributes ra) {
        citaService.eliminar(id);
        ra.addFlashAttribute("success", "Cita eliminada");
        return "redirect:/app/citas";
    }

    private Map<String, Object> aVista(CitaMedica c) {
        Map<String, Object> m = new LinkedHashMap<>();
        m.put("id", c.getIdCita());
        m.put("paciente", c.getPaciente() != null ? c.getPaciente().getNombres() + " " + c.getPaciente().getApellidos() : "—");
        m.put("fecha_hora", c.getFechaHora());
        m.put("motivo", c.getMotivo());
        m.put("estado", c.getEstado());
        return m;
    }

}
