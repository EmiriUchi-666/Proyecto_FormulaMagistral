package com.example.consultoriomedico.Controller;

import com.example.consultoriomedico.Model.Proveedor;
import com.example.consultoriomedico.Service.ProveedorService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/proveedores")
public class ProveedorController {

    private final ProveedorService service;

    public ProveedorController(ProveedorService service) {
        this.service = service;
    }

    /**
     * Lista proveedores. Admite filtros opcionales por query param
     * (se evalúan en este orden si vienen varios):
     *  - /proveedores?ruc=20123456789      -> coincidencia exacta
     *  - /proveedores?nombre=distrib       -> coincidencia parcial
     *  - /proveedores?soloActivos=true
     *  - /proveedores                      -> todos
     */
    @GetMapping
    public ResponseEntity<?> listar(
            @RequestParam(required = false) String nombre,
            @RequestParam(required = false) String ruc,
            @RequestParam(required = false, defaultValue = "false") boolean soloActivos) {

        if (ruc != null) {
            Proveedor encontrado = service.buscarPorRuc(ruc);
            if (encontrado == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(encontrado);
        }

        if (nombre != null) {
            return ResponseEntity.ok(service.buscarPorNombre(nombre));
        }

        return ResponseEntity.ok(soloActivos ? service.listarActivos() : service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Proveedor> buscar(@PathVariable Integer id) {
        Proveedor proveedor = service.buscar(id);
        if (proveedor == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(proveedor);
    }

    @PostMapping
    public ResponseEntity<Proveedor> guardar(@Valid @RequestBody Proveedor proveedor) {
        Proveedor nuevo = service.guardar(proveedor);
        return new ResponseEntity<>(nuevo, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Proveedor> actualizar(@PathVariable Integer id, @Valid @RequestBody Proveedor proveedor) {
        Proveedor existente = service.buscar(id);
        if (existente == null) {
            return ResponseEntity.notFound().build();
        }
        existente.setNombre(proveedor.getNombre());
        existente.setRuc(proveedor.getRuc());
        existente.setTelefono(proveedor.getTelefono());
        existente.setEmail(proveedor.getEmail());
        existente.setDireccion(proveedor.getDireccion());
        return ResponseEntity.ok(service.guardar(existente));
    }

    /** Baja lógica en vez de DELETE físico. */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> desactivar(@PathVariable Integer id) {
        if (service.buscar(id) == null) {
            return ResponseEntity.notFound().build();
        }
        service.desactivar(id);
        return ResponseEntity.noContent().build();
    }

}