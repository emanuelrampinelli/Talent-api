package com.talent.controllers;

import com.talent.dto.InstituicaoDTO;
import com.talent.services.InstituicaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * Controlador responsável por manipular requisições relacionadas a instituições.
 */
@RestController
@RequestMapping("/instituicoes")
public class InstituicaoController {

    @Autowired
    private InstituicaoService instituicaoService;

    /**
     * Busca uma instituição pelo ID.
     *
     * @param id ID da instituição a ser buscada.
     * @return ResponseEntity contendo a instituição encontrada ou mensagem de "Instituição não encontrada".
     */
    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable UUID id) {
        return instituicaoService.findById(id);
    }

    /**
     * Busca uma instituição pelo nome.
     *
     * @param nomeInstituicao Nome da instituição a ser buscada.
     * @return ResponseEntity contendo a instituição encontrada ou mensagem de "Instituição não encontrada".
     */
    @GetMapping("/nome/{nomeInstituicao}")
    public ResponseEntity<Object> findByName(@PathVariable String nomeInstituicao) {
        return instituicaoService.findByName(nomeInstituicao);
    }

    /**
     * Salva uma nova instituição.
     *
     * @param instituicaoDTO Objeto de transferência de dados contendo informações da nova instituição.
     * @return ResponseEntity contendo a instituição salva.
     */
    @PostMapping
    public ResponseEntity<Object> saveInstituicao(@RequestBody InstituicaoDTO instituicaoDTO) {
        return instituicaoService.saveInstituicao(instituicaoDTO);
    }

    /**
     * Atualiza uma instituição existente pelo ID.
     *
     * @param id               ID da instituição a ser atualizada.
     * @param instituicaoDTO Objeto de transferência de dados contendo informações atualizadas da instituição.
     * @return ResponseEntity contendo a instituição atualizada ou mensagem de "Instituição não encontrada".
     */
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateInstituicao(@PathVariable UUID id, @RequestBody InstituicaoDTO instituicaoDTO) {
        return instituicaoService.updateInstituicao(id, instituicaoDTO);
    }

    /**
     * Exclui uma instituição existente pelo ID.
     *
     * @param id ID da instituição a ser excluída.
     * @return ResponseEntity contendo mensagem de sucesso ou "Instituição não encontrada".
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteInstituicao(@PathVariable UUID id) {
        return instituicaoService.deleteInstituicao(id);
    }
}
