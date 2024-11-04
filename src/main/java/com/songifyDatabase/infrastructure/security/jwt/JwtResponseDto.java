package com.songifyDatabase.infrastructure.security.jwt;

import lombok.Builder;

@Builder
public record JwtResponseDto(String token) {
}
