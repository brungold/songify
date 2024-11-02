package com.songifyDatabase.infrastructure.security.jwt;

import lombok.Builder;

@Builder
public record JetResponseDto(String token) {
}
