package com.songifyDatabase.infrastructure.security.jwt;

public record TokenRequestDto(String username, String password) {
}
