package com.talent.services;

import com.talent.dto.AvaliacaoDTO;
import com.talent.enums.AvaliacaoStatusEnum;
import com.talent.model.Avaliacao;
import com.talent.model.Equipe;
import com.talent.model.Instituicao;
import com.talent.model.ModeloQuestionario;
import com.talent.repository.AvaliacaoRepository;
import com.talent.repository.EquipeRepository;
import com.talent.repository.InstituicaoRepository;
import com.talent.repository.ModeloQuestionarioRepository;
import com.talent.util.DateFormattingHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;

import java.util.UUID;
import java.util.stream.Collectors;


@Service
public class AvaliacaoService {

    @Autowired
    private ModeloQuestionarioRepository modeloQuestionarioRepository;

    @Autowired
    private EquipeRepository equipeRepository;

    @Autowired
    private AvaliacaoRepository avaliacaoRepository;

    @Autowired
    private InstituicaoRepository instituicaoRepository;


    public ResponseEntity<Object> findByInstituicao(UUID idInstituicao) {
        try {
            Instituicao instituicao = instituicaoRepository.findById(idInstituicao).orElse(null);
            if (instituicao != null) {
                List<Avaliacao> list = avaliacaoRepository.findAllByInstituicaoId(idInstituicao);

                // Converter a lista de Avaliacao para AvaliacaoDTO
                List<AvaliacaoDTO> listDTO = list.stream()
                        .map(avaliacao -> {
                            AvaliacaoDTO dto = new AvaliacaoDTO();
                            dto.setTitulo(avaliacao.getTitulo());
                            dto.setDataInicio(avaliacao.getDataInicio() !=null? avaliacao.getDataInicio().toString(): null);
                            dto.setDataTermino(avaliacao.getDataTermino() != null? avaliacao.getDataTermino().toString(): null);
                            dto.setDescricao(avaliacao.getDescricao());
                            dto.setIdModeloAutoAvaliacao(avaliacao.getModeloAutoAvaliacao().getId());
                            dto.setIdModeloInterface(avaliacao.getModeloInterface().getId());
                            dto.setIdModeloSupervisor(avaliacao.getModeloSupervisor().getId());
                            dto.setIdModeloLiderado(avaliacao.getModeloLiderado().getId());
                            dto.setIdInstituicao(avaliacao.getInstituicao().getId());
                            dto.setStatus(resolveStatusAvaliacao(avaliacao.getDataInicio(),avaliacao.getDataTermino()));
                            return dto;
                        })
                        .collect(Collectors.toList());

                return ResponseEntity.status(HttpStatus.OK).body(listDTO);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Instituicao não encontrada.");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao buscar colaboradores. " + e.getMessage());
        }
    }


    public ResponseEntity<Object> saveAvaliacao(AvaliacaoDTO avaliacaoDTO){

        Instituicao instituicao = instituicaoRepository.findById(avaliacaoDTO.getIdInstituicao()).orElseThrow(IllegalAccessError::new);
        ModeloQuestionario modeloAutoAvaliacao = modeloQuestionarioRepository.findById(avaliacaoDTO.getIdModeloAutoAvaliacao()).orElseThrow(IllegalAccessError::new);
        ModeloQuestionario modeloInterface = modeloQuestionarioRepository.findById(avaliacaoDTO.getIdModeloInterface()).orElseThrow(IllegalAccessError::new);
        ModeloQuestionario modeloSupervisor = modeloQuestionarioRepository.findById(avaliacaoDTO.getIdModeloSupervisor()).orElseThrow(IllegalAccessError::new);
        ModeloQuestionario modeloLiderado = modeloQuestionarioRepository.findById(avaliacaoDTO.getIdModeloLiderado()).orElseThrow(IllegalAccessError::new);

        try {

            Date dataInicio = DateFormattingHelper.formatDateTraco(avaliacaoDTO.getDataInicio());
            Date dataTermino = DateFormattingHelper.formatDateTraco(avaliacaoDTO.getDataTermino());

            if(dataInicio == null){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Data inicio inválida.");
            }

            Avaliacao avaliacao = new Avaliacao();
            avaliacao.setTitulo(avaliacaoDTO.getTitulo());
            avaliacao.setDataInicio(dataInicio);
            avaliacao.setDataTermino(dataTermino);
            avaliacao.setDescricao(avaliacaoDTO.getDescricao());
            avaliacao.setModeloAutoAvaliacao(modeloAutoAvaliacao);
            avaliacao.setModeloInterface(modeloInterface);
            avaliacao.setModeloSupervisor(modeloSupervisor);
            avaliacao.setModeloLiderado(modeloLiderado);
            avaliacao.setInstituicao(instituicao);

            // Inicialize a coleção de equipes
            avaliacao.setEquipes(new HashSet<>());

            // Adicione as equipes à avaliação
            for (UUID id : avaliacaoDTO.getIdEquipes()) {
                avaliacao.getEquipes().add(equipeRepository.findById(id).orElseThrow());
            }

            // Persista a avaliação no banco de dados
            avaliacaoRepository.save(avaliacao);

            return ResponseEntity.status(HttpStatus.CREATED).body("Avaliação criada com sucesso.");

        }catch (Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex);
        }

    }

    private AvaliacaoStatusEnum resolveStatusAvaliacao(Date dataInicial, Date dataTermino){

        Date dataAtual = Date.valueOf(LocalDate.now());

        if (dataInicial == null || dataTermino == null){
            return AvaliacaoStatusEnum.PAUSADA;
        }
        else if (dataTermino.after(dataAtual)) {
            return AvaliacaoStatusEnum.ENCERRADO;
        }
        else if (dataInicial.after(dataAtual)) {
                return AvaliacaoStatusEnum.EM_ANDAMENTO;
        }
        return AvaliacaoStatusEnum.CRIADO;
    }

}
