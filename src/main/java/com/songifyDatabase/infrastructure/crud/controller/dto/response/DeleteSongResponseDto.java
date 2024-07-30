package com.songifyDatabase.infrastructure.crud.controller.dto.response;

import org.springframework.http.HttpStatus;

public record DeleteSongResponseDto(String message, HttpStatus status) {
}
