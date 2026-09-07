package com.example.consultoriomedico.Repository;

import com.example.consultoriomedico.Model.LoteMateriaPrima;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface LoteMateriaPrimaRepository extends JpaRepository<LoteMateriaPrima, Integer> {

    /** Lotes de una materia prima específica (para ver su stock disponible). */
    List<LoteMateriaPrima> findByMateriaPrima_IdMateriaPrima(Integer idMateriaPrima);

    /** Lotes que ya vencieron y aún tienen stock (no deberían usarse en preparaciones). */
    List<LoteMateriaPrima> findByFechaVencimientoBeforeAndCantidadActualGreaterThan(
            LocalDate fecha, java.math.BigDecimal cantidad);

    /**
     * Lotes cuyo stock actual está en o por debajo del stock mínimo definido
     * a nivel de materia prima. Se usa JPQL porque compara dos campos de
     * entidades distintas (no un método derivado simple).
     */
    @Query("SELECT l FROM LoteMateriaPrima l " +
            "WHERE l.cantidadActual <= l.materiaPrima.stockMinimo")
    List<LoteMateriaPrima> findConStockBajo();

}