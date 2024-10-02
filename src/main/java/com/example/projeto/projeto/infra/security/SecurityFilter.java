package com.example.projeto.projeto.infra.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.projeto.projeto.repositories.UserRepository;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SecurityFilter extends OncePerRequestFilter {

@Autowired
TokenService tokenService;
@Autowired
UserRepository userRepository;

    // esse metodo é o filter interno que chama no SecurityCOnfig no metodo addFilterBefore
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
            
        var token = this.recoverToken(request);        
        if(token!=null){
                var login = tokenService.validateToken(token);
                UserDetails user = userRepository.findByLogin(login);
                var authentication = new UsernamePasswordAuthenticationToken(user, null,user.getAuthorities());


                SecurityContextHolder.getContext().setAuthentication(authentication);
        }    

        // aqui significa que já terminou e pode ir para o proximo filtro(o que seria no Security config)
        // o userNamePasswordAutenticationFilter
            filterChain.doFilter(request, response);
            
            }
            // retornar o valor do token para subir na aplicaçao e ser pego no doFilterInternal
            private String recoverToken(HttpServletRequest request){
          var authHeader = request.getHeader("Authorization");
          if(authHeader==null){
        return null;
          }
          
          return authHeader.replace("Bearer ", "");
            }

}
