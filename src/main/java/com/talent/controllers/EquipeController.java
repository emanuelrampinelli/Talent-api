package com.talent.controllers;

import com.talent.dto.EquipeDTO;
import com.talent.services.EquipeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * Controlador para manipulação de recursos relacionados a Equipes.
 */
@RestController
@RequestMapping("/equipes")
public class EquipeController {

    @Autowired
    private EquipeService equipeService;

    /**
     * Obtém uma equipe por ID.
     *
     * @param id ID da equipe a ser obtida.
     * @return ResponseEntity contendo a equipe ou uma mensagem de erro.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Object> getEquipeById(@PathVariable UUID id) {
        return equipeService.getEquipeById(id);
    }

    /**
     * Salva uma nova equipe.
     *
     * @param equipeDTO DTO contendo informações da nova equipe.
     * @return ResponseEntity contendo a equipe recém-criada ou uma mensagem de erro.
     */
    @PostMapping
    public ResponseEntity<Object> saveEquipe(@RequestBody @Valid EquipeDTO equipeDTO) {
        return equipeService.saveEquipe(equipeDTO);
    }

    /**
     * Atualiza uma equipe existente.
     *
     * @param id        ID da equipe a ser atualizada.
     * @param equipeDTO DTO contendo as informações atualizadas da equipe.
     * @return ResponseEntity contendo a equipe atualizada ou uma mensagem de erro.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateEquipe(@PathVariable UUID id, @RequestBody EquipeDTO equipeDTO) {
        return equipeService.updateEquipe(id, equipeDTO);
    }

    /**
     * Exclui uma equipe por ID.
     *
     * @param id ID da equipe a ser excluída.
     * @return ResponseEntity indicando o sucesso ou uma mensagem de erro.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteEquipe(@PathVariable UUID id) {
        return equipeService.deleteEquipe(id);
    }
}
