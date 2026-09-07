package com.example.consultoriomedico.DTO;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * DTO de solo lectura para el reporte "lotes por vencer".
 * Se llena a partir de la vista SQL "vw_lotes_por_vencer".
 */
public record LotePorVencerDTO(
        String materiaPrima,
        String codigoLote,
        LocalDate fechaVencimiento,
        BigDecimal cantidadActual
) {
}