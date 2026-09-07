package com.example.consultoriomedico.Service;

import com.example.consultoriomedico.Model.LoteMateriaPrima;
import com.example.consultoriomedico.Repository.LoteMateriaPrimaRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
public class LoteMateriaPrimaService {

    private final LoteMateriaPrimaRepository repository;

    public LoteMateriaPrimaService(LoteMateriaPrimaRepository repository) {
        this.repository = repository;
    }

    public List<LoteMateriaPrima> listar() {
        return repository.findAll();
    }

    /** Lotes de una materia prima puntual, para ver su stock disponible. */
    public List<LoteMateriaPrima> listarPorMateriaPrima(Integer idMateriaPrima) {
        return repository.findByMateriaPrima_IdMateriaPrima(idMateriaPrima);
    }

    /** Lotes ya vencidos que todavía tienen stock (para descartar/dar de baja). */
    public List<LoteMateriaPrima> listarVencidos() {
        return repository.findByFechaVencimientoBeforeAndCantidadActualGreaterThan(
                LocalDate.now(), BigDecimal.ZERO);
    }

    /** Lotes cuyo stock actual ya llegó al mínimo definido para la materia prima. */
    public List<LoteMateriaPrima> listarConStockBajo() {
        return repository.findConStockBajo();
    }

    public LoteMateriaPrima guardar(LoteMateriaPrima lote) {
        return repository.save(lote);
    }

    public LoteMateriaPrima buscar(Integer id) {
        return repository.findById(id).orElse(null);
    }

    public void eliminar(Integer id) {
        repository.deleteById(id);
    }

}