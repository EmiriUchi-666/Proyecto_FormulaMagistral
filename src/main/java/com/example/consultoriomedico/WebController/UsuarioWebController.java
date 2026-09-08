package com.example.consultoriomedico.WebController;

import com.example.consultoriomedico.Model.Rol;
import com.example.consultoriomedico.Model.Usuario;
import com.example.consultoriomedico.Service.RolService;
import com.example.consultoriomedico.Service.UsuarioService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/app/usuarios")
public class UsuarioWebController {

    private final UsuarioService usuarioService;
    private final RolService rolService;

    public UsuarioWebController(UsuarioService usuarioService, RolService rolService) {
        this.usuarioService = usuarioService;
        this.rolService = rolService;
    }

    @GetMapping
    public String listar(Model model) {
        List<Map<String, Object>> usuarios = usuarioService.listar().stream()
                .map(this::aVista)
                .toList();
        model.addAttribute("usuarios", usuarios);
        return "usuarios/list";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("roles", rolService.listar());
        return "usuarios/form";
    }

    @PostMapping("/guardar")
    public String guardar(@RequestParam String nombres,
                           @RequestParam String apellidos,
                           @RequestParam String email,
                           @RequestParam String password,
                           @RequestParam Integer idRol,
                           RedirectAttributes ra) {

        Rol rol = rolService.buscar(idRol);

        Usuario usuario = new Usuario();
        usuario.setNombres(nombres);
        usuario.setApellidos(apellidos);
        usuario.setEmail(email);
        usuario.setPasswordHash(password); // UsuarioService.guardar() lo cifra con BCrypt
        usuario.setActivo(true);
        usuario.setRol(rol);

        usuarioService.guardar(usuario);
        ra.addFlashAttribute("success", "Usuario registrado correctamente");
        return "redirect:/app/usuarios";
    }

    @PostMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Integer id, RedirectAttributes ra) {
        usuarioService.eliminar(id);
        ra.addFlashAttribute("success", "Usuario eliminado");
        return "redirect:/app/usuarios";
    }

    private Map<String, Object> aVista(Usuario u) {
        Map<String, Object> m = new LinkedHashMap<>();
        m.put("id", u.getIdUsuario());
        m.put("email", u.getEmail());
        m.put("nombres", u.getNombres() + " " + u.getApellidos());
        m.put("rol", u.getRol() != null ? u.getRol().getNombre() : "—");
        return m;
    }

}
