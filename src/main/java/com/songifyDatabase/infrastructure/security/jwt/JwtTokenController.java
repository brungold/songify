package com.songifyDatabase.infrastructure.security.jwt;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class JwtTokenController {

    private final JwtTokenGenerator tokenGenerator;

    @PostMapping("/token")
    public ResponseEntity<JetResponseDto> authenticateAndGenerateToken(@RequestBody TokenRequestDto dto){
        //1. generujemy token
        String token = tokenGenerator.authenticateAndGenerateToken(dto.username(), dto.password());
        return ResponseEntity.ok(
                JetResponseDto.builder()
                        .token(token)
                        .build()
        );
    }
}
