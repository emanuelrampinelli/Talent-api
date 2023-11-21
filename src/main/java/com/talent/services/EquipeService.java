package com.talent.services;

import com.talent.dto.EquipeDTO;
import com.talent.model.Colaborador;
import com.talent.model.Equipe;
import com.talent.repository.ColaboradorRepository;
import com.talent.repository.EquipeRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

/**
 * Serviço para manipulação de equipes.
 */
@Service
public class EquipeService {

    @Autowired
    private EquipeRepository equipeRepository;

    @Autowired
    private ColaboradorRepository colaboradorRepository;



    /**
     * Obtém uma equipe pelo nome.
     *
     * @param nome O nome da equipe a ser recuperada.
     * @return ResponseEntity contendo a equipe se encontrada, ou uma resposta 404 se não encontrada.
     */
    public ResponseEntity<Object> getEquipeByNome(String nome) {
        try {
            Optional<Equipe> optionalEquipe = equipeRepository.findByNome(nome);
            if (optionalEquipe.isPresent()) {
                return ResponseEntity.status(HttpStatus.OK).body(optionalEquipe.get());
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Equipe não encontrada");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao obter a equipe por nome. " + e.getMessage());
        }
    }

    /**
     * Obtém uma equipe pelo ID.
     *
     * @param id O ID da equipe a ser recuperada.
     * @return ResponseEntity contendo a equipe se encontrada, ou uma resposta 404 se não encontrada.
     */
    public ResponseEntity<Object> getEquipeById(UUID id) {
        try {
            Optional<Equipe> optionalEquipe = equipeRepository.findById(id);
            if (optionalEquipe.isPresent()) {
                return ResponseEntity.status(HttpStatus.OK).body(optionalEquipe.get());
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Equipe não encontrada para o ID: " + id);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao obter a equipe por ID. " + e.getMessage());
        }
    }


    /**
     * Salva uma nova equipe com base no DTO fornecido.
     *
     * @param equipeDTO O DTO contendo informações sobre a equipe a ser salva.
     * @return ResponseEntity contendo a equipe salva ou uma mensagem de erro.
     */
    public ResponseEntity<Object> saveEquipe(EquipeDTO equipeDTO) {
        try {
            // Busca o líder da equipe no repositório de colaboradores.
            Colaborador lider = colaboradorRepository.findById(equipeDTO.getIdLider()).orElse(null);

            // Verifica se o líder foi encontrado.
            if (lider == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Líder não encontrado.");
            }

            // Cria uma nova instância de Equipe e copia as propriedades do DTO.
            Equipe equipe = new Equipe();
            BeanUtils.copyProperties(equipeDTO, equipe);

            // Define o líder da equipe.
            equipe.setLider(lider);

            // Salva a equipe no repositório.
            Equipe equipeSalva = equipeRepository.save(equipe);

            // Atribui a equipe aos colaboradores, excluindo o líder da lista se estiver presente.
            equipeDTO.getColaboradores().stream()
                    .filter(colab -> !colab.getId().equals(equipeDTO.getIdLider()))
                    .map(colab -> colaboradorRepository.findById(colab.getId()))
                    .filter(Optional::isPresent)
                    .map(Optional::get)
                    .forEach(colaborador -> {
                        colaborador.setEquipe(equipeSalva);
                        colaboradorRepository.save(colaborador);
                    });

            // Retorna uma resposta HTTP indicando sucesso e a equipe recém-criada.
            return ResponseEntity.status(HttpStatus.CREATED).body(equipeSalva);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao salvar a equipe. " + e.getMessage());
        }
    }

    /**
     * Atualiza uma equipe existente com base no DTO fornecido.
     *
     * @param equipeDTO O DTO contendo informações atualizadas sobre a equipe.
     * @return ResponseEntity contendo a equipe atualizada se encontrada, ou uma resposta 404 se não encontrada.
     */
    public ResponseEntity<Object> updateEquipe(EquipeDTO equipeDTO) {
        try {
            Optional<Equipe> optionalEquipe = equipeRepository.findById(equipeDTO.getId());

            if (optionalEquipe.isPresent()) {
                Equipe equipe = optionalEquipe.get();
                UUID idEquipe = equipe.getId();
                BeanUtils.copyProperties(equipeDTO, equipe);
                equipe.setId(idEquipe);
                Equipe equipeSalva = equipeRepository.save(equipe);
                return ResponseEntity.status(HttpStatus.OK).body(equipeSalva);

            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Equipe não encontrada.");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao atualizar a equipe. " + e.getMessage());
        }
    }

    /**
     * Exclui uma equipe pelo ID.
     *
     * @param id O ID da equipe a ser excluída.
     * @return ResponseEntity indicando o sucesso ou a falha da exclusão.
     */
    public ResponseEntity<Object> deleteEquipe(UUID id) {
        try {
            Optional<Equipe> optionalEquipe = equipeRepository.findById(id);

            if (optionalEquipe.isPresent()) {
                equipeRepository.deleteById(id);
                return ResponseEntity.status(HttpStatus.OK).body("Equipe excluída com sucesso");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Equipe não encontrada");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao excluir a equipe. " + e.getMessage());
        }
    }

}
