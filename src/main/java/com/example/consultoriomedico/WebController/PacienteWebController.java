package com.example.consultoriomedico.WebController;


import com.example.consultoriomedico.Model.Paciente;
import com.example.consultoriomedico.Service.PacienteService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class PacienteWebController {


    private final PacienteService service;


    public PacienteWebController(PacienteService service){
        this.service = service;
    }



    // LISTAR PACIENTES
    @GetMapping("/app/pacientes")
    public String listar(Model model){

        model.addAttribute("pacientes", service.listar());

        return "pacientes/list";
    }



    // FORMULARIO NUEVO PACIENTE
    @GetMapping("/app/pacientes/nuevo")
    public String nuevo(Model model){

        model.addAttribute("paciente", new Paciente());

        return "pacientes/form";
    }



    // GUARDAR PACIENTE
    @PostMapping("/app/pacientes/guardar")
    public String guardar(
            @ModelAttribute("paciente") Paciente paciente){

        service.guardar(paciente);

        return "redirect:/app/pacientes";
    }



    // FORMULARIO EDITAR
    @GetMapping("/app/pacientes/editar/{id}")
    public String editar(
            @PathVariable Integer id,
            Model model){

        Paciente paciente = service.buscar(id);

        model.addAttribute("paciente", paciente);

        return "pacientes/form";
    }



    // ELIMINAR PACIENTE
    @GetMapping("/app/pacientes/eliminar/{id}")
    public String eliminar(
            @PathVariable Integer id){

        service.eliminar(id);

        return "redirect:/app/pacientes";
    }

}