package com.songifyDatabase.infrastructure.crud.song.controller.error.dto;

import org.springframework.http.HttpStatus;

public record ErrorSongResponseDto(String message, HttpStatus httpStatus) {
}
