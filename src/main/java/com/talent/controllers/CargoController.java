package com.talent.controllers;

import com.talent.dto.CargoDTO;
import com.talent.services.CargoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * Controlador responsável por manipular requisições relacionadas a cargos.
 */
@RestController
@RequestMapping("/cargos")
public class CargoController {

    @Autowired
    private CargoService cargoService;

    /**
     * Busca um cargo pelo ID.
     *
     * @param id ID do cargo a ser buscado.
     * @return ResponseEntity contendo o cargo encontrado ou mensagem de "Cargo não encontrado".
     */
    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable UUID id) {
        return cargoService.findById(id);
    }

    /**
     * Busca um cargo pelo nome.
     *
     * @param nomeCargo Nome do cargo a ser buscado.
     * @return ResponseEntity contendo o cargo encontrado ou mensagem de "Cargo não encontrado".
     */
    @GetMapping("/nome/{nomeCargo}")
    public ResponseEntity<Object> findByName(@PathVariable String nomeCargo) {
        return cargoService.findByName(nomeCargo);
    }

    /**
     * Salva um novo cargo.
     *
     * @param cargoDTO Objeto de transferência de dados contendo informações do novo cargo.
     * @return ResponseEntity contendo o cargo salvo.
     */
    @PostMapping
    public ResponseEntity<Object> saveCargo(@RequestBody CargoDTO cargoDTO) {
        return cargoService.saveCargo(cargoDTO);
    }

    /**
     * Atualiza um cargo existente pelo ID.
     *
     * @param id      ID do cargo a ser atualizado.
     * @param cargoDTO Objeto de transferência de dados contendo informações atualizadas do cargo.
     * @return ResponseEntity contendo o cargo atualizado ou mensagem de "Cargo não encontrado".
     */
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateCargo(@PathVariable UUID id, @RequestBody CargoDTO cargoDTO) {
        return cargoService.updateCargo(id, cargoDTO);
    }

    /**
     * Exclui um cargo existente pelo ID.
     *
     * @param id ID do cargo a ser excluído.
     * @return ResponseEntity contendo mensagem de sucesso ou "Cargo não encontrado".
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteCargo(@PathVariable UUID id) {
        return cargoService.deleteCargo(id);
    }
}
