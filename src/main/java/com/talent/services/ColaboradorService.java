package com.talent.services;

import com.talent.dto.ColaboradorDTO;
import com.talent.model.Colaborador;
import com.talent.repository.ColaboradorRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

/**
 * Serviço que fornece operações relacionadas a Colaboradores.
 */
@Service
public class ColaboradorService {

    @Autowired
    private ColaboradorRepository colaboradorRepository;

    /**
     * Obtém um colaborador por ID.
     *
     * @param id ID do colaborador a ser obtido.
     * @return ResponseEntity contendo o colaborador ou uma mensagem de erro.
     */
    public ResponseEntity<Object> getColaboradorById(UUID id) {
        Optional<Colaborador> optionalColaborador = colaboradorRepository.findById(id);
        if (optionalColaborador.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(optionalColaborador.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Colaborador não encontrado");
        }
    }

    /**
     * Salva um novo colaborador.
     *
     * @param colaboradorDTO DTO contendo informações do novo colaborador.
     * @return ResponseEntity contendo o colaborador recém-criado ou uma mensagem de erro.
     */
    public ResponseEntity<Object> saveColaborador(ColaboradorDTO colaboradorDTO) {
        Colaborador novoColaborador = new Colaborador();
        BeanUtils.copyProperties(colaboradorDTO, novoColaborador);
        Colaborador colaboradorSalvo = colaboradorRepository.save(novoColaborador);
        return ResponseEntity.status(HttpStatus.CREATED).body(colaboradorSalvo);
    }

    /**
     * Atualiza um colaborador existente.
     *
     * @param id            ID do colaborador a ser atualizado.
     * @param colaboradorDTO DTO contendo as informações atualizadas do colaborador.
     * @return ResponseEntity contendo o colaborador atualizado ou uma mensagem de erro.
     */
    public ResponseEntity<Object> updateColaborador(UUID id, ColaboradorDTO colaboradorDTO) {
        Optional<Colaborador> optionalColaborador = colaboradorRepository.findById(id);
        if (optionalColaborador.isPresent()) {
            Colaborador colaboradorAtualizado = optionalColaborador.get();
            BeanUtils.copyProperties(colaboradorDTO, colaboradorAtualizado);
            Colaborador colaboradorSalvo = colaboradorRepository.save(colaboradorAtualizado);
            return ResponseEntity.status(HttpStatus.OK).body(colaboradorSalvo);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Colaborador não encontrado");
        }
    }

    /**
     * Exclui um colaborador por ID.
     *
     * @param id ID do colaborador a ser excluído.
     * @return ResponseEntity indicando o sucesso ou uma mensagem de erro.
     */
    public ResponseEntity<Object> deleteColaborador(UUID id) {
        Optional<Colaborador> optionalColaborador = colaboradorRepository.findById(id);
        if (optionalColaborador.isPresent()) {
            colaboradorRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body("Colaborador excluído com sucesso");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Colaborador não encontrado");
        }
    }
}
