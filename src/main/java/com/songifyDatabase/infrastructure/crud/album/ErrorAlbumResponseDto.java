package com.songifyDatabase.infrastructure.crud.album;

import org.springframework.http.HttpStatus;

public record ErrorAlbumResponseDto(String message, HttpStatus status) {
}
