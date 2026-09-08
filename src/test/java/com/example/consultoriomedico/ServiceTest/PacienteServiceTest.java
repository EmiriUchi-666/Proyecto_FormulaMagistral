package com.example.consultoriomedico.ServiceTest;


import com.example.consultoriomedico.Model.Paciente;
import com.example.consultoriomedico.Repository.PacienteRepository;
import com.example.consultoriomedico.Service.PacienteService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;



@ExtendWith(MockitoExtension.class)
class PacienteServiceTest {


    @Mock
    private PacienteRepository repository;


    @InjectMocks
    private PacienteService service;



    @Test
    void debeRegistrarPacienteCorrectamente(){


        Paciente paciente = new Paciente();

        paciente.setNombres("Juan");
        paciente.setApellidos("Perez");



        when(repository.save(paciente))
                .thenReturn(paciente);



        Paciente resultado = service.guardar(paciente);



        assertNotNull(resultado);


        assertEquals(
                "Juan",
                resultado.getNombres()
        );


        verify(repository)
                .save(paciente);

    }





    @Test
    void debeBuscarPacientePorIdCorrectamente(){


        Paciente paciente = new Paciente();


        paciente.setIdPaciente(1);

        paciente.setNombres("Juan");



        when(repository.findById(1))
                .thenReturn(java.util.Optional.of(paciente));



        Paciente resultado = service.buscar(1);



        assertNotNull(resultado);



        assertEquals(
                "Juan",
                resultado.getNombres()
        );



        verify(repository)
                .findById(1);

    }






    @Test
    void debeRetornarNullCuandoPacienteNoExiste(){



        when(repository.findById(99))
                .thenReturn(java.util.Optional.empty());



        Paciente resultado = service.buscar(99);



        assertNull(resultado);



        verify(repository)
                .findById(99);


    }


}