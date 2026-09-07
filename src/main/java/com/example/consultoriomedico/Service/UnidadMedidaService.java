package com.example.consultoriomedico.Service;

import com.example.consultoriomedico.Model.UnidadMedida;
import com.example.consultoriomedico.Repository.UnidadMedidaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UnidadMedidaService {

    private final UnidadMedidaRepository repository;

    public UnidadMedidaService(UnidadMedidaRepository repository) {
        this.repository = repository;
    }

    public List<UnidadMedida> listar() {
        return repository.findAll();
    }

    public UnidadMedida guardar(UnidadMedida unidadMedida) {
        return repository.save(unidadMedida);
    }

    public UnidadMedida buscar(Integer id) {
        return repository.findById(id).orElse(null);
    }

    public void eliminar(Integer id) {
        repository.deleteById(id);
    }

}