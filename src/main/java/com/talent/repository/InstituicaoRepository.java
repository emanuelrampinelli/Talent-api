package com.talent.repository;

import com.talent.model.Instituicao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Transactional
@Repository
public interface InstituicaoRepository extends JpaRepository<Instituicao, UUID> {

    Optional<Instituicao> findByNome (String nome);
}
