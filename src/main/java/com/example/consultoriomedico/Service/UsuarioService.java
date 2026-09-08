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



<<<<<<< HEAD
    /** Usado para resolver al usuario actualmente logueado (por su email) al registrar ventas/movimientos. */
    public Usuario buscarPorEmail(String email){
        return repository.findByEmail(email);
    }



=======
>>>>>>> 4aa01638f118a77226d52029b8c22d2f96991c71
    public void eliminar(Integer id){
        repository.deleteById(id);
    }

}