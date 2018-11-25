package com.vtUnitri.Websocket.service.implementation;

import com.vtUnitri.Websocket.domain.User;
import com.vtUnitri.Websocket.repositories.UserRepository;
import com.vtUnitri.Websocket.service.UserService;
import com.vtUnitri.Websocket.service.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.InvalidParameterException;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository repository){
        this.userRepository = repository;
    }

    @Override
    public List<User> findAll() {
        return this.userRepository.findAll();
    }

    @Override
    public User findbyId(Long id) {
        Optional<User> obj = this.userRepository.findById(id);
        return obj.orElse(null);
    }


    @Override
    public User save(User user) {
        if(user == null){
            throw new ObjectNotFoundException("Object cannot be null!");
        }
        return this.userRepository.save(user);
    }

    @Override
    public void delete(Long id) {
        if(id == null){
            throw new InvalidParameterException("Id cannot be null");
        }
        this.userRepository.delete(id);
    }

    @Override
    public User findByEmail(String email) {
        if(email == null){
            throw new InvalidParameterException("Email cannot be null");
        }
        return this.userRepository.findByEmail(email);
    }
}
