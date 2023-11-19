package com.talent.controllers;

import com.talent.config.security.services.TokenJWTService;
import com.talent.dto.usuario.UsuarioDTO;
import com.talent.dto.usuario.request.LoginUsuarioRequestDTO;
import com.talent.dto.usuario.request.RegistrarUsuarioRequestDTO;
import com.talent.dto.usuario.response.LoginUsuarioResponseDTO;
import com.talent.dto.usuario.response.RegistrarUsuarioResponseDTO;
import com.talent.enums.MensagemResponseEnum;
import com.talent.model.Usuario;
import com.talent.services.UsuarioService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.time.LocalDate;

@RestController
@RequestMapping("/auth")
@Slf4j
public class UsuarioController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenJWTService tokenJWTService;

    @Autowired
    private UsuarioService usuarioService;

    /**
     * Autentica um usuário.
     *
     * @param usuario Dados do usuário para autenticação.
     * @return ResponseEntity contendo LoginUsuarioResponseDTO ou mensagem de erro.
     */
    @PostMapping("/login")
    public ResponseEntity<LoginUsuarioResponseDTO> login(@RequestBody @Valid LoginUsuarioRequestDTO usuario) {

        LoginUsuarioResponseDTO loginUsuarioResponseDTO;

        try {
            var usernamePassword = new UsernamePasswordAuthenticationToken(usuario.getEmail(), usuario.getSenha());
            var auth = authenticationManager.authenticate(usernamePassword);
            var token = tokenJWTService.gerarTokenJWT((Usuario) auth.getPrincipal());

            UsuarioDTO usuarioDTO = usuarioService.findByEmail(usuario.getEmail());
            loginUsuarioResponseDTO = new LoginUsuarioResponseDTO(MensagemResponseEnum.AUTENTICACAO_SUCESSO, token, usuarioDTO);

            return ResponseEntity.status(HttpStatus.OK).body(loginUsuarioResponseDTO);

        } catch (Exception ex) {
            log.error(MensagemResponseEnum.AUTENTICACAO_FALHA.getValue() + " : " + ex.getMessage(), ex);
            loginUsuarioResponseDTO = new LoginUsuarioResponseDTO(MensagemResponseEnum.AUTENTICACAO_FALHA, null, null);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(loginUsuarioResponseDTO);
        }
    }

    /**
     * Cadastra um novo usuário.
     *
     * @param registrarUsuarioRequestDTO Dados do novo usuário.
     * @return ResponseEntity contendo RegistrarUsuarioResponseDTO ou mensagem de erro.
     */
    @PostMapping("/register")
    public ResponseEntity<RegistrarUsuarioResponseDTO> register(@RequestBody @Valid RegistrarUsuarioRequestDTO registrarUsuarioRequestDTO) {

        Usuario usuario = new Usuario();
        UsuarioDTO usuarioDTO = usuarioService.findByEmail(registrarUsuarioRequestDTO.getEmail());

        // Verifica a existência do mesmo email já cadastrado.
        if (usuarioDTO != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new RegistrarUsuarioResponseDTO(MensagemResponseEnum.EMAIL_JA_CADASTRADO, null));
        } else {

            BeanUtils.copyProperties(registrarUsuarioRequestDTO,usuario);
            usuario.setSenha(new BCryptPasswordEncoder().encode(registrarUsuarioRequestDTO.getSenha()));
            usuario.setDataCadastro(Date.valueOf(LocalDate.now()));
            usuario.setIsBloqueado(registrarUsuarioRequestDTO.isBloqueado()?1:0);

            usuarioDTO = usuarioService.save(usuario);

            if (usuarioDTO != null) {
                return ResponseEntity.status(HttpStatus.OK).body(
                        new RegistrarUsuarioResponseDTO(MensagemResponseEnum.CADASTRO_SUCESSO, usuarioDTO));
            }
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(
                    new RegistrarUsuarioResponseDTO(MensagemResponseEnum.CADASTRO_FALHA, null));
        }
    }
}
