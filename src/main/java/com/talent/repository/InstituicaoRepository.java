package com.talent.repository;

import com.talent.model.Instituicao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

/**
 * Repositório para a entidade Instituicao.
 */
@Repository
@Transactional
public interface InstituicaoRepository extends JpaRepository<Instituicao, UUID> {

    /**
     * Busca uma instituição pelo nome.
     *
     * @param nome Nome da instituição a ser procurada.
     * @return Uma instituição opcional com o nome fornecido, ou vazia se não encontrada.
     */
    Optional<Instituicao> findByNome(String nome);
}
