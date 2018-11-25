package com.vtUnitri.Websocket.controller;

import com.vtUnitri.Websocket.domain.User;
import com.vtUnitri.Websocket.service.UserService;
import com.vtUnitri.Websocket.service.exception.InvalidParametersException;
import com.vtUnitri.Websocket.service.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService service){
        this.userService = service;
    }

    @GetMapping
    public ResponseEntity findAll(){
        return ResponseEntity.ok(this.userService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity findOne(@PathVariable Long id){
        return ResponseEntity.ok(this.userService.findbyId(id));
    }

    @GetMapping("/{email}")
    public ResponseEntity findByEmail(@PathVariable String email){
        return ResponseEntity.ok(this.userService.findByEmail(email));
    }

    @PostMapping
    public ResponseEntity save(@RequestBody User user){
        try {
            return ResponseEntity.ok(this.userService.save(user));
        }catch (ObjectNotFoundException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping
    public ResponseEntity update(@RequestBody User user){
        try {
            return ResponseEntity.ok(this.userService.save(user));
        }catch (ObjectNotFoundException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id){
        try {
            this.userService.delete(id);
            return ResponseEntity.ok().build();
        }catch (InvalidParametersException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
