package com.talent.services;

import com.talent.dto.ModeloQuestionarioDTO;
import com.talent.dto.PerguntaDTO;
import com.talent.enums.TipoQuestionarioEnum;
import com.talent.model.Instituicao;
import com.talent.model.ModeloQuestionario;
import com.talent.model.Pergunta;
import com.talent.repository.InstituicaoRepository;
import com.talent.repository.ModeloQuestionarioRepository;
import com.talent.repository.PerguntaRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Serviço para manipulação de recursos relacionados a Questionários.
 */
@Service
public class QuestionarioService {

    @Autowired
    private ModeloQuestionarioRepository modeloQuestionarioRepository;

    @Autowired
    private PerguntaRepository perguntaRepository;

    @Autowired
    private InstituicaoRepository instituicaoRepository;

    /**
     * Obtém todos os modelos de questionário com base no nome e/ou tipo.
     *
     * @param nome Nome do modelo de questionário.
     * @param tipo Tipo do modelo de questionário.
     * @return ResponseEntity contendo a lista de modelos de questionário correspondentes ou uma mensagem de erro.
     */
    public ResponseEntity<Object> findAllByNomeOrTipo(String nome, TipoQuestionarioEnum tipo) {
        List<ModeloQuestionario> modelos = modeloQuestionarioRepository.findAllByNomeOrTipo(nome, tipo);

        if (!modelos.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body(modelos);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nenhum modelo encontrado");
        }
    }

    /**
     * Obtém todos os modelos de questionário de uma instituição por ID.
     *
     * @param idInstituicao ID da instituição.
     * @return ResponseEntity contendo a lista de modelos de questionário da instituição ou uma mensagem de erro.
     */
    public ResponseEntity<Object> getAllModeloInstituicao(UUID idInstituicao) {
        List<ModeloQuestionario> modelos = modeloQuestionarioRepository.findAllByInstituicaoId(idInstituicao);

        if (!modelos.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body(modelos);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nenhum modelo encontrado para a instituição com o ID: " + idInstituicao);
        }
    }

    /**
     * Atualiza um modelo de questionário existente.
     *
     * @param modeloDTO DTO contendo as informações atualizadas do modelo de questionário.
     * @return ResponseEntity contendo o modelo de questionário atualizado ou uma mensagem de erro.
     */
    public ResponseEntity<Object> updateQuestionario(ModeloQuestionarioDTO modeloDTO) {
        Optional<ModeloQuestionario> optionalModelo = modeloQuestionarioRepository.findById(modeloDTO.getId());

        if (optionalModelo.isPresent()) {
            ModeloQuestionario modeloAtualizado = optionalModelo.get();
            UUID idmodelo = modeloAtualizado.getId();
            BeanUtils.copyProperties(modeloDTO, modeloAtualizado);
            modeloAtualizado.setId(idmodelo);
            ModeloQuestionario modeloSalvo = modeloQuestionarioRepository.save(modeloAtualizado);

            return ResponseEntity.status(HttpStatus.OK).body(modeloSalvo);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Modelo não encontrado");
        }
    }

