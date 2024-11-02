package com.songifyDatabase;

import com.songifyDatabase.infrastructure.security.jwt.JwtConfigurationProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(value = {JwtConfigurationProperties.class})
public class SongifyApplication {

    public static void main(String[] args) {
        SpringApplication.run(SongifyApplication.class, args);
    }

}
