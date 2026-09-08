package com.example.consultoriomedico.Repository;


import com.example.consultoriomedico.Model.CitaMedica;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CitaMedicaRepository 
        extends JpaRepository<CitaMedica,Integer> {


}