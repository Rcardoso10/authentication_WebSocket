package com.vtUnitri.Websocket.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JWTUtil {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private Long expiration;

    public String generateToken(String userName){
        return Jwts.builder()
                .setSubject(userName)
                .setExpiration(new Date(System.currentTimeMillis() + expiration) )
                .signWith( SignatureAlgorithm.HS512, secret.getBytes())
                .compact();
    }

    public boolean tokenValid(String token) {
        Claims claims = getClains(token);
        if(claims != null){
            String userName = claims.getSubject();
            Date expiration = claims.getExpiration();
            Date now = new Date(System.currentTimeMillis());
            if(userName != null && expiration != null && now.before(expiration)){
                return true;
            }
        }
        return false;
    }

    private Claims getClains(String token) {
        try {
            return Jwts.parser().setSigningKey(secret.getBytes()).parseClaimsJws(token).getBody();
        }catch (Exception e){
            return null;
        }

    }

    public String getUserName(String token) {
        Claims claims = getClains(token);
        if(claims != null){
            return claims.getSubject();
        }
        return null;
    }
}
