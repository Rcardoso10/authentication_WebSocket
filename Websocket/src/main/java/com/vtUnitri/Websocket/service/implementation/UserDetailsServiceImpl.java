package com.vtUnitri.Websocket.service.implementation;

import com.vtUnitri.Websocket.domain.User;
import com.vtUnitri.Websocket.repositories.UserRepository;
import com.vtUnitri.Websocket.security.UserSecurity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        User user = userRepository.findByEmail(email);
        if(user == null){
            throw new UsernameNotFoundException(email);
        }
        return new UserSecurity(user.getId(),user.getEmail(),user.getPassword(),user.getProfiles());
    }
}
