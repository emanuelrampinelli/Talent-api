package com.talent.controllers;

import com.talent.dto.ColaboradorDTO;
import com.talent.services.ColaboradorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * Controlador para manipulação de recursos relacionados a Colaboradores.
 */
@RestController
@RequestMapping("/colaboradores")
public class ColaboradorController {

    @Autowired
    private ColaboradorService colaboradorService;

    /**
     * Obtém um colaborador por ID.
     *
     * @param id ID do colaborador a ser obtido.
     * @return ResponseEntity contendo o colaborador ou uma mensagem de erro.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Object> getColaboradorById(@PathVariable UUID id) {
        return colaboradorService.getColaboradorById(id);
    }

    /**
     * Obtém uma lista de colaboradores por ID de Instituição.
     *
     * @param id ID da instituição para a qual se deseja obter a lista de colaboradores.
     * @return ResponseEntity contendo a lista de colaboradores ou uma mensagem de erro.
     */
    @GetMapping("/instituicao/{id}")
    public ResponseEntity<Object> getColaboradoresByInstituicao(@PathVariable UUID id) {
        return colaboradorService.getColaboradoresByInstituicao(id);
    }

    /**
     * Salva um novo colaborador.
     *
     * @param colaboradorDTO DTO contendo informações do novo colaborador.
     * @return ResponseEntity contendo o colaborador recém-criado ou uma mensagem de erro.
     */
    @PostMapping
    public ResponseEntity<Object> saveColaborador(@RequestBody @Valid ColaboradorDTO colaboradorDTO) {
        return colaboradorService.saveColaborador(colaboradorDTO);
    }

        /**
         * Atualiza um colaborador existente.
         *
         * @param id            ID do colaborador a ser atualizado.
         * @param colaboradorDTO DTO contendo as informações atualizadas do colaborador.
         * @return ResponseEntity contendo o colaborador atualizado ou uma mensagem de erro.
         */
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateColaborador(@PathVariable UUID id, @RequestBody ColaboradorDTO colaboradorDTO) {
        return colaboradorService.updateColaborador(id, colaboradorDTO);
    }

    /**
     * Exclui um colaborador por ID.
     *
     * @param id ID do colaborador a ser excluído.
     * @return ResponseEntity indicando o sucesso ou uma mensagem de erro.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteColaborador(@PathVariable UUID id) {
        return colaboradorService.deleteColaborador(id);
    }
}
