package com.example.consultoriomedico.Repository;

import com.example.consultoriomedico.Model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PacienteRepository extends JpaRepository<Paciente,Integer>{

}