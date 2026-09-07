package com.example.consultoriomedico.Repository;

import com.example.consultoriomedico.Model.VentaDetalle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VentaDetalleRepository extends JpaRepository<VentaDetalle, Integer> {

    /** Líneas de detalle de una venta específica. */
    List<VentaDetalle> findByVenta_IdVenta(Integer idVenta);

}