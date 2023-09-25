package com.songifyDatabase.song.infructure.controller.error;

import org.springframework.http.HttpStatus;

public record ErrorSongResponseDto(String message, HttpStatus httpStatus) {
}
