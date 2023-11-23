package com.talent.controllers;

import com.talent.dto.AvaliacaoDTO;
import com.talent.services.AvaliacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * Controlador responsável por lidar com as requisições relacionadas a Avaliações.
 */
@RestController
@RequestMapping("/avaliacoes")
public class AvaliacaoController {

    @Autowired
    private AvaliacaoService avaliacaoService;

    /**
     * Obtém as avaliações associadas a uma instituição.
     *
     * @param idInstituicao O identificador da instituição.
     * @return ResponseEntity contendo a lista de AvaliacaoDTO ou mensagem de erro.
     */
    @GetMapping("/{idInstituicao}")
    public ResponseEntity<Object> findByInstituicao(@PathVariable UUID idInstituicao) {
        return avaliacaoService.findByInstituicao(idInstituicao);
    }

    /**
     * Salva uma nova avaliação com base nos dados fornecidos no corpo da requisição.
     *
     * @param avaliacaoDTO Os dados da avaliação a serem salvos.
     * @return ResponseEntity indicando o sucesso ou falha da criação da avaliação.
     */
    @PostMapping
    public ResponseEntity<Object> saveAvaliacao(@RequestBody AvaliacaoDTO avaliacaoDTO) {
        return avaliacaoService.saveAvaliacao(avaliacaoDTO);
    }

    /**
     * Exclui uma avaliação com base no ID.
     *
     * @param id O identificador da avaliação a ser excluída.
     * @return ResponseEntity indicando o sucesso ou falha da exclusão.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteAvaliacao(@PathVariable UUID id) {
        return avaliacaoService.deleteAvaliacao(id);
    }
}
