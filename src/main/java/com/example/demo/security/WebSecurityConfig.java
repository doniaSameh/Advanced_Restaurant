package com.example.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.example.demo.services.UserService;

// This annotation indicates that this class provides the web security configuration for the application
@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    
    // This annotation enables dependency injection of the AuthenticationFilter bean
    // @Autowired
    // private AuthenticationFilter authenticationFilter;

    // This method defines the security filter chain for the application
    @Autowired
    private UserService service;
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
        .userDetailsService(service)
        .csrf().disable()
        .authorizeRequests()
        .antMatchers("/auth/register","/auth/register/save")
        .permitAll()
        .anyRequest()
        .authenticated()
        .and()
        .formLogin()
        .loginPage("/auth/login")
        .loginProcessingUrl("/auth/login/save")
        .defaultSuccessUrl("/menu")
        .permitAll()
        .and()
        .logout()
        .permitAll()
        .logoutRequestMatcher(new AntPathRequestMatcher("/auth/logout")) 
        .logoutSuccessUrl("/auth/login"); 
        return httpSecurity.build();
    }

    // This method creates a BCryptPasswordEncoder bean for password encoding
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
