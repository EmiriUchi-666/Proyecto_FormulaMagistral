package com.example.consultoriomedico.Service;

import com.example.consultoriomedico.Model.LoteMateriaPrima;
import com.example.consultoriomedico.Model.MovimientoInventario;
import com.example.consultoriomedico.Repository.LoteMateriaPrimaRepository;
import com.example.consultoriomedico.Repository.MovimientoInventarioRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Lógica de negocio de los movimientos de inventario. Es el servicio
 * más importante del módulo de Inventario porque, además de guardar
 * el movimiento, actualiza el stock (cantidad_actual) del lote:
 *
 *  - ENTRADA: incrementa la cantidad_actual del lote.
 *  - SALIDA: valida que haya stock suficiente y luego lo descuenta.
 *
 * Esta regla ("no permitir una salida sin stock suficiente") es la
 * que se valida en las pruebas unitarias del servicio.
 */
@Service
public class MovimientoInventarioService {

    private final MovimientoInventarioRepository movimientoRepository;
    private final LoteMateriaPrimaRepository loteRepository;

    public MovimientoInventarioService(MovimientoInventarioRepository movimientoRepository,
                                        LoteMateriaPrimaRepository loteRepository) {
        this.movimientoRepository = movimientoRepository;
        this.loteRepository = loteRepository;
    }

    public List<MovimientoInventario> listar() {
        return movimientoRepository.findAll();
    }

    /** Kardex: historial de movimientos de un lote, del más reciente al más antiguo. */
    public List<MovimientoInventario> listarPorLote(Integer idLote) {
        return movimientoRepository.findByLote_IdLoteOrderByFechaDesc(idLote);
    }

    public MovimientoInventario buscar(Integer id) {
        return movimientoRepository.findById(id).orElse(null);
    }

    /**
     * Registra un movimiento de inventario y actualiza el stock del lote.
     *
     * @throws IllegalArgumentException si el lote no existe.
     * @throws IllegalStateException si es una SALIDA y no hay stock suficiente.
     */
    public MovimientoInventario registrar(MovimientoInventario movimiento) {
        LoteMateriaPrima lote = loteRepository.findById(movimiento.getLote().getIdLote())
                .orElseThrow(() -> new IllegalArgumentException("El lote indicado no existe"));

        BigDecimal cantidad = movimiento.getCantidad();

        if ("SALIDA".equalsIgnoreCase(movimiento.getTipoMovimiento())) {
            if (lote.getCantidadActual().compareTo(cantidad) < 0) {
                throw new IllegalStateException("Stock insuficiente en el lote " + lote.getCodigoLote());
            }
            lote.setCantidadActual(lote.getCantidadActual().subtract(cantidad));
        } else if ("ENTRADA".equalsIgnoreCase(movimiento.getTipoMovimiento())) {
            lote.setCantidadActual(lote.getCantidadActual().add(cantidad));
        } else {
            throw new IllegalArgumentException("tipo_movimiento debe ser ENTRADA o SALIDA");
        }

        loteRepository.save(lote);

        movimiento.setLote(lote);
        movimiento.setFecha(LocalDateTime.now());
        return movimientoRepository.save(movimiento);
    }

}