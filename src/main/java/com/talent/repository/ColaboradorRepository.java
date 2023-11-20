package com.talent.repository;

import com.talent.model.Colaborador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Repository
@Transactional
public interface ColaboradorRepository  extends JpaRepository<Colaborador, UUID> {
}
