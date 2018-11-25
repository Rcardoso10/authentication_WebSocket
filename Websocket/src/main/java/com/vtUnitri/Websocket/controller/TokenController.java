package com.vtUnitri.Websocket.controller;

import com.vtUnitri.Websocket.dto.CredentialsDto;
import com.vtUnitri.Websocket.dto.TokenDto;
import com.vtUnitri.Websocket.security.JWTUtil;
import com.vtUnitri.Websocket.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/auth")
public class TokenController {

    private UserDetailsService userDetailsService;

    private UserService userService;

    private AuthenticationManager authenticationManager;

    private JWTUtil jwtUtil;

    @Autowired
    public TokenController(UserDetailsService userDetailsService, UserService userService,
                           AuthenticationManager authenticationManager, JWTUtil jwtUtil) {
        this.userDetailsService = userDetailsService;
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping
    public TokenDto createToken(@RequestBody CredentialsDto dto){
        try {
            TokenDto tokenDto = new TokenDto();
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            dto.getEmail(),dto.getPassword(), new ArrayList<>()
                    )
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
            final UserDetails userDetails = userDetailsService.loadUserByUsername(dto.getEmail());
            final String token = "Bearer " + jwtUtil.generateToken(userDetails.getUsername());
            //final User user = userService.findByEmail(dto.getEmail()) ;
            tokenDto.setAuthorization(token);
            return tokenDto;
        }catch (Exception e){
            throw new BadCredentialsException("Não foi possivel criar o token");
        }
    }
}
