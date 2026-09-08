package com.example.consultoriomedico.ServiceTest;


import com.example.consultoriomedico.Model.CitaMedica;
import com.example.consultoriomedico.Repository.CitaMedicaRepository;
import com.example.consultoriomedico.Service.CitaMedicaService;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.junit.jupiter.MockitoExtension;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;



@ExtendWith(MockitoExtension.class)
class CitaMedicaServiceTest {



    @Mock
    private CitaMedicaRepository repository;



    @InjectMocks
    private CitaMedicaService service;




    @Test
    void debeRegistrarCitaCorrectamente(){


        CitaMedica cita = new CitaMedica();

        cita.setMotivo("Dolor de cabeza");

        cita.setEstado("Pendiente");



        when(repository.save(cita))
                .thenReturn(cita);



        CitaMedica resultado =
                service.guardar(cita);



        assertNotNull(resultado);



        assertEquals(
                "Dolor de cabeza",
                resultado.getMotivo()
        );



        assertEquals(
                "Pendiente",
                resultado.getEstado()
        );



        verify(repository)
                .save(cita);

    }






    @Test
    void debeBuscarCitaPorIdCorrectamente(){


        CitaMedica cita = new CitaMedica();


        cita.setIdCita(1);

        cita.setMotivo("Consulta dermatológica");



        when(repository.findById(1))
                .thenReturn(java.util.Optional.of(cita));



        CitaMedica resultado =
                service.buscar(1);



        assertNotNull(resultado);



        assertEquals(
                "Consulta dermatológica",
                resultado.getMotivo()
        );



        verify(repository)
                .findById(1);


    }








    @Test
    void debeRetornarNullCuandoCitaNoExiste(){


        when(repository.findById(99))
                .thenReturn(java.util.Optional.empty());



        CitaMedica resultado =
                service.buscar(99);



        assertNull(resultado);



        verify(repository)
                .findById(99);


    }



}