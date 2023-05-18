package com.example.demo.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.models.User;


// The UserRepository is an interface that extends the JpaRepository interface, 
// which is a Spring Data JPA specific interface. This repository is responsible 
// for defining the persistence operations for the User entity.

// The UserRepository interface inherits several methods from the JpaRepository, 
// including methods for basic CRUD (Create, Read, Update, and Delete) operations 
// on the User entity, such as findAll(), findById(), save(), delete(), etc.

// By annotating the interface with @Repository, Spring will automatically detect 
// and create an instance of the repository bean, which can be injected into other components,
// such as services or controllers, using Spring's dependency injection mechanism.

@Repository
public interface UserRepository extends JpaRepository<User, String>{
    public Optional<User> findByUsername(String username);
}
