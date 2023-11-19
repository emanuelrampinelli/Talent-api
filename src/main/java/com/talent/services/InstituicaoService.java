package com.talent.services;

import com.talent.dto.InstituicaoDTO;
import com.talent.model.Instituicao;
import com.talent.repository.InstituicaoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.UUID;

/**
 * Serviço responsável por operações relacionadas a instituições.
 */
@Service
public class InstituicaoService {

    @Autowired
    private InstituicaoRepository instituicaoRepository;

    /**
     * Busca uma instituição pelo ID.
     *
     * @param id ID da instituição a ser buscada.
     * @return ResponseEntity contendo a instituição encontrada ou mensagem de "Instituição não encontrada".
     */
    public ResponseEntity<Object> findById(UUID id) {
        Instituicao instituicao = instituicaoRepository.findById(id).orElse(null);
        if (instituicao != null) {
            return ResponseEntity.status(HttpStatus.OK).body(instituicao);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Instituição não encontrada");
        }
    }

    /**
     * Busca uma instituição pelo nome.
     *
     * @param nomeInstituicao Nome da instituição a ser buscada.
     * @return ResponseEntity contendo a instituição encontrada ou mensagem de "Instituição não encontrada".
     */
    public ResponseEntity<Object> findByName(String nomeInstituicao) {
        Instituicao instituicao = instituicaoRepository.findByNome(nomeInstituicao).orElse(null);
        if (instituicao != null) {
            return ResponseEntity.status(HttpStatus.OK).body(instituicao);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Instituição não encontrada");
        }
    }

    /**
     * Salva uma nova instituição.
     *
     * @param instituicaoDTO Objeto de transferência de dados contendo informações da nova instituição.
     * @return ResponseEntity contendo a instituição salva.
     */
    public ResponseEntity<Object> saveInstituicao(InstituicaoDTO instituicaoDTO) {
        Instituicao novaInstituicao = new Instituicao();
        BeanUtils.copyProperties(instituicaoDTO, novaInstituicao);
        novaInstituicao.setDataCadastro(Date.valueOf(String.valueOf(LocalDate.now())));
        Instituicao instituicaoSalva = instituicaoRepository.save(novaInstituicao);
        return ResponseEntity.status(HttpStatus.OK).body(instituicaoSalva);
    }

    /**
     * Atualiza uma instituição existente pelo ID.
     *
     * @param id               ID da instituição a ser atualizada.
     * @param instituicaoDTO Objeto de transferência de dados contendo informações atualizadas da instituição.
     * @return ResponseEntity contendo a instituição atualizada ou mensagem de "Instituição não encontrada".
     */
    public ResponseEntity<Object> updateInstituicao(UUID id, InstituicaoDTO instituicaoDTO) {
        Instituicao instituicao = instituicaoRepository.findById(id).orElse(null);
        if (instituicao != null) {
            instituicao.setNome(instituicaoDTO.getNome());
            instituicao.setDataCadastro(instituicao.getDataCadastro());
            Instituicao instituicaoAtualizada = instituicaoRepository.save(instituicao);
            return ResponseEntity.status(HttpStatus.OK).body(instituicaoAtualizada);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Instituição não encontrada");
        }
    }

    /**
     * Exclui uma instituição existente pelo ID.
     *
     * @param id ID da instituição a ser excluída.
     * @return ResponseEntity contendo mensagem de sucesso ou "Instituição não encontrada".
     */
    public ResponseEntity<Object> deleteInstituicao(UUID id) {
        Instituicao instituicao = instituicaoRepository.findById(id).orElse(null);
        if (instituicao != null) {
            instituicaoRepository.delete(instituicao);
            return ResponseEntity.status(HttpStatus.OK).body("Instituição excluída com sucesso");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Instituição não encontrada");
        }
    }
}
