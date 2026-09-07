package com.example.consultoriomedico.Service;

import com.example.consultoriomedico.Model.VentaDetalle;
import com.example.consultoriomedico.Repository.VentaDetalleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Lógica de negocio de las líneas de detalle de venta. El subtotal
 * de cada línea se calcula aquí (cantidad * precio_unitario) en vez
 * de confiar en que el cliente lo envíe correctamente.
 */
@Service
public class VentaDetalleService {

    private final VentaDetalleRepository repository;

    public VentaDetalleService(VentaDetalleRepository repository) {
        this.repository = repository;
    }

    public List<VentaDetalle> listar() {
        return repository.findAll();
    }

    /** Líneas de una venta específica. */
    public List<VentaDetalle> listarPorVenta(Integer idVenta) {
        return repository.findByVenta_IdVenta(idVenta);
    }

    public VentaDetalle buscar(Integer id) {
        return repository.findById(id).orElse(null);
    }

    public VentaDetalle guardar(VentaDetalle detalle) {
        detalle.setSubtotal(detalle.getCantidad().multiply(detalle.getPrecioUnitario()));
        return repository.save(detalle);
    }

    public void eliminar(Integer id) {
        repository.deleteById(id);
    }

}