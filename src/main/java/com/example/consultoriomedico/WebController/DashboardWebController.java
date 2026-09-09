package com.example.consultoriomedico.WebController;

import com.example.consultoriomedico.Model.Venta;
import com.example.consultoriomedico.Service.LoteMateriaPrimaService;
import com.example.consultoriomedico.Service.VentaService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Panel principal. Los KPIs de Ventas/Ingresos/Alertas de inventario
 * son reales (calculados a partir de VentaService y
 * LoteMateriaPrimaService). "Órdenes Activas" queda en 0 porque el
 * módulo de Preparación (orden_preparacion) todavía no existe.
 */
@Controller
public class DashboardWebController {

    private static final String[] DIAS_CORTOS = {"Lun", "Mar", "Mié", "Jue", "Vie", "Sáb", "Dom"};

    private final VentaService ventaService;
    private final LoteMateriaPrimaService loteMateriaPrimaService;

    public DashboardWebController(VentaService ventaService, LoteMateriaPrimaService loteMateriaPrimaService) {
        this.ventaService = ventaService;
        this.loteMateriaPrimaService = loteMateriaPrimaService;
    }

    @GetMapping("/app/")
    public String dashboard(Model model) {
        List<Venta> todasLasVentas = ventaService.listar();
        LocalDate hoy = LocalDate.now();

        List<Venta> ventasHoy = todasLasVentas.stream()
                .filter(v -> v.getFechaVenta() != null && v.getFechaVenta().toLocalDate().isEqual(hoy))
                .toList();

        BigDecimal ingresosHoy = ventasHoy.stream()
                .map(Venta::getTotal)
                .filter(java.util.Objects::nonNull)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        int alertas = loteMateriaPrimaService.listarVencidos().size()
                + loteMateriaPrimaService.listarConStockBajo().size();

        model.addAttribute("kpiVentasHoy", ventasHoy.size());
        model.addAttribute("kpiIngresos", "S/ " + ingresosHoy);
        model.addAttribute("kpiOrdenes", 0);
        model.addAttribute("kpiAlertas", alertas);

        // Ventas de los últimos 7 días (incluyendo hoy), para el gráfico
        List<String> chartLabels = new ArrayList<>();
        List<BigDecimal> chartData = new ArrayList<>();
        for (int i = 6; i >= 0; i--) {
            LocalDate dia = hoy.minusDays(i);
            chartLabels.add(DIAS_CORTOS[dia.getDayOfWeek().getValue() - 1]);
            BigDecimal totalDia = todasLasVentas.stream()
                    .filter(v -> v.getFechaVenta() != null && v.getFechaVenta().toLocalDate().isEqual(dia))
                    .map(Venta::getTotal)
                    .filter(java.util.Objects::nonNull)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
            chartData.add(totalDia);
        }
        model.addAttribute("chartLabels", chartLabels);
        model.addAttribute("chartData", chartData);

        List<Map<String, Object>> ultimasVentas = todasLasVentas.stream()
                .sorted((a, b) -> Integer.compare(b.getIdVenta(), a.getIdVenta()))
                .limit(5)
                .map(this::aVistaVenta)
                .toList();
        model.addAttribute("ultimasVentas", ultimasVentas);

        return "dashboard";
    }

    private Map<String, Object> aVistaVenta(Venta v) {

    Map<String, Object> m = new LinkedHashMap<>();

    m.put("id", v.getIdVenta());

    m.put("paciente",
            v.getPaciente() != null
            ? v.getPaciente().getNombres() + " " + v.getPaciente().getApellidos()
            : "Sin paciente");

    m.put("total", v.getTotal());

    m.put("estado",
            v.getEstado() != null
            ? v.getEstado()
            : "SIN ESTADO");

    return m;
}

}
