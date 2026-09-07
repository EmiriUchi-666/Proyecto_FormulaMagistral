package com.example.consultoriomedico.Service;

import com.example.consultoriomedico.Model.MateriaPrima;
import com.example.consultoriomedico.Repository.MateriaPrimaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MateriaPrimaService {

    private final MateriaPrimaRepository repository;

    public MateriaPrimaService(MateriaPrimaRepository repository) {
        this.repository = repository;
    }

    public List<MateriaPrima> listar() {
        return repository.findAll();
    }

    /** Lista solo las materias primas activas (para selects/combos del frontend). */
    public List<MateriaPrima> listarActivas() {
        return repository.findByActivoTrue();
    }

    /** Búsqueda por coincidencia parcial de nombre. */
    public List<MateriaPrima> buscarPorNombre(String nombre) {
        return repository.findByNombreContainingIgnoreCase(nombre);
    }

    /** Búsqueda exacta por tipo. */
    public List<MateriaPrima> buscarPorTipo(String tipo) {
        return repository.findByTipoIgnoreCase(tipo);
    }

    public MateriaPrima guardar(MateriaPrima materiaPrima) {
        return repository.save(materiaPrima);
    }

    public MateriaPrima buscar(Integer id) {
        return repository.findById(id).orElse(null);
    }

    /** Baja lógica: en vez de borrar, se marca como inactiva. */
    public void desactivar(Integer id) {
        MateriaPrima materiaPrima = buscar(id);
        if (materiaPrima != null) {
            materiaPrima.setActivo(false);
            repository.save(materiaPrima);
        }
    }

    public void eliminar(Integer id) {
        repository.deleteById(id);
    }

}
