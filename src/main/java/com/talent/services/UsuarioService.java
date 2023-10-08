package com.talent.services;

import com.talent.dto.usuario.UsuarioDTO;
import com.talent.model.Usuario;
import com.talent.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    /**
     * Cadastra um usuário.
     *
     * @param usuario Dados do usuário a serem cadastrados.
     * @return Um objeto UsuarioDTO com as informações cadastradas ou null em caso de falha.
     */
    public UsuarioDTO save(Usuario usuario) {

        usuario = usuarioRepository.save(usuario);
        return new UsuarioDTO(usuario);
    }

    /**
     * Busca um usuário pelo email.
     *
     * @param email Email do usuário a ser buscado.
     * @return Um objeto UsuarioDTO com as informações do usuário encontrado ou null se não encontrado.
     */
    public UsuarioDTO findByEmail(String email) {

        Usuario usuario = (Usuario) usuarioRepository.findByEmail(email);

        return (usuario != null) ? new UsuarioDTO(usuario) : null;
    }
}
