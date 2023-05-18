package com.example.demo.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.example.demo.models.User;
import com.example.demo.repositories.UserRepository;

@RestController
@RequestMapping("/users")
public class UserController {

    // Injects the UserRepository dependency
    @Autowired
    private UserRepository userRepository;

    // Handles GET requests to retrieve all users
    @GetMapping("")
    public ResponseEntity getUsers(){
        List<User> users = this.userRepository.findAll();
        return new ResponseEntity(users, HttpStatus.OK);
    }

    // Handles GET requests to retrieve a user by ID
    @GetMapping("{userId}")
    public ResponseEntity getUser(@PathVariable("userId") String userId){
        User user = this.userRepository.findById(userId).get();
        if(user  == null)
            return new ResponseEntity("Not Found", HttpStatus.NOT_FOUND);
        return new ResponseEntity(user, HttpStatus.OK);
    }

    // Handles POST requests to create a new user
    @PostMapping("")
    public ResponseEntity createUser(@RequestBody Map<String,String> body){
        User res = new User();
        res.setUsername(body.get("username"));
        // Saves the new user in the database
        userRepository.save(res);
        return new ResponseEntity(res, HttpStatus.CREATED);
    }

    // Handles PUT requests to update an existing user by ID
    @PutMapping("{userId}")
    public ResponseEntity updateUser(@PathVariable("userId") String userId, 
    @RequestBody Map<String,String> body){
        User user = this.userRepository.findById(userId).get();
        if(user  == null)
            return new ResponseEntity("Not Found", HttpStatus.NOT_FOUND);
        // Updates the user's username and saves it in the database
        user.setUsername(body.get("username"));
        userRepository.save(user);
        return new ResponseEntity(user, HttpStatus.OK);
    }

    // Handles DELETE requests to delete an existing user by ID
    @DeleteMapping("{userId}")
    public ResponseEntity deleteUser(@PathVariable("userId") String userId){
        User user = this.userRepository.findById(userId).get();
        if(user == null)
            return new ResponseEntity("Not Found", HttpStatus.NOT_FOUND);
        // Deletes the user from the database
        this.userRepository.delete(user);
        return new ResponseEntity(user, HttpStatus.OK);
    }

}
