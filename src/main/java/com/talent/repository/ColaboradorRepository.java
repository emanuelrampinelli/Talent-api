package com.talent.repository;

import com.talent.model.Colaborador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Repository
@Transactional
public interface ColaboradorRepository  extends JpaRepository<Colaborador, UUID> {

    /**
     * Encontra todos os colaboradores associados a uma instituição com o ID fornecido.
     *
     * @param idInstituicao ID da instituição associada aos colaboradores.
     * @return Lista de colaboradores ou uma lista vazia se nenhum for encontrado.
     */
    List<Colaborador> findAllByInstituicaoId(UUID idInstituicao);
}
