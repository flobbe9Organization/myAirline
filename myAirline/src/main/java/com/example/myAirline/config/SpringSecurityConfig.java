package com.example.myAirline.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


/**
 * Configuration for security and password encoding.
 * 
 * @since 1.0
 * @author Florin Schikarski
 */
@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {
    
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.csrf().disable()
            // .headers().frameOptions().disable()
            // .and()
            .authorizeRequests()
            .antMatchers("/**").permitAll() // TODO: for testing only, remove later
            .anyRequest()
            .authenticated()
            .and()
            .formLogin();
            
            return http.build();
    }


    @Bean
    public PasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder();
    }
}