package com.example.consultoriomedico.Service;

import com.example.consultoriomedico.Model.Proveedor;
import com.example.consultoriomedico.Repository.ProveedorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProveedorService {

    private final ProveedorRepository repository;

    public ProveedorService(ProveedorRepository repository) {
        this.repository = repository;
    }

    public List<Proveedor> listar() {
        return repository.findAll();
    }

    /** Lista solo los proveedores activos (para selects/combos). */
    public List<Proveedor> listarActivos() {
        return repository.findByActivoTrue();
    }

    /** Búsqueda por coincidencia parcial de nombre (ej: "distrib"). */
    public List<Proveedor> buscarPorNombre(String nombre) {
        return repository.findByNombreContainingIgnoreCase(nombre);
    }

    /** Búsqueda exacta por RUC. */
    public Proveedor buscarPorRuc(String ruc) {
        return repository.findByRuc(ruc);
    }

    public Proveedor guardar(Proveedor proveedor) {
        if (proveedor.getActivo() == null) {
            proveedor.setActivo(true);
        }
        return repository.save(proveedor);
    }

    public Proveedor buscar(Integer id) {
        return repository.findById(id).orElse(null);
    }

    /** Baja lógica, igual que en MateriaPrima. */
    public void desactivar(Integer id) {
        Proveedor proveedor = buscar(id);
        if (proveedor != null) {
            proveedor.setActivo(false);
            repository.save(proveedor);
        }
    }

    public void eliminar(Integer id) {
        repository.deleteById(id);
    }

}