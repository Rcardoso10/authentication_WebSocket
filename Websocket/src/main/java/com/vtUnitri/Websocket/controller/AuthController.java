package com.vtUnitri.Websocket.controller;

import com.vtUnitri.Websocket.security.JWTUtil;
import com.vtUnitri.Websocket.security.UserSecurity;
import com.vtUnitri.Websocket.service.implementation.UserServiceLogged;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private JWTUtil jwtUtil;

    @Autowired
    public AuthController(JWTUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/refreshToken")
    public ResponseEntity<Void> refreshToken(HttpServletResponse response){
        UserSecurity userSecurity = UserServiceLogged.authenticated();
        String token = jwtUtil.generateToken(userSecurity.getUsername());
        response.addHeader("Authorization ", "Bearer " + token);
        response.addHeader("access-control-expose-headers", "Authorization");
        return ResponseEntity.noContent().build();
    }
}
