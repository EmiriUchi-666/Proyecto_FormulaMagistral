package com.example.consultoriomedico.Repository;


import com.example.consultoriomedico.Model.Receta;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RecetaRepository extends JpaRepository<Receta,Integer> {

}