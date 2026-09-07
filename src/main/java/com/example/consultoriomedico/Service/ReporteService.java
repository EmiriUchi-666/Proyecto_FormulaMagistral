package com.example.consultoriomedico.Service;

import com.example.consultoriomedico.DTO.FormulaMasVendidaDTO;
import com.example.consultoriomedico.DTO.InventarioValorizadoDTO;
import com.example.consultoriomedico.DTO.LotePorVencerDTO;
import com.example.consultoriomedico.Repository.ReporteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Capa de servicio del módulo de Reportes. Es una capa delgada:
 * toda la consulta ya viene resuelta desde las vistas SQL, así que
 * el servicio solo delega al repositorio. Se mantiene por
 * consistencia con la arquitectura en capas del resto del proyecto
 * (Controller -> Service -> Repository).
 */
@Service
public class ReporteService {

    private final ReporteRepository repository;

    public ReporteService(ReporteRepository repository) {
        this.repository = repository;
    }

    public List<LotePorVencerDTO> lotesPorVencer() {
        return repository.obtenerLotesPorVencer();
    }

    public List<FormulaMasVendidaDTO> formulasMasVendidas() {
        return repository.obtenerFormulasMasVendidas();
    }

    public List<InventarioValorizadoDTO> inventarioValorizado() {
        return repository.obtenerInventarioValorizado();
    }

}