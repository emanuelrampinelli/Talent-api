package com.talent.services;


import com.talent.model.Usuario;
import com.talent.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    public Usuario save(Usuario usuario) {return usuarioRepository.save(usuario);}

    public Optional<Usuario> findByEmail(String email){
        return usuarioRepository.findByEmail(email);
    }

}