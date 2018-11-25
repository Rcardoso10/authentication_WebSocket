package com.vtUnitri.Websocket.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class GeneretePassword {

    public static String generatePassword( String password ){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(password);
    }

    public static void main( String[] args ){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        System.out.println(encoder.encode("123"));
    }
}
