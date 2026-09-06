package com.example.consultoriomedico.Service;

import com.example.consultoriomedico.Model.Usuario;
import com.example.consultoriomedico.Repository.UsuarioRepository;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioRepository repository;
    private final BCryptPasswordEncoder encoder;


    public UsuarioService(
            UsuarioRepository repository,
            BCryptPasswordEncoder encoder
    ){
        this.repository = repository;
        this.encoder = encoder;
    }



    public List<Usuario> listar(){
        return repository.findAll();
    }



    public Usuario guardar(Usuario usuario){

        usuario.setPasswordHash(
                encoder.encode(usuario.getPasswordHash())
        );

        return repository.save(usuario);
    }



    public Usuario buscar(Integer id){
        return repository.findById(id)
                .orElse(null);
    }



    public void eliminar(Integer id){
        repository.deleteById(id);
    }

}