package com.talent.services;

import com.talent.dto.AvaliacaoDTO;
import com.talent.enums.AvaliacaoStatusEnum;
import com.talent.enums.QuestionarioStatusEnum;
import com.talent.model.*;
import com.talent.repository.*;
import com.talent.util.DateFormattingHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Serviço para operações relacionadas a Avaliações.
 */
@Service
public class AvaliacaoService {

    @Autowired
    private ModeloQuestionarioRepository modeloQuestionarioRepository;

    @Autowired
    private QuestionarioRepository questionarioRepository;

    @Autowired
    private EquipeRepository equipeRepository;

    @Autowired
    private AvaliacaoRepository avaliacaoRepository;

    @Autowired
    private InstituicaoRepository instituicaoRepository;

    @Autowired
    private ColaboradorRepository colaboradorRepository;

    /**
     * Busca avaliações associadas a uma instituição.
     *
     * @param idInstituicao O identificador da instituição.
     * @return ResponseEntity contendo a lista de AvaliacaoDTO ou mensagem de erro.
     */
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
                            dto.setDataInicio(avaliacao.getDataInicio() != null ? avaliacao.getDataInicio().toString() : null);
                            dto.setDataTermino(avaliacao.getDataTermino() != null ? avaliacao.getDataTermino().toString() : null);
                            dto.setDescricao(avaliacao.getDescricao());
                            dto.setIdModeloAutoAvaliacao(avaliacao.getModeloAutoAvaliacao().getId());
                            dto.setIdModeloInterface(avaliacao.getModeloInterface().getId());
                            dto.setIdModeloSupervisor(avaliacao.getModeloSupervisor().getId());
                            dto.setIdModeloLiderado(avaliacao.getModeloLiderado().getId());
                            dto.setIdInstituicao(avaliacao.getInstituicao().getId());
                            dto.setStatus(resolveStatusAvaliacao(avaliacao.getDataInicio(), avaliacao.getDataTermino()));
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

    /**
     * Exclui uma avaliação com base no ID.
     *
     * @param id O identificador da avaliação a ser excluída.
     * @return ResponseEntity indicando o sucesso ou falha da exclusão.
     */
    public ResponseEntity<Object> deleteAvaliacao(UUID id) {
        Optional<Avaliacao> optionalAvaliacao = avaliacaoRepository.findById(id);
        if (optionalAvaliacao.isPresent()) {
            avaliacaoRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body("Avaliacao excluída com sucesso");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Avaliacao não encontrada");
        }
    }

    /**
     * Salva uma nova avaliação com base nos dados fornecidos no AvaliacaoDTO.
     *
     * @param avaliacaoDTO Os dados da avaliação a serem salvos.
     * @return ResponseEntity indicando o sucesso ou falha da criação da avaliação.
     */
    public ResponseEntity<Object> saveAvaliacao(AvaliacaoDTO avaliacaoDTO) {

        // Obtenção das entidades associadas à avaliação
        Instituicao instituicao = instituicaoRepository.findById(avaliacaoDTO.getIdInstituicao()).orElseThrow(IllegalAccessError::new);
        ModeloQuestionario modeloAutoAvaliacao = modeloQuestionarioRepository.findById(avaliacaoDTO.getIdModeloAutoAvaliacao()).orElseThrow(IllegalAccessError::new);
        ModeloQuestionario modeloInterface = modeloQuestionarioRepository.findById(avaliacaoDTO.getIdModeloInterface()).orElseThrow(IllegalAccessError::new);
        ModeloQuestionario modeloSupervisor = modeloQuestionarioRepository.findById(avaliacaoDTO.getIdModeloSupervisor()).orElseThrow(IllegalAccessError::new);
        ModeloQuestionario modeloLiderado = modeloQuestionarioRepository.findById(avaliacaoDTO.getIdModeloLiderado()).orElseThrow(IllegalAccessError::new);

        try {

            // Conversão das datas fornecidas no formato String para o tipo Date
            Date dataInicio = DateFormattingHelper.formatDateTraco(avaliacaoDTO.getDataInicio());
            Date dataTermino = DateFormattingHelper.formatDateTraco(avaliacaoDTO.getDataTermino());

            if (dataInicio == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Data inicio inválida.");
            }

            // Criação da entidade Avaliacao com base nos dados fornecidos
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

            // Montagem dos questionários associados às equipes
            montarEquipes(avaliacaoDTO);

            return ResponseEntity.status(HttpStatus.CREATED).body("Avaliação criada com sucesso.");

        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex);
        }
    }

    /**
     * Resolve o status da avaliação com base nas datas fornecidas.
     *
     * @param dataInicial A data de início da avaliação.
     * @param dataTermino A data de término da avaliação.
     * @return O status da avaliação.
     */
    private AvaliacaoStatusEnum resolveStatusAvaliacao(Date dataInicial, Date dataTermino) {

        Date dataAtual = Date.valueOf(LocalDate.now());

        if (dataInicial == null || dataTermino == null) {
            return AvaliacaoStatusEnum.PAUSADA;
        } else if (dataTermino.after(dataAtual)) {
            return AvaliacaoStatusEnum.ENCERRADO;
        } else if (dataInicial.after(dataAtual)) {
            return AvaliacaoStatusEnum.EM_ANDAMENTO;
        }
        return AvaliacaoStatusEnum.CRIADO;
    }

    /**
     * Monta os questionários associados às equipes para uma avaliação.
     *
     * @param avaliacao Os dados da avaliação.
     */
    private void montarEquipes(AvaliacaoDTO avaliacao) {

        for (UUID idEquipe : avaliacao.getIdEquipes()) {

            Equipe equipe = equipeRepository.findById(idEquipe).orElseThrow(IllegalArgumentException::new);

            Colaborador lider = equipe.getLider();
            List<Colaborador> colaboradores = colaboradorRepository.findAllByEquipeId(equipe.getId());

            ModeloQuestionario modeloInterface = modeloQuestionarioRepository.findById(avaliacao.getIdModeloInterface()).orElseThrow();
            ModeloQuestionario modeloSupervisor = modeloQuestionarioRepository.findById(avaliacao.getIdModeloSupervisor()).orElseThrow();
            ModeloQuestionario modeloAutoAvalicao = modeloQuestionarioRepository.findById(avaliacao.getIdModeloLiderado()).orElseThrow();

            for (Colaborador colabAvalia : colaboradores) {

                Questionario questionarioLider = new Questionario();
                questionarioLider.setAvaliado(lider);
                questionarioLider.setAvaliador(colabAvalia);
                questionarioLider.setModelo(modeloSupervisor);
                questionarioLider.setStatus(QuestionarioStatusEnum.NAO_INICIADO);

                questionarioRepository.save(questionarioLider);

                for (Colaborador colabAvaliado : colaboradores) {

                    Questionario questionario = new Questionario();

                    if (!colabAvalia.equals(colabAvaliado)) {
                        questionario.setAvaliado(colabAvaliado);
                        questionario.setAvaliador(colabAvalia);
                        questionario.setModelo(modeloInterface);

                    } else {
                        questionario.setAvaliado(colabAvalia);
                        questionario.setAvaliador(colabAvalia);
                        questionario.setModelo(modeloAutoAvalicao);
                    }
                    questionario.setStatus(QuestionarioStatusEnum.NAO_INICIADO);
                    questionarioRepository.save(questionario);
                }
            }
        }
    }
}
