package com.example.consultoriomedico.Service;

import com.example.consultoriomedico.Model.Venta;
import com.example.consultoriomedico.Repository.VentaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class VentaService {

    private final VentaRepository repository;

    public VentaService(VentaRepository repository) {
        this.repository = repository;
    }

    public List<Venta> listar() {
        return repository.findAll();
    }

    /** Historial de compras de un paciente. */
    public List<Venta> listarPorPaciente(Integer idPaciente) {
        return repository.findByPaciente_IdPaciente(idPaciente);
    }

    /** Ventas registradas entre dos fechas (ambos límites incluidos). */
    public List<Venta> buscarPorRangoFechas(LocalDateTime desde, LocalDateTime hasta) {
        return repository.findByFechaVentaBetween(desde, hasta);
    }

    /** Ventas filtradas por estado (EMITIDA / ANULADA). */
    public List<Venta> buscarPorEstado(String estado) {
        return repository.findByEstadoIgnoreCase(estado);
    }

    public Venta buscar(Integer id) {
        return repository.findById(id).orElse(null);
    }

    /** Registra la cabecera de la venta con estado inicial EMITIDA. */
    public Venta registrar(Venta venta) {
        venta.setFechaVenta(LocalDateTime.now());
        if (venta.getEstado() == null) {
            venta.setEstado("EMITIDA");
        }
        return repository.save(venta);
    }

    /** Anula una venta ya emitida (no se elimina físicamente). */
    public Venta anular(Integer id) {
        Venta venta = buscar(id);
        if (venta == null) {
            return null;
        }
        venta.setEstado("ANULADA");
        return repository.save(venta);
    }

    public void eliminar(Integer id) {
        repository.deleteById(id);
    }

}