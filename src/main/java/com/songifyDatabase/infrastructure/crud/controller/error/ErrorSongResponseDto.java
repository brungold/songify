package com.songifyDatabase.infrastructure.crud.controller.error;

import org.springframework.http.HttpStatus;

public record ErrorSongResponseDto(String message, HttpStatus httpStatus) {
}
