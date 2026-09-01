package com.example.consultoriomedico.Controller;


import com.example.consultoriomedico.Model.Paciente;
import com.example.consultoriomedico.Service.PacienteService;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;


import java.util.List;



@RestController
@RequestMapping("/pacientes")
public class PacienteController {



private final PacienteService service;



public PacienteController(PacienteService service){

    this.service=service;

}




@GetMapping
public ResponseEntity<List<Paciente>> listar(){

return ResponseEntity.ok(service.listar());

}



@GetMapping("/{id}")
public ResponseEntity<Paciente> buscar(@PathVariable Integer id){


Paciente paciente=service.buscar(id);


if(paciente==null){

return ResponseEntity.notFound().build();

}


return ResponseEntity.ok(paciente);

}




@PostMapping
public ResponseEntity<Paciente> guardar(
@RequestBody Paciente paciente){


Paciente nuevo=service.guardar(paciente);


return new ResponseEntity<>(nuevo,HttpStatus.CREATED);

}





@PutMapping("/{id}")
public ResponseEntity<Paciente> actualizar(
@PathVariable Integer id,
@RequestBody Paciente paciente){



Paciente existente=service.buscar(id);



if(existente==null){

return ResponseEntity.notFound().build();

}



existente.setTipoDocumento(paciente.getTipoDocumento());
existente.setNumDocumento(paciente.getNumDocumento());
existente.setNombres(paciente.getNombres());
existente.setApellidos(paciente.getApellidos());
existente.setTelefono(paciente.getTelefono());
existente.setEmail(paciente.getEmail());


return ResponseEntity.ok(service.guardar(existente));


}




@DeleteMapping("/{id}")

public ResponseEntity<Void> eliminar(@PathVariable Integer id){


Paciente paciente=service.buscar(id);



if(paciente==null){

return ResponseEntity.notFound().build();

}


service.eliminar(id);


return ResponseEntity.noContent().build();


}


}