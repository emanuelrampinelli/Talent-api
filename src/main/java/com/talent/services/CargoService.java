package com.talent.services;

import com.talent.dto.CargoDTO;
import com.talent.model.Cargo;
import com.talent.model.Instituicao;
import com.talent.repository.CargoRepository;
import com.talent.repository.InstituicaoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * Serviço responsável por operações relacionadas a cargos.
 */
@Service
public class CargoService {

    @Autowired
    private CargoRepository cargoRepository;

    @Autowired
    private InstituicaoRepository instituicaoRepository;

    /**
     * Busca um cargo pelo ID.
     *
     * @param id ID do cargo a ser buscado.
     * @return ResponseEntity contendo o cargo encontrado ou mensagem de "Cargo não encontrado".
     */
    public ResponseEntity<Object> findById(UUID id) {
        Cargo cargo = cargoRepository.findById(id).orElse(null);
        if (cargo != null) {
            return ResponseEntity.status(HttpStatus.OK).body(cargo);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cargo não encontrado");
        }
    }

    /**
     * Busca um cargo pelo nome.
     *
     * @param nomeCargo Nome do cargo a ser buscado.
     * @return ResponseEntity contendo o cargo encontrado ou mensagem de "Cargo não encontrado".
     */
    public ResponseEntity<Object> findByName(String nomeCargo) {
        Cargo cargo = cargoRepository.findByNome(nomeCargo).orElse(null);
        if (cargo != null) {
            return ResponseEntity.status(HttpStatus.OK).body(cargo);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cargo não encontrado");
        }
    }

    /**
     * Salva um novo cargo.
     *
     * @param cargoDTO Objeto de transferência de dados contendo informações do novo cargo.
     * @return ResponseEntity contendo o cargo salvo.
     */
    public ResponseEntity<Object> saveCargo(CargoDTO cargoDTO) {

        Instituicao instituicao = instituicaoRepository.findById(cargoDTO.getIdEmpresa()).orElse(null);
        if (instituicao != null) {
            Cargo novoCargo = new Cargo();
            novoCargo.setInstituicao(instituicao);
            BeanUtils.copyProperties(cargoDTO, novoCargo);
            Cargo cargoSalvo = cargoRepository.save(novoCargo);
            return ResponseEntity.status(HttpStatus.OK).body(cargoSalvo);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Instituicao não encontrada");
        }

    }

    /**
     * Atualiza um cargo existente pelo ID.
     *
     * @param idCargo  ID do cargo a ser atualizado.
     * @param cargoDTO Objeto de transferência de dados contendo informações atualizadas do cargo.
     * @return ResponseEntity contendo o cargo atualizado ou mensagem de "Cargo não encontrado".
     */
    public ResponseEntity<Object> updateCargo(UUID idCargo, CargoDTO cargoDTO) {
        Cargo cargo = cargoRepository.findById(idCargo).orElse(null);
        if (cargo != null) {
            cargo.setNome(cargoDTO.getNome());
            cargo.setDescricao(cargoDTO.getDescricao());
            Cargo cargoAtualizado = cargoRepository.save(cargo);
            return ResponseEntity.status(HttpStatus.OK).body(cargoAtualizado);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cargo não encontrado");
        }
    }

    /**
     * Exclui um cargo existente pelo ID.
     *
     * @param idCargo ID do cargo a ser excluído.
     * @return ResponseEntity contendo mensagem de sucesso ou "Cargo não encontrado".
     */
    public ResponseEntity<Object> deleteCargo(UUID idCargo) {
        Cargo cargo = cargoRepository.findById(idCargo).orElse(null);
        if (cargo != null) {
            cargoRepository.delete(cargo);
            return ResponseEntity.status(HttpStatus.OK).body("Cargo excluído com sucesso");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cargo não encontrado");
        }
    }
}
