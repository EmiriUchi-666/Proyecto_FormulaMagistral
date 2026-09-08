package com.example.consultoriomedico.Repository;


import com.example.consultoriomedico.Model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    /** Usado por el login real (Spring Security busca al usuario por su email). */
    Usuario findByEmail(String email);

}