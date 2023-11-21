package com.talent.repository;

import com.talent.enums.ColaboradorStatusEnum;
import com.talent.model.Colaborador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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

    @Query("SELECT c FROM Colaborador c " +
            "WHERE c.nome = :nome OR c.cargo.id = :fk_cargo OR c.situacao = :situacao")
    List<Colaborador> findAllByNomeAndFkCargoAndSituacao(@Param("nome") String nome,
                                                         @Param("fk_cargo") UUID fkCargo,
                                                         @Param("situacao") ColaboradorStatusEnum situacao);
}
