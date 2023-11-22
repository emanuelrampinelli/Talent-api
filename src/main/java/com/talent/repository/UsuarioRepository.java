package com.talent.repository;

import com.talent.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * Repositório para a entidade Usuario.
 */
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, UUID> {

     /**
      * Busca um usuário pelo endereço de e-mail.
      *
      * @param email Endereço de e-mail do usuário a ser procurado.
      * @return UserDetails associado ao endereço de e-mail fornecido.
      */
     UserDetails findByEmail(String email);
}
