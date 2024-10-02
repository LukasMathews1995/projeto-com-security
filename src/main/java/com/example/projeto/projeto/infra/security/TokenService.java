package com.example.projeto.projeto.infra.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.example.projeto.projeto.domain.user.User;
import java.time.*;


@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;

    //gerar o token
    public String generateToken(User user){
        try {
            Algorithm algorithm =  Algorithm.HMAC256(secret);

            //issuer é quem é o emissor ,quem criou o token
            //subject - sujeito que ta recebnedo esse token
            //sign fazer a assinatura e a geracao final
                String token = JWT.create().withIssuer("projeto")
                .withSubject(user.getLogin()).withExpiresAt(timeExpiration()).sign(algorithm);
          return token;
    
          
        } catch (JWTCreationException e) {
           throw new RuntimeException("Error while generation token", e);
        }
    }
// retorna o login do usuario
    public String validateToken(String token){
            Algorithm algorithm = Algorithm.HMAC256(secret);


        try {
            return JWT.require(algorithm).withIssuer("projeto").build().verify(token)
            .getSubject();
        } catch (JWTVerificationException e) {
            return "";
          
        }
    }

    private Instant timeExpiration (){
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }

}
