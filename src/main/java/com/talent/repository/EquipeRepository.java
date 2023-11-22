package com.talent.repository;

import com.talent.model.Equipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

/**
 * Repositório para a entidade Equipe.
 */
@Repository
@Transactional
public interface EquipeRepository extends JpaRepository<Equipe, UUID> {

    /**
     * Busca uma equipe pelo nome.
     *
     * @param nome Nome da equipe a ser procurada.
     * @return Uma equipe opcional com o nome fornecido, ou vazio se não encontrada.
     */
    Optional<Equipe> findByNome(String nome);
}
