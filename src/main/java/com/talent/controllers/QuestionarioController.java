package com.talent.controllers;

import com.talent.dto.ModeloQuestionarioDTO;
import com.talent.dto.PerguntaDTO;
import com.talent.enums.TipoQuestionarioEnum;
import com.talent.services.QuestionarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * Controlador para manipulação de recursos relacionados a Questionários.
 */
@RestController
@RequestMapping("/questionarios")
public class QuestionarioController {

    @Autowired
    private QuestionarioService questionarioService;

    /**
     * Obtém todos os modelos de questionário de uma instituição com base no nome e/ou tipo.
     *
     * @param nome Nome do modelo de questionário.
     * @param tipo Tipo do modelo de questionário.
     * @return ResponseEntity contendo a lista de modelos de questionário correspondentes ou uma mensagem de erro.
     */
    @GetMapping("/modelos/buscar/{nome}/{tipo}")
    public ResponseEntity<Object> getAllModeloInstituicao(@PathVariable String nome,
                                                          @PathVariable TipoQuestionarioEnum tipo) {
        return questionarioService.findAllByNomeOrTipo(nome, tipo);
    }

    /**
     * Obtém todos os modelos de questionário de uma instituição por ID.
     *
     * @param idInstituicao ID da instituição.
     * @return ResponseEntity contendo a lista de modelos de questionário da instituição ou uma mensagem de erro.
     */
    @GetMapping("/modelos/{idInstituicao}")
    public ResponseEntity<Object> getAllModeloInstituicao(@PathVariable UUID idInstituicao) {
        return questionarioService.getAllModeloInstituicao(idInstituicao);
    }

    /**
     * Salva um novo modelo de questionário.
     *
     * @param modeloDTO DTO contendo informações do novo modelo de questionário.
     * @return ResponseEntity contendo o modelo de questionário recém-criado ou uma mensagem de erro.
     */
    @PostMapping("/modelos")
    public ResponseEntity<Object> saveQuestionario(@RequestBody @Valid ModeloQuestionarioDTO modeloDTO) {
        return questionarioService.saveQuestionario(modeloDTO);
    }

    /**
     * Exclui um modelo de questionário por ID.
     *
     * @param id ID do modelo de questionário a ser excluído.
     * @return ResponseEntity indicando o sucesso ou uma mensagem de erro.
     */
    @DeleteMapping("/modelos/{id}")
    public ResponseEntity<Object> deleteQuestionario(@PathVariable UUID id) {
        return questionarioService.deleteQuestionario(id);
    }

    /**
     * Atualiza um modelo de questionário existente.
     *
     * @param modeloDTO DTO contendo as informações atualizadas do modelo de questionário.
     * @return ResponseEntity contendo o modelo de questionário atualizado ou uma mensagem de erro.
     */
    @PutMapping("/modelos")
    public ResponseEntity<Object> updateQuestionario(@RequestBody @Valid ModeloQuestionarioDTO modeloDTO) {
        return questionarioService.updateQuestionario(modeloDTO);
    }

    /**
     * Obtém todas as perguntas de um questionário por ID do modelo.
     *
     * @param idModelo ID do modelo de questionário.
     * @return ResponseEntity contendo a lista de perguntas do modelo de questionário ou uma mensagem de erro.
     */
    @GetMapping("/modelos/perguntas/{idModelo}")
    public ResponseEntity<Object> getAllPerguntasQuestionario(@PathVariable UUID idModelo) {
        return questionarioService.getAllPerguntasQuestionario(idModelo);
    }

    /**
     * Salva uma nova pergunta para um modelo de questionário.
     *
     * @param perguntaDTO DTO contendo informações da nova pergunta.
     * @return ResponseEntity contendo a pergunta recém-criada ou uma mensagem de erro.
     */
    @PostMapping("/modelos/perguntas")
    public ResponseEntity<Object> savePerguntaModelo(@RequestBody @Valid PerguntaDTO perguntaDTO) {
        return questionarioService.savePerguntaModelo(perguntaDTO);
    }

    /**
     * Exclui uma pergunta de um modelo de questionário por ID.
     *
     * @param id ID da pergunta a ser excluída.
     * @return ResponseEntity indicando o sucesso ou uma mensagem de erro.
     */
    @DeleteMapping("/modelos/perguntas/{id}")
    public ResponseEntity<Object> deletePerguntaQuestionario(@PathVariable UUID id) {
        return questionarioService.deletePerguntaQuestionario(id);
    }

    /**
     * Atualiza uma pergunta de um modelo de questionário existente.
     *
     * @param perguntaDTO DTO contendo as informações atualizadas da pergunta.
     * @return ResponseEntity contendo a pergunta atualizada ou uma mensagem de erro.
     */
    @PutMapping("/modelos/perguntas")
    public ResponseEntity<Object> updatePerguntaQuestionario(@RequestBody @Valid PerguntaDTO perguntaDTO) {
        return questionarioService.updatePerguntaQuestionario(perguntaDTO);
    }
}
