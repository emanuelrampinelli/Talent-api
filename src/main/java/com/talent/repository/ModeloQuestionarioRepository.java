package com.talent.repository;

import com.talent.enums.TipoQuestionarioEnum;
import com.talent.model.ModeloQuestionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

/**
 * Repositório para a entidade ModeloQuestionario.
 */
@Repository
@Transactional
public interface ModeloQuestionarioRepository extends JpaRepository<ModeloQuestionario, UUID> {

    /**
     * Busca todos os modelos de questionário associados a uma instituição pelo ID da instituição.
     *
     * @param id ID da instituição.
     * @return Lista de modelos de questionário associados à instituição.
     */
    List<ModeloQuestionario> findAllByInstituicaoId(UUID id);

    /**
     * Busca todos os modelos de questionário pelo nome e/ou tipo.
     *
     * @param nome Nome do modelo de questionário.
     * @param tipo Tipo do modelo de questionário.
     * @return Lista de modelos de questionário correspondentes aos parâmetros fornecidos.
     */
    List<ModeloQuestionario> findAllByNomeOrTipo(String nome, TipoQuestionarioEnum tipo);
}
