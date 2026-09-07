package com.example.consultoriomedico.Repository;

import com.example.consultoriomedico.Model.MovimientoInventario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovimientoInventarioRepository extends JpaRepository<MovimientoInventario, Integer> {

    /** Historial de movimientos de un lote (kardex del lote). */
    List<MovimientoInventario> findByLote_IdLoteOrderByFechaDesc(Integer idLote);

}