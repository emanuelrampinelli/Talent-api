package com.talent.controllers;

import com.talent.dto.ColaboradorDTO;
import com.talent.enums.ColaboradorStatusEnum;
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
     * Obtém uma lista de colaboradores com base no nome, fkCargo e situacao.
     *
     * @param nome     Nome do colaborador.
     * @param fkCargo  ID do cargo associado ao colaborador.
     * @param situacao Situação do colaborador.
     * @return ResponseEntity contendo a lista de colaboradores ou uma resposta 404 se nenhum for encontrado.
     */
    @GetMapping("/buscar/{nome}/{fkCargo}/{situacao}")
    public ResponseEntity<Object> findAllByNomeAndFkCargoAndSituacao(@PathVariable String nome,
                                                                     @PathVariable UUID fkCargo,
                                                                     @PathVariable ColaboradorStatusEnum situacao) {
        return colaboradorService.findAllByNomeAndFkCargoAndSituacao(nome, fkCargo, situacao);
    }

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
