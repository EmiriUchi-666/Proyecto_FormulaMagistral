package com.example.consultoriomedico.Repository;

import com.example.consultoriomedico.DTO.FormulaMasVendidaDTO;
import com.example.consultoriomedico.DTO.InventarioValorizadoDTO;
import com.example.consultoriomedico.DTO.LotePorVencerDTO;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repositorio del módulo de Reportes.
 *
 * A diferencia de los demás repositorios, este NO extiende
 * JpaRepository: las vistas SQL (vw_lotes_por_vencer,
 * vw_formulas_mas_vendidas, vw_inventario_valorizado) no tienen
 * llave primaria, por lo que no se pueden mapear como @Entity de
 * forma natural. En su lugar, se usa JdbcTemplate para ejecutar la
 * consulta contra la vista y mapear cada fila a un DTO inmutable.
 *
 * JdbcTemplate ya está disponible automáticamente porque
 * spring-boot-starter-data-jpa incluye spring-jdbc como dependencia
 * transitiva; no hace falta agregar nada al pom.xml.
 */
@Repository
public class ReporteRepository {

    private final JdbcTemplate jdbcTemplate;

    public ReporteRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<LotePorVencerDTO> obtenerLotesPorVencer() {
        String sql = "SELECT materia_prima, codigo_lote, fecha_vencimiento, cantidad_actual " +
                "FROM vw_lotes_por_vencer";

        return jdbcTemplate.query(sql, (rs, rowNum) -> new LotePorVencerDTO(
                rs.getString("materia_prima"),
                rs.getString("codigo_lote"),
                rs.getDate("fecha_vencimiento").toLocalDate(),
                rs.getBigDecimal("cantidad_actual")
        ));
    }

    public List<FormulaMasVendidaDTO> obtenerFormulasMasVendidas() {
        String sql = "SELECT formula, unidades_vendidas, ingreso_total " +
                "FROM vw_formulas_mas_vendidas";

        return jdbcTemplate.query(sql, (rs, rowNum) -> new FormulaMasVendidaDTO(
                rs.getString("formula"),
                rs.getBigDecimal("unidades_vendidas"),
                rs.getBigDecimal("ingreso_total")
        ));
    }

    public List<InventarioValorizadoDTO> obtenerInventarioValorizado() {
        String sql = "SELECT materia_prima, stock_actual, valor_total " +
                "FROM vw_inventario_valorizado";

        return jdbcTemplate.query(sql, (rs, rowNum) -> new InventarioValorizadoDTO(
                rs.getString("materia_prima"),
                rs.getBigDecimal("stock_actual"),
                rs.getBigDecimal("valor_total")
        ));
    }

}