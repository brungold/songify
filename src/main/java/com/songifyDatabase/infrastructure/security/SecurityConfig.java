package com.songifyDatabase.infrastructure.security;

import com.songifyDatabase.domain.usercrud.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;

@Configuration
public class SecurityConfig {

    @Bean
    public UserDetailsManager userDetailsService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return new UserDetailsServiceImpl(userRepository, passwordEncoder);
    }

    @Bean
    PasswordEncoder passwordEncoder(){
//        return NoOpPasswordEncoder.getInstance();
        return new BCryptPasswordEncoder();
    }
}
