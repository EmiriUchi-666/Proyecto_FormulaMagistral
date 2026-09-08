package com.example.consultoriomedico.WebController;

import com.example.consultoriomedico.Model.Paciente;
import com.example.consultoriomedico.Service.PacienteService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/app/clientes")
public class ClienteWebController {

    private final PacienteService pacienteService;

    public ClienteWebController(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    @GetMapping
    public String listar(@RequestParam(required = false) String q,
                          @RequestParam(defaultValue = "1") int page,
                          Model model) {
        List<Map<String, Object>> filtrados = pacienteService.listar().stream()
                .filter(p -> q == null || q.isBlank()
                        || (p.getNombres() + " " + p.getApellidos()).toLowerCase().contains(q.toLowerCase())
                        || p.getNumDocumento().contains(q))
                .map(this::aVista)
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
        model.addAttribute("cliente", new LinkedHashMap<String, Object>());
        return "clientes/form";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Integer id, Model model) {
        Paciente paciente = pacienteService.buscar(id);
        model.addAttribute("cliente", paciente != null ? aVista(paciente) : new LinkedHashMap<>());
        return "clientes/form";
    }

    @PostMapping("/guardar")
    public String guardar(@RequestParam(required = false) Integer id,
                           @RequestParam String tipo_documento,
                           @RequestParam String num_documento,
                           @RequestParam String nombres,
                           @RequestParam String apellidos,
                           @RequestParam(required = false) String telefono,
                           @RequestParam(required = false) String email,
                           RedirectAttributes ra) {

        Paciente paciente = id != null ? pacienteService.buscar(id) : new Paciente();
        if (paciente == null) {
            paciente = new Paciente();
        }
        paciente.setTipoDocumento(tipo_documento);
        paciente.setNumDocumento(num_documento);
        paciente.setNombres(nombres);
        paciente.setApellidos(apellidos);
        paciente.setTelefono(telefono);
        paciente.setEmail(email);

        pacienteService.guardar(paciente);
        ra.addFlashAttribute("success", "Cliente guardado correctamente");
        return "redirect:/app/clientes";
    }

    @PostMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Integer id, RedirectAttributes ra) {
        pacienteService.eliminar(id);
        ra.addFlashAttribute("success", "Cliente eliminado");
        return "redirect:/app/clientes";
    }

    private Map<String, Object> aVista(Paciente p) {
        Map<String, Object> m = new LinkedHashMap<>();
        m.put("id", p.getIdPaciente());
        m.put("tipo_documento", p.getTipoDocumento());
        m.put("num_documento", p.getNumDocumento());
        m.put("nombres", p.getNombres());
        m.put("apellidos", p.getApellidos());
        m.put("telefono", p.getTelefono());
        m.put("email", p.getEmail());
        return m;
    }

}
