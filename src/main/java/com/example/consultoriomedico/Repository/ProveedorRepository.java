package com.example.consultoriomedico.Repository;

import com.example.consultoriomedico.Model.Proveedor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProveedorRepository extends JpaRepository<Proveedor, Integer> {

    /** Búsqueda parcial e insensible a mayúsculas por nombre. */
    List<Proveedor> findByNombreContainingIgnoreCase(String nombre);

    /** Búsqueda exacta por RUC (el RUC es único, así que devuelve como mucho uno). */
    Proveedor findByRuc(String ruc);

    /** Lista solo los proveedores activos. */
    List<Proveedor> findByActivoTrue();

}