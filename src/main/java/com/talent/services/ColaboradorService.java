package com.talent.services;

import com.talent.dto.ColaboradorDTO;
import com.talent.dto.usuario.UsuarioDTO;
import com.talent.enums.MensagemResponseEnum;
import com.talent.model.Cargo;
import com.talent.model.Colaborador;
import com.talent.model.Instituicao;
import com.talent.model.Usuario;
import com.talent.repository.CargoRepository;
import com.talent.repository.ColaboradorRepository;
import com.talent.repository.InstituicaoRepository;
import com.talent.util.DateFormattingHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Serviço que fornece operações relacionadas a Colaboradores.
 */
@Service
public class ColaboradorService {

    @Autowired
    private ColaboradorRepository colaboradorRepository;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private InstituicaoRepository instituicaoRepository;

    @Autowired
    private CargoRepository cargoRepository;

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
     * Obtém uma lista de colaboradores por ID de Instituição.
     *
     * @param id ID da instituição para a qual se deseja obter a lista de colaboradores.
     * @return ResponseEntity contendo a lista de colaboradores ou uma mensagem de erro.
     */
    public ResponseEntity<Object> getColaboradoresByInstituicao(UUID id) {
        // Chama o repositório para obter a lista de colaboradores associados à instituição pelo ID.
        List<Colaborador> colaboradores = colaboradorRepository.findAllByInstituicaoId(id);

        // Verifica se a lista de colaboradores não está vazia.
        if (!colaboradores.isEmpty()) {
            // Retorna uma resposta HTTP indicando sucesso e a lista de colaboradores.
            return ResponseEntity.status(HttpStatus.OK).body(colaboradores);
        } else {
            // Retorna uma resposta HTTP indicando que nenhum colaborador foi encontrado para a instituição.
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nenhum colaborador encontrado para a instituição com o ID: " + id);
        }
    }

    /**
     * Salva um novo colaborador, associado a uma instituição e cargo específicos.
     *
     * @param colaboradorDTO DTO contendo informações do novo colaborador.
     * @return ResponseEntity contendo o colaborador recém-criado ou uma mensagem de erro.
     */
    public ResponseEntity<Object> saveColaborador(ColaboradorDTO colaboradorDTO) {

        // Verifica se a instituição associada ao colaborador existe.
        Instituicao instituicao = instituicaoRepository.findById(colaboradorDTO.getIdInstituicao()).orElse(null);
        if (instituicao == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Instituição não cadastrada.");
        }

        // Verifica se o cargo associado ao colaborador existe.
        Cargo cargo = cargoRepository.findById(colaboradorDTO.getIdCargo()).orElse(null);
        if (cargo == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Cargo não cadastrado.");
        }

        // Verifica se já existe um usuário com o mesmo e-mail cadastrado.
        UsuarioDTO usuarioDTO = usuarioService.findByEmail(colaboradorDTO.getUsuario().getEmail());
        Usuario usuario = new Usuario();

        // Se o e-mail já estiver cadastrado, retorna um código de erro.
        if (usuarioDTO != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(MensagemResponseEnum.EMAIL_JA_CADASTRADO);
        } else {
            // Copia as propriedades do DTO para a entidade Usuario.
            BeanUtils.copyProperties(colaboradorDTO.getUsuario(), usuario);
            usuario.setSenha(new BCryptPasswordEncoder().encode(colaboradorDTO.getUsuario().getSenha()));
            usuario.setDataCadastro(Date.valueOf(LocalDate.now()));
            usuario.setIsBloqueado(colaboradorDTO.getUsuario().isBloqueado() ? 1 : 0);
            usuarioDTO = usuarioService.save(usuario);

            // Se o usuário for salvo com sucesso, continua o processo.
            if (usuarioDTO != null) {
                Colaborador novoColaborador = new Colaborador();
                // Copia as propriedades do DTO para a entidade Colaborador.
                BeanUtils.copyProperties(usuarioDTO, usuario);
                novoColaborador.setUsuario(usuario);
                novoColaborador.setInstituicao(instituicao);
                novoColaborador.setCargo(cargo);  // Adiciona a associação com o cargo.
                BeanUtils.copyProperties(colaboradorDTO, novoColaborador);

                // Formata as datas no formato desejado.
                novoColaborador.setDataNascimento(DateFormattingHelper.formatDateTraco(colaboradorDTO.getDataNascimento()));
                novoColaborador.setDataAdmissao(DateFormattingHelper.formatDateTraco(colaboradorDTO.getDataAdmissao()));
                novoColaborador.setDataDesligamento(DateFormattingHelper.formatDateTraco(colaboradorDTO.getDataDesligamento()));

                // Salva o novo colaborador no repositório.
                Colaborador colaboradorSalvo = colaboradorRepository.save(novoColaborador);
                BeanUtils.copyProperties(colaboradorSalvo, colaboradorDTO);
                colaboradorDTO.getUsuario().setId(usuarioDTO.getId());

                // Retorna uma resposta HTTP indicando sucesso e os dados do colaborador.
                return ResponseEntity.status(HttpStatus.CREATED).body(colaboradorDTO);
            }

            // Se houver falha no cadastro, retorna um código de erro.
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(MensagemResponseEnum.CADASTRO_FALHA);
        }
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
            UUID idColaborador = colaboradorAtualizado.getId();
            BeanUtils.copyProperties(colaboradorDTO, colaboradorAtualizado);
            colaboradorAtualizado.setId(idColaborador);
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
