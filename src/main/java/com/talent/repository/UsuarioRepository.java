package com.talent.repository;

import com.talent.model.Usuario;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
@Transactional
public interface UsuarioRepository extends JpaRepository<Usuario, UUID> {

     UserDetails findByEmail(String email);

}