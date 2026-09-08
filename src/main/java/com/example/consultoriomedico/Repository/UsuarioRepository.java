package com.example.consultoriomedico.Repository;


import com.example.consultoriomedico.Model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

<<<<<<< HEAD
    /** Usado por el login real (Spring Security busca al usuario por su email). */
    Usuario findByEmail(String email);

=======
>>>>>>> 4aa01638f118a77226d52029b8c22d2f96991c71
}