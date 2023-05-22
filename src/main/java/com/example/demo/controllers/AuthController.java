package com.example.demo.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.models.User;
import com.example.demo.repositories.UserRepository;

@Controller
@RequestMapping("/auth")
public class AuthController {
    
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/login")
    public ModelAndView getUserForm(){
        ModelAndView mav= new ModelAndView("login.html");
        mav.addObject("user",new User());
        return mav;

    }

    @PostMapping("/login/save")
    public String loginUser(@ModelAttribute() User user){
        System.out.println(user.getUsername());
        User myUser = userRepository.findByUsername(user.getUsername()).orElse(null);
        
        if(this.bCryptPasswordEncoder.matches(user.getPassword(), myUser.getPassword())){
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                myUser.getUsername(),
                 myUser.getPassword(),
                 myUser.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            return "redirect:/menu";
        }
        return "redirect:/auth/login";

    }
    @GetMapping("/register")
    public ModelAndView getSignupForm(){
        ModelAndView mav= new ModelAndView("signup.html");
        mav.addObject("user",new User());
        return mav;

 
    }

    @PostMapping("/register/save")
     public String saveUserForm(@ModelAttribute() User user){
        String hashPass= this.bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(hashPass);
      userRepository.save(user);
      return "redirect:/auth/login";
     }
}
