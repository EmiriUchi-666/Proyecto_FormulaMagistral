package com.example.consultoriomedico.DTO;

import java.math.BigDecimal;

/**
 * DTO de solo lectura para el reporte "inventario valorizado".
 * Se llena a partir de la vista SQL "vw_inventario_valorizado".
 */
public record InventarioValorizadoDTO(
        String materiaPrima,
        BigDecimal stockActual,
        BigDecimal valorTotal
) {
}