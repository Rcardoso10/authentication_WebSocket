package com.vtUnitri.Websocket.service;

import com.vtUnitri.Websocket.domain.User;

import java.util.List;


public interface UserService {

    List<User> findAll();

    User findbyId(Long id);

    User save (User user);

    void delete (Long id);

    User findByEmail (String email);
}
