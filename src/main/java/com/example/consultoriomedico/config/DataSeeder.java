package com.example.consultoriomedico.config;

import com.example.consultoriomedico.Model.Rol;
import com.example.consultoriomedico.Model.Usuario;
import com.example.consultoriomedico.Repository.RolRepository;
import com.example.consultoriomedico.Repository.UsuarioRepository;
import com.example.consultoriomedico.Service.UsuarioService;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Crea el rol ADMIN y un usuario de prueba (admin@farmaprep.pe / admin123)
 * la primera vez que se levanta la aplicación, para poder entrar al
 * frontend sin tener que insertar datos a mano en MySQL.
 *
 * No hace nada si ya existe al menos un usuario (no duplica datos en
 * cada reinicio).
 */
@Component
public class DataSeeder implements CommandLineRunner {

    private final RolRepository rolRepository;
    private final UsuarioRepository usuarioRepository;
    private final UsuarioService usuarioService;

    public DataSeeder(RolRepository rolRepository, UsuarioRepository usuarioRepository, UsuarioService usuarioService) {
        this.rolRepository = rolRepository;
        this.usuarioRepository = usuarioRepository;
        this.usuarioService = usuarioService;
    }

    @Override
    public void run(String... args) {
        if (!usuarioRepository.findAll().isEmpty()) {
            return;
        }

        Rol admin = rolRepository.findAll().stream()
                .filter(r -> "ADMIN".equalsIgnoreCase(r.getNombre()))
                .findFirst()
                .orElseGet(() -> {
                    Rol nuevo = new Rol();
                    nuevo.setNombre("ADMIN");
                    return rolRepository.save(nuevo);
                });

        Usuario usuario = new Usuario();
        usuario.setNombres("Administrador");
        usuario.setApellidos("Del Sistema");
        usuario.setEmail("admin@farmaprep.pe");
        usuario.setPasswordHash("admin123"); // UsuarioService.guardar() lo cifra con BCrypt
        usuario.setActivo(true);
        usuario.setRol(admin);

        usuarioService.guardar(usuario);

        System.out.println(">>> Usuario admin creado: admin@farmaprep.pe / admin123 (cámbialo luego)");
    }

}
