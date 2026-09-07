package com.example.consultoriomedico.Repository;

import com.example.consultoriomedico.Model.MateriaPrima;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MateriaPrimaRepository extends JpaRepository<MateriaPrima, Integer> {

    /** Lista solo las materias primas activas (activo = true). */
    List<MateriaPrima> findByActivoTrue();

    /** Búsqueda parcial e insensible a mayúsculas por nombre. */
    List<MateriaPrima> findByNombreContainingIgnoreCase(String nombre);

    /** Búsqueda exacta por tipo (ej: "excipiente", "principio activo"). */
    List<MateriaPrima> findByTipoIgnoreCase(String tipo);

}