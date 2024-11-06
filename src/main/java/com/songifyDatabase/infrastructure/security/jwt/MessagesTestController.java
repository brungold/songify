package com.songifyDatabase.infrastructure.security.jwt;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessagesTestController {

    @GetMapping("/message")
    public ResponseEntity<MessageTestDto> message(Authentication authentication) {
        MessageTestDto message = new MessageTestDto("Hi " + authentication.getName());
        return ResponseEntity.ok(message);
    }
}
