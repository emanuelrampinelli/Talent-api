package com.talent.controllers;

import com.talent.dto.EquipeDTO;
import com.talent.services.EquipeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;

/**
 * Controlador para manipulação de requisições relacionadas a equipes.
 */
@RestController
@RequestMapping("/equipes")
public class EquipeController {

    @Autowired
    private EquipeService equipeService;

    /**
     * Obtém uma equipe pelo ID.
     *
     * @param id O ID da equipe a ser recuperada.
     * @return ResponseEntity contendo a equipe se encontrada, ou uma resposta 404 se não encontrada.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Object> getEquipeById(@PathVariable UUID id) {
        return equipeService.getEquipeById(id);
    }

    /**
     * Obtém uma equipe pelo nome.
     *
     * @param nome O nome da equipe a ser recuperada.
     * @return ResponseEntity contendo a equipe se encontrada, ou uma resposta 404 se não encontrada.
     */
    @GetMapping("/nome/{nome}")
    public ResponseEntity<Object> getEquipeByNome(@PathVariable String nome) {
        return equipeService.getEquipeByNome(nome);
    }

    /**
     * Salva uma nova equipe com base no DTO fornecido.
     *
     * @param equipeDTO O DTO contendo informações sobre a equipe a ser salva.
     * @return ResponseEntity contendo a equipe salva ou uma mensagem de erro.
     */
    @PostMapping
    public ResponseEntity<Object> saveEquipe(@RequestBody @Valid EquipeDTO equipeDTO) {
        return equipeService.saveEquipe(equipeDTO);
    }

    /**
     * Atualiza uma equipe existente com base no DTO fornecido.
     *
     * @param equipeDTO O DTO contendo informações atualizadas sobre a equipe.
     * @return ResponseEntity contendo a equipe atualizada se encontrada, ou uma resposta 404 se não encontrada.
     */
    @PutMapping
    public ResponseEntity<Object> updateEquipe(@RequestBody EquipeDTO equipeDTO) {
        return equipeService.updateEquipe(equipeDTO);
    }

    /**
     * Exclui uma equipe pelo ID.
     *
     * @param id O ID da equipe a ser excluída.
     * @return ResponseEntity indicando o sucesso ou a falha da exclusão.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteEquipe(@PathVariable UUID id) {
        return equipeService.deleteEquipe(id);
    }
}