    /**
     * Exclui um modelo de questionário por ID.
     *
     * @param id ID do modelo de questionário a ser excluído.
     * @return ResponseEntity indicando o sucesso ou uma mensagem de erro.
     */
    public ResponseEntity<Object> deleteQuestionario(UUID id) {
        ModeloQuestionario modelo = modeloQuestionarioRepository.findById(id).orElse(null);

        if (modelo != null) {
            List<Pergunta> perguntas = perguntaRepository.findAllByModeloId(modelo.getId());

            if (perguntas.size() > 0) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Modelo com perguntas alocadas, remova antes de excluir.");
            }

            modeloQuestionarioRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body("Modelo excluído com sucesso");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Modelo não encontrado");
        }
    }

    /**
     * Salva um novo modelo de questionário.
     *
     * @param modeloDTO DTO contendo informações do novo modelo de questionário.
     * @return ResponseEntity contendo o modelo de questionário recém-criado ou uma mensagem de erro.
     */
    public ResponseEntity<Object> saveQuestionario(ModeloQuestionarioDTO modeloDTO) {
        try {
            Instituicao instituicao = instituicaoRepository.findById(modeloDTO.getIdInstituicao()).orElse(null);

            if (instituicao == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Instituição não cadastrada.");
            }

            ModeloQuestionario modelo = new ModeloQuestionario();
            BeanUtils.copyProperties(modeloDTO, modelo);
            modelo.setInstituicao(instituicao);
            ModeloQuestionario modeloSalvo = modeloQuestionarioRepository.save(modelo);

            return ResponseEntity.status(HttpStatus.CREATED).body(modeloSalvo);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao salvar o modelo. " + e.getMessage());
        }
    }

    /**
     * Obtém todas as perguntas de um questionário por ID do modelo.
     *
     * @param idModelo ID do modelo de questionário.
     * @return ResponseEntity contendo a lista de perguntas do modelo de questionário ou uma mensagem de erro.
     */
    public ResponseEntity<Object> getAllPerguntasQuestionario(UUID idModelo) {
        List<Pergunta> perguntas = perguntaRepository.findAllByModeloId(idModelo);

        if (!perguntas.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body(perguntas);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nenhuma pergunta encontrada para o modelo com o ID: " + idModelo);
        }
    }

    /**
     * Exclui uma pergunta de um modelo de questionário por ID.
     *
     * @param id ID da pergunta a ser excluída.
     * @return ResponseEntity indicando o sucesso ou uma mensagem de erro.
     */
    public ResponseEntity<Object> deletePerguntaQuestionario(UUID id) {
        Pergunta pergunta = perguntaRepository.findById(id).orElse(null);

        if (pergunta != null) {
            perguntaRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body("Pergunta excluída com sucesso");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pergunta não encontrada");
        }
    }

    /**
     * Atualiza uma pergunta de um modelo de questionário existente.
     *
     * @param perguntaDTO DTO contendo as informações atualizadas da pergunta.
     * @return ResponseEntity contendo a pergunta atualizada ou uma mensagem de erro.
     */
    public ResponseEntity<Object> updatePerguntaQuestionario(PerguntaDTO perguntaDTO) {
        Optional<Pergunta> optionalPergunta = perguntaRepository.findById(perguntaDTO.getId());

        if (optionalPergunta.isPresent()) {
            Pergunta perguntaAtualizado = optionalPergunta.get();
            UUID idPergunta = perguntaAtualizado.getId();
            BeanUtils.copyProperties(perguntaDTO, perguntaAtualizado);
            perguntaAtualizado.setId(idPergunta);
            Pergunta perguntaSalva = perguntaRepository.save(perguntaAtualizado);

            return ResponseEntity.status(HttpStatus.OK).body(perguntaSalva);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pergunta não encontrada");
        }
    }

    /**
     * Salva uma nova pergunta para um modelo de questionário.
     *
     * @param perguntaDTO DTO contendo informações da nova pergunta.
     * @return ResponseEntity contendo a pergunta recém-criada ou uma mensagem de erro.
     */
    public ResponseEntity<Object> savePerguntaModelo(PerguntaDTO perguntaDTO) {
        try {
            ModeloQuestionario modelo = modeloQuestionarioRepository.findById(perguntaDTO.getIdModelo()).orElse(null);

            if (modelo == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Modelo não cadastrado.");
            }

            Pergunta pergunta = new Pergunta();
            BeanUtils.copyProperties(perguntaDTO, pergunta);
            pergunta.setModelo(modelo);
            Pergunta perguntaSalva = perguntaRepository.save(pergunta);

            return ResponseEntity.status(HttpStatus.CREATED).body(perguntaSalva);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao salvar a pergunta. " + e.getMessage());
        }
    }
}
