package com.songify.song.infructure.controller.dto.response;

import org.springframework.http.HttpStatus;

public record DeleteSongResponseDto(String message, HttpStatus status) {
}
