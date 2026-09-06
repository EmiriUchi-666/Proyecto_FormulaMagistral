package com.example.consultoriomedico.Repository;


import com.example.consultoriomedico.Model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

}