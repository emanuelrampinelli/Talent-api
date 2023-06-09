package com.talent.repository;

import com.talent.model.Usuario;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UsuarioRepository {

    public Optional<Usuario> findByEmail (String email){
        return null;
    }
}
