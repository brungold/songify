package com.songifyDatabase.infrastructure.crud.album;

import com.songifyDatabase.domain.crud.AlbumNotFoundException;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Log4j2
public class AlbumErrorHandler {

    @ExceptionHandler(AlbumNotFoundException.class)
    public ResponseEntity<ErrorAlbumResponseDto> handlerException(AlbumNotFoundException exception) {
        log.warn("AlbumNotFoundException while accessing album");
        ErrorAlbumResponseDto dto = new ErrorAlbumResponseDto(exception.getMessage(), HttpStatus.NOT_FOUND);
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(dto);
    }
}
