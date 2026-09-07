package com.example.consultoriomedico.Repository;

import com.example.consultoriomedico.Model.MovimientoInventario;
import org.springframework.data.jpa.repository.JpaRepository;


public interface MovimientoInventarioRepository 
        extends JpaRepository<MovimientoInventario,Integer> {

}