package com.talent.config.security.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.talent.model.Usuario;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

@Service
@Slf4j
public class TokenJWTService {

    @Value("${security.token.secret}")
    private String secret;

    @Value("${security.token.timeout.hour}")
    private int timeOutHour;

    public String gerarTokenJWT(Usuario usuario){

        try{
            Algorithm algorithm = Algorithm.HMAC256(secret);
            String token = JWT.create()
                    .withIssuer("auth")
                    .withSubject(usuario.getEmail())
                    .withExpiresAt(Date.from(calcularTimeOut()))
                    .sign(algorithm);
            return token;
        }catch (JWTCreationException e){
            log.error("JWT - Error ao criar token: "+ e);
            throw new RuntimeException(e);
        }
    }

    public String validarTokenJWT(String token){

        try{
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("auth")
                    .build()
                    .verify(token)
                    .getSubject();
        }catch (JWTVerificationException e){
            log.error("JWT - Error ao validar token: "+ e);
            return "";
        }
    }

    private Instant calcularTimeOut(){
        return LocalDateTime.now().plusHours(timeOutHour).toInstant(ZoneOffset.of("-03:00"));
    }
}
