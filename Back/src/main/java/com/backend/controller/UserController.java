package com.backend.controller;

import com.backend.dto.UserDTO;
import com.backend.model.User;
import com.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping(produces = "application/json")
    public ResponseEntity<List<User>> getTests() {
        List<User> users = userService.findAll();
        if(users != null)
        {
            return new ResponseEntity<>(users, HttpStatus.OK);
        }
        return new ResponseEntity<>(users, HttpStatus.NOT_MODIFIED);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Integer id) {
        userService.remove(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value = "/login",produces = "application/json")
    public ResponseEntity<User> getUser(@RequestBody UserDTO userDTO) {
        List<User> users = userService.findAll();
        if(users != null)
        {
            for(User user : users){
                if(user.getUsername().equals(userDTO.getUsername()) && user.getPassword().equals(userDTO.getPassword()))
                    return new ResponseEntity<>(user, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_MODIFIED);
    }

    @GetMapping(value = "/{username}", produces = "application/json")
    public ResponseEntity<User> getUser(@PathVariable String username) {
        List<User> users = userService.findAll();
        if(users.size() != 0)
        {
            for(User k : users){
                if(k.getUsername().equals(username)){
                    return new ResponseEntity<>(k, HttpStatus.OK);
                }
            }
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_MODIFIED);
    }
}
