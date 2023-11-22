package com.talent.repository;

import com.talent.model.Pergunta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

/**
 * Repositório para a entidade Pergunta.
 */
@Repository
@Transactional
public interface PerguntaRepository extends JpaRepository<Pergunta, UUID> {

    /**
     * Busca todas as perguntas associadas a um modelo de questionário pelo ID do modelo.
     *
     * @param id ID do modelo de questionário.
     * @return Lista de perguntas associadas ao modelo de questionário.
     */
    List<Pergunta> findAllByModeloId(UUID id);
}
