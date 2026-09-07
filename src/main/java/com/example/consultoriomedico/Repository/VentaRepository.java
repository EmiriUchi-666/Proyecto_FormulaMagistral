package com.example.consultoriomedico.Repository;

import com.example.consultoriomedico.Model.Venta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface VentaRepository extends JpaRepository<Venta, Integer> {

    /** Historial de ventas de un paciente. */
    List<Venta> findByPaciente_IdPaciente(Integer idPaciente);

    /** Ventas dentro de un rango de fechas (ambos límites incluidos). */
    List<Venta> findByFechaVentaBetween(LocalDateTime desde, LocalDateTime hasta);

    /** Ventas filtradas por estado (ej: "EMITIDA", "ANULADA"). */
    List<Venta> findByEstadoIgnoreCase(String estado);

}