package com.example.consultoriomedico.config;

import com.example.consultoriomedico.Model.Usuario;
import com.example.consultoriomedico.Repository.UsuarioRepository;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Conecta el login del frontend (Thymeleaf) con la tabla real "usuario".
 *
 * Spring Security llama a loadUserByUsername() con lo que el usuario
 * escribió en el campo "username" del formulario de login — en este
 * proyecto ese campo en realidad contiene el EMAIL (no hay columna
 * "username" en la tabla usuario, así que se reutiliza el email como
 * identificador de acceso).
 *
 * La contraseña se compara contra "password_hash", que ya viene
 * cifrada con BCrypt porque UsuarioService la cifra antes de guardar.
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UsuarioRepository repository;

    public CustomUserDetailsService(UsuarioRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario usuario = repository.findByEmail(email);

        if (usuario == null) {
            throw new UsernameNotFoundException("No existe un usuario con el email: " + email);
        }

        if (Boolean.FALSE.equals(usuario.getActivo())) {
            throw new UsernameNotFoundException("El usuario está desactivado: " + email);
        }

        String rolNombre = usuario.getRol() != null ? usuario.getRol().getNombre() : "USER";

        return User.withUsername(usuario.getEmail())
                .password(usuario.getPasswordHash())
                .authorities(List.of(new SimpleGrantedAuthority("ROLE_" + rolNombre)))
                .build();
    }

}
