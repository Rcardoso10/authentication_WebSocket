package com.vtUnitri.Websocket.service.implementation;

import com.vtUnitri.Websocket.security.UserSecurity;
import org.springframework.security.core.context.SecurityContextHolder;

public class UserServiceLogged {

    public static UserSecurity authenticated(){
        try {
            return (UserSecurity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        }catch (Exception e){
            return null;
        }
    }
}
