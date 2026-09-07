package com.example.consultoriomedico.DTO;

import java.math.BigDecimal;

/**
 * DTO de solo lectura para el reporte "fórmulas más vendidas".
 * Se llena a partir de la vista SQL "vw_formulas_mas_vendidas".
 */
public record FormulaMasVendidaDTO(
        String formula,
        BigDecimal unidadesVendidas,
        BigDecimal ingresoTotal
) {
}