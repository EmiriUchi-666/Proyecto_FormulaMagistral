package com.example.consultoriomedico.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Seguridad combinada del proyecto:
 *
 *  - La API REST (/proveedores, /materias-primas, /ventas, /reportes,
 *    etc.) queda SIN autenticación, porque el entregable del curso se
 *    prueba con Postman directamente.
 *  - El frontend (Thymeleaf, todo bajo /app/**) SÍ pide login real
 *    contra la tabla "usuario" (ver CustomUserDetailsService).
 *
 * Si más adelante el entregable exige proteger también la API,
 * basta con quitar sus rutas de permitAll() aquí.
 */
@Configuration
public class SecurityConfig {

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
          .csrf(csrf -> csrf.ignoringRequestMatchers(
              // La API REST no usa formularios HTML, así que no necesita token CSRF.
              "/proveedores/**", "/unidades-medida/**", "/materias-primas/**",
              "/lotes-materia-prima/**", "/movimientos-inventario/**",
              "/ventas/**", "/ventas-detalle/**", "/reportes/**",
              "/pacientes/**", "/recetas/**", "/citas/**", "/roles/**", "/usuarios/**",
              "/categorias-formula/**", "/formulas/**", "/formula-ingredientes/**"
          ))
          .authorizeHttpRequests(auth -> auth
              // Recursos estáticos y login del frontend: libres.
              .requestMatchers("/css/**", "/js/**", "/app/login").permitAll()
              // Toda la API REST: libre (se prueba con Postman).
              .requestMatchers(
                  "/proveedores/**", "/unidades-medida/**", "/materias-primas/**",
                  "/lotes-materia-prima/**", "/movimientos-inventario/**",
                  "/ventas/**", "/ventas-detalle/**", "/reportes/**",
                  "/pacientes/**", "/recetas/**", "/citas/**", "/roles/**", "/usuarios/**",
                  "/categorias-formula/**", "/formulas/**", "/formula-ingredientes/**"
              ).permitAll()
              // El resto del frontend (todo lo que cuelga de /app/**) sí requiere login.
              .requestMatchers("/app/**").authenticated()
              .anyRequest().permitAll()
          )
          .formLogin(form -> form
              .loginPage("/app/login")
              .loginProcessingUrl("/app/login")
              .defaultSuccessUrl("/app/", true)
              .permitAll()
          )
          .logout(logout -> logout
              .logoutUrl("/app/logout")
              .logoutSuccessUrl("/app/login?logout")
              .permitAll()
          );

        return http.build();
    }

}