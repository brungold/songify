package com.songifyDatabase.infrastructure.security;

import com.songifyDatabase.domain.usercrud.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig {

    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepository) {
        return new UserDetailsServiceImpl(userRepository);
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }

    //    @Bean
//    public UserDetailsService userDetailsService(){
//        var manager = new InMemoryUserDetailsManager();
//        var user1 = User.withUsername("maciek")
//                .password("12345")
//                .roles("USER")
//                .build();
//        var user2 = User.withUsername("albert")
//                .password("12345")
//                .roles("ADMIN")
//                .build();
//
//        manager.createUser(user1);
//        manager.createUser(user2);
//        return manager;
//    }
}
