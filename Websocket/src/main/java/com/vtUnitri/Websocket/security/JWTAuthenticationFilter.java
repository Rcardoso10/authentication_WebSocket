package com.vtUnitri.Websocket.security;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vtUnitri.Websocket.dto.CredentialsDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    private AuthenticationManager authenticationManager;

    private JWTUtil jwtUtil;

    @Autowired
    public JWTAuthenticationFilter(AuthenticationManager authenticationManager, JWTUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {
        try {
            CredentialsDto dto = new ObjectMapper().readValue(request.getInputStream(), CredentialsDto.class);
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                    dto.getEmail(), dto.getPassword(), new ArrayList<>());
            Authentication authentication = authenticationManager.authenticate(token);
            return authentication;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain filterChain,
                                            Authentication auth){
        String userName = ((UserSecurity) auth.getPrincipal()).getUsername();
        String token = jwtUtil.generateToken(userName);
        response.addHeader("Authorization","Bearer " + token);
        response.addHeader("access-control-expose-headers", "Authorization");
        log.info("<========= Token Criado com Sucesso! =========> ");
    }
}
