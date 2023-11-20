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

@Service
public class EquipeService {

    @Autowired
    private EquipeRepository equipeRepository;

    @Autowired
    private ColaboradorRepository colaboradorRepository;

    /**
     * Obtém uma equipe por ID.
     *
     * @param id ID da equipe a ser obtida.
     * @return ResponseEntity contendo a equipe ou uma mensagem de erro.
     */
    public ResponseEntity<Object> getEquipeById(UUID id) {
        Optional<Equipe> optionalEquipe = equipeRepository.findById(id);
        if (optionalEquipe.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(optionalEquipe.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Equipe não encontrada para o ID: " + id);
        }
    }

    /**
     * Salva uma nova equipe.
     *
     * @param equipeDTO DTO contendo informações da nova equipe.
     * @return ResponseEntity contendo a equipe recém-criada ou uma mensagem de erro.
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
     * Atualiza uma equipe existente.
     *
     * @param id        ID da equipe a ser atualizada.
     * @param equipeDTO DTO contendo as informações atualizadas da equipe.
     * @return ResponseEntity contendo a equipe atualizada ou uma mensagem de erro.
     */
    public ResponseEntity<Object> updateEquipe(UUID id, EquipeDTO equipeDTO) {
        // Implemente a lógica de validação e atualização da equipe no repositório.
        // ...

        // Exemplo de retorno de uma mensagem de sucesso.
        return ResponseEntity.status(HttpStatus.OK).body("Equipe atualizada com sucesso.");
    }

    /**
     * Exclui uma equipe por ID.
     *
     * @param id ID da equipe a ser excluída.
     * @return ResponseEntity indicando o sucesso ou uma mensagem de erro.
     */
    public ResponseEntity<Object> deleteEquipe(UUID id) {
        // Implemente a lógica de exclusão da equipe no repositório.
        // ...

        // Exemplo de retorno de uma mensagem de sucesso.
        return ResponseEntity.status(HttpStatus.OK).body("Equipe excluída com sucesso.");
    }
}
